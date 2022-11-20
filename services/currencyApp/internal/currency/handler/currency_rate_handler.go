package handler

import (
	"github.com/gin-gonic/gin"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/currency"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/dto"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/util"
	"net/http"
)

const BaseCurrency = currency.RUB

type CurrencyHandler struct {
	service currency.Service
}

func NewHandlerCreateStudent(service currency.Service) *CurrencyHandler {
	return &CurrencyHandler{service: service}
}

func (h *CurrencyHandler) GetAllHandler(ctx *gin.Context) {
	var input dto.GetAllRequest
	_ = ctx.ShouldBindJSON(&input)

	currentBaseRates := h.service.GetCurrentCurrencyRates()
	newCurrency := currency.Currency(input.Name)
	rates, err := currentBaseRates.FromToAnotherBase(newCurrency)
	if err != nil {
		util.APIResponse(ctx, "Currency not found", http.StatusNotFound, http.MethodGet, nil)
		return
	}

	result := make([]dto.CurrencyResponse, len(rates.Rates))
	i := 0
	for k := range rates.Rates {
		amount, err := rates.ConvertFromTo(
			1.0,
			newCurrency,
			k,
		)
		if err != nil {
			util.APIResponse(ctx, "Currency not found", http.StatusNotFound, http.MethodGet, nil)
			return
		}

		result[i] = dto.CurrencyResponse{
			Name:   string(k),
			Amount: amount,
		}
		i++
	}

	util.APIResponse(
		ctx,
		"Success",
		http.StatusOK,
		http.MethodGet,
		dto.GetAllResponse{
			Base:       string(rates.Base),
			Currencies: result,
			Time:       rates.AtTime,
		},
	)
}

func (h *CurrencyHandler) GetHandler(ctx *gin.Context) {
	var input dto.GetRequest
	_ = ctx.ShouldBindJSON(&input)

	rates := h.service.GetCurrentCurrencyRates()
	currency, err := rates.ConvertFromTo(
		1.0,
		BaseCurrency,
		currency.Currency(input.Name),
	)
	if err != nil {
		util.APIResponse(ctx, "Can't convert", http.StatusInternalServerError, http.MethodGet, nil)
		return
	}

	util.APIResponse(
		ctx,
		"Success",
		http.StatusOK,
		http.MethodGet,
		dto.GetResponse{
			Name:     input.Name,
			Currency: currency,
			Time:     rates.AtTime,
		},
	)
}
