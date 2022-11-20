package app

import (
	"context"
	helmet "github.com/danielkov/gin-helmet"
	"github.com/gin-contrib/cors"
	"github.com/gin-contrib/gzip"
	"github.com/gin-gonic/gin"
	currencyApi "gitlab.ozon.dev/kolya_cypandin/project-base/internal/client/currency"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/config/inmem_config"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/currency"
	currencyService "gitlab.ozon.dev/kolya_cypandin/project-base/internal/currency/service"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/route"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/util/file/yaml"
	"golang.org/x/sync/errgroup"
	"net/http"
	"time"
)

func Run(ctx context.Context, erg *errgroup.Group, server *http.Server) error {
	// FILE
	fileReader := yaml.New()

	// APPLICATION CONFIG
	configFilePath := "configs/application.yaml"
	configManager, err := inmem_config.NewConfigManager(fileReader, configFilePath)
	if err != nil {
		return err
	}

	currencyApiImpl := currencyApi.NewCurrencyApi()
	currencyServiceImpl := currencyService.New(currencyApiImpl)

	erg.Go(func() error {
		updateTickMs := configManager.CurrencyRatesUpdateTick()
		ticker := time.NewTicker(time.Duration(updateTickMs) * time.Millisecond)

		return currencyServiceImpl.ListenForUpdates(ctx, ticker)
	})

	router := SetupRouter(currencyServiceImpl)
	server.Handler = router.Handler()
	erg.Go(func() error {
		return server.ListenAndServe()
	})

	return nil
}

func SetupRouter(service currency.Service) *gin.Engine {
	/**
	@description Init Router
	*/
	router := gin.Default()
	/**
	@description Setup Mode Application
	*/
	gin.SetMode(gin.ReleaseMode)

	/**
	@description Setup Middleware
	*/
	router.Use(cors.New(cors.Config{
		AllowOrigins:  []string{"*"},
		AllowMethods:  []string{"*"},
		AllowHeaders:  []string{"*"},
		AllowWildcard: true,
	}))
	router.Use(helmet.Default())
	router.Use(gzip.Gzip(gzip.BestCompression))
	/**
	@description Init All Route
	*/
	route.InitCurrencyRoutes(service, router)

	return router
}
