package currency

import (
	"fmt"
	"strings"
	"time"

	"github.com/pkg/errors"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/common"
)

type Currency string

type CurrencyRates struct {
	Base   Currency             `json:"base"`
	Rates  map[Currency]float64 `json:"rates"`
	AtTime time.Time            `json:"at_time"`
}

func New(base Currency, rates map[Currency]float64) *CurrencyRates {
	return &CurrencyRates{
		Base:   base,
		Rates:  rates,
		AtTime: time.Now(),
	}
}

func (cr *CurrencyRates) FromToAnotherBase(newBase Currency) (*CurrencyRates, error) {
	if newBase == cr.Base {
		return New(cr.Base, cr.Rates), nil
	}

	newBaseCurrency, ok := cr.Rates[newBase]
	if !ok {
		msg := fmt.Sprintf("Currency=%s not found", newBase)
		return nil, errors.Wrap(common.EntityNotFound, msg)
	}

	newBaseCurrencyRates := make(map[Currency]float64, len(cr.Rates))
	for key, value := range cr.Rates {
		newBaseCurrencyRates[key] = value / newBaseCurrency
	}
	newBaseCurrencyRates[newBase] = 1.0

	return New(newBase, newBaseCurrencyRates), nil
}

func (cr *CurrencyRates) ConvertFromTo(amount float64, from, to Currency) (result float64, err error) {
	fromCurrencyRates, err := cr.FromToAnotherBase(from)
	if err != nil {
		return result, err
	}

	result, err = fromCurrencyRates.AmountInCurrency(amount, to)
	if err != nil {
		return result, err
	}
	return result, nil
}

func (cr *CurrencyRates) AmountInCurrency(amount float64, currency Currency) (result float64, err error) {
	result, ok := cr.Rates[currency]
	if !ok {
		msg := fmt.Sprintf("Currency=%s not found", currency)
		return result, errors.Wrap(common.EntityNotFound, msg)
	}

	return amount * 1.0 / result, nil
}

const (
	RUB Currency = "RUB"
	USD Currency = "USD"
	EUR Currency = "EUR"
	CNY Currency = "CNY"
)

var currencies = map[string]Currency{
	"RUB": RUB,
	"USD": USD,
	"EUR": EUR,
	"CNY": CNY,
}

func GetCurrency(value string) (Currency, bool) {
	value = strings.ToUpper(value)
	currency, ok := currencies[value]
	return currency, ok
}

var currencyToSign = map[Currency]string{
	RUB: "₽",
	USD: "$",
	EUR: "€",
	CNY: "¥",
}

func GetCurrencySign(currency Currency) (string, bool) {
	sign, ok := currencyToSign[currency]
	return sign, ok
}
