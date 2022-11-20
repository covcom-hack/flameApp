package service

import (
	"context"
	"encoding/json"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/currency"
	"math/rand"
	"time"

	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/client"
	"gitlab.ozon.dev/kolya_cypandin/project-base/pkg/logger"
)

type CurrencyRatesStorage struct {
	requestService client.ApiRequestService
	currencyRates  currency.CurrencyRates
}

func New(requestService client.ApiRequestService) *CurrencyRatesStorage {
	return &CurrencyRatesStorage{
		requestService: requestService,
	}
}

func (crs *CurrencyRatesStorage) GetCurrentCurrencyRates() (currencyRates currency.CurrencyRates) {
	return crs.currencyRates
}

func (crs *CurrencyRatesStorage) ListenForUpdates(ctx context.Context, ticker *time.Ticker) error {
	logger.Info(ctx).Msg("Initializing currency rates...")
	err := crs.updateCurrencyRates()
	if err != nil {
		logger.Error(ctx).Err(err).Msg("Got error while currency rates initialization")
		return err
	}

	logger.Info(ctx).Msg("Listening for currency rates updates...")
	for {
		select {
		case <-ctx.Done():
			logger.Debug(ctx).Msg("domain.currency.service.ListenForUpdates context done")
			return ctx.Err()
		case <-ticker.C:
			select {
			case <-ctx.Done():
				logger.Debug(ctx).Msg("domain.currency.service.ListenForUpdates context done")
				return ctx.Err()
			default:
				logger.Debug(ctx).Msg("Regular tick, will update currency rates...")
				err = crs.updateCurrencyRates()
				if err != nil {
					logger.Error(ctx).Err(err).Msg("Got error while updating currency rates, will be ignored")
				}
				logger.Debug(ctx).Msg("Currency rates successfully updated after regular tick")
			}
		}
	}
}

func (crs *CurrencyRatesStorage) updateCurrencyRates() (err error) {
	responseBody, err := crs.requestService.Request()
	if err != nil {
		return err
	}

	rates := currency.CurrencyRates{}
	err = json.NewDecoder(responseBody).Decode(&rates)
	rates.AtTime = time.Now()
	if err != nil {
		return err
	}
	rates.Rates[rates.Base] = 1.0

	crs.currencyRates = addDeltas(rates)
	return nil
}

func addDeltas(rates currency.CurrencyRates) currency.CurrencyRates {
	for cur, _ := range rates.Rates {
		delta := rates.Rates[cur] * (rand.Float64() - 0.5) / 40
		rates.Rates[cur] = rates.Rates[cur] + delta
	}
	rates.Rates[rates.Base] = 1.0
	return rates
}
