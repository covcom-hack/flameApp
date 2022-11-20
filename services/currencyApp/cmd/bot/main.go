package main

import (
	"context"
	"github.com/pkg/errors"
	"gitlab.ozon.dev/kolya_cypandin/project-base/internal/app"
	"gitlab.ozon.dev/kolya_cypandin/project-base/pkg/logger"
	"golang.org/x/sync/errgroup"
	"net/http"
	"os"
	"os/signal"
	"syscall"
)

func main() {
	// CONTEXT, ERRG
	ctxWithCancel, cancel := context.WithCancel(context.Background())
	erg, ctx := errgroup.WithContext(ctxWithCancel)
	logger.Info(ctx).Msg("Starts main")

	// GRACEFUL SHUTDOWN
	erg.Go(func() error {
		signalsListenChan := make(chan os.Signal, 1)
		signal.Notify(signalsListenChan, syscall.SIGINT, syscall.SIGTERM)

		select {
		case sig := <-signalsListenChan:
			logger.Warn(ctx).Msgf("Received signal: %s, context will be cancelled\n", sig)
			cancel()
		case <-ctx.Done():
			logger.Debug(ctx).Msg("cmd.bot.main context done")
			return ctx.Err()
		}

		return nil
	})

	// RUN
	var server = &http.Server{Addr: ":8080", Handler: nil}
	erg.Go(func() error {
		return app.Run(ctx, erg, server)
	})

	// ERRORS
	err := erg.Wait()
	if err != nil {
		_ = server.Shutdown(ctx)
		if errors.Is(err, context.Canceled) {
			logger.Warn(ctx).Err(err).Msg("Context was cancelled")
		} else {
			logger.Error(ctx).Err(err).Msg("Received error while application runtime")
		}
	} else {
		logger.Debug(ctx).Msg("Application finished gracefully")
	}
}
