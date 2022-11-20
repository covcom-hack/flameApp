package route

import (
	"github.com/gin-gonic/gin"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/currency"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/currency/handler"
)

func InitCurrencyRoutes(service currency.Service, route *gin.Engine) {

	/**
	@description All Handlers currency
	*/
	currencyRateHandler := handler.NewHandlerCreateStudent(service)

	/**
	@description All Student Route
	*/
	groupRoute := route.Group("api/v1")
	groupRoute.GET("/currency", currencyRateHandler.GetHandler)
	groupRoute.GET("/currency/all", currencyRateHandler.GetAllHandler)
}
