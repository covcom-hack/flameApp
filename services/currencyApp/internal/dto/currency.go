package dto

import "time"

type GetRequest struct {
	Name string `json:"name"`
}

type GetResponse struct {
	Name     string    `json:"name"`
	Currency float64   `json:"currency"`
	Time     time.Time `json:"time"`
}

type GetAllRequest struct {
	Name string `json:"name"`
}

type CurrencyResponse struct {
	Name   string  `json:"name"`
	Amount float64 `json:"amount"`
}

type GetAllResponse struct {
	Base       string             `json:"base"`
	Currencies []CurrencyResponse `json:"currencies"`
	Time       time.Time          `json:"time"`
}
