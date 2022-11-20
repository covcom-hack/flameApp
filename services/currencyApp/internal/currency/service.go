package currency

type Service interface {
	GetCurrentCurrencyRates() CurrencyRates
}
