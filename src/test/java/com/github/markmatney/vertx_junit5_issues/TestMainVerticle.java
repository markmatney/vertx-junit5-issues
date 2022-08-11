package com.github.markmatney.vertx_junit5_issues;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxParameterProvider;
import io.vertx.junit5.VertxTestContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

  @BeforeEach
  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  void verticle_deployed(Vertx vertx, VertxTestContext testContext) throws Throwable {
    testContext.completeNow();
  }

  @Test
  void vertx_parameter_filename(Vertx vertx, VertxTestContext testContext) throws Throwable {
    String filename = System.getenv(VertxParameterProvider.VERTX_PARAMETER_FILENAME);

    assertNotNull(filename);

    testContext.completeNow();
  }

  @Test
  void vertx_parameter_filename_portable(Vertx vertx, VertxTestContext testContext) throws Throwable {
    String portableVariableName = VertxParameterProvider.VERTX_PARAMETER_FILENAME.replace('.', '_').toUpperCase();
    String filename = System.getenv(portableVariableName);

    assertNotNull(filename);

    testContext.completeNow();
  }
}
