/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.automl.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.automl.v1.AutoMlClient;
import com.google.cloud.automl.v1.AutoMlSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link AutoMlClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(AutoMlClient.class)
@ConditionalOnProperty(value = "com.google.cloud.automl.v1.auto-ml.enabled", matchIfMissing = true)
@EnableConfigurationProperties(AutoMlSpringProperties.class)
public class AutoMlSpringAutoConfiguration {
  private final AutoMlSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(AutoMlSpringAutoConfiguration.class);

  protected AutoMlSpringAutoConfiguration(
      AutoMlSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from AutoMl-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean. The default is gRPC and will default to it
   * unless the useRest option is supported and provided to use HTTP transport instead
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultAutoMlTransportChannelProvider")
  public TransportChannelProvider defaultAutoMlTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return AutoMlSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return AutoMlSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a AutoMlSettings bean configured to use a DefaultCredentialsProvider and the client
   * library's default transport channel provider (defaultAutoMlTransportChannelProvider()). It also
   * configures the quota project ID and executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in AutoMlSpringProperties. Method-level properties will take precedence over service-level
   * properties if available, and client library defaults will be used if neither are specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link AutoMlSettings} bean configured with {@link TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public AutoMlSettings autoMlSettings(
      @Qualifier("defaultAutoMlTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    AutoMlSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = AutoMlSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = AutoMlSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          AutoMlSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings getDatasetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDatasetSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getDatasetSettings().setRetrySettings(getDatasetRetrySettings);

      RetrySettings listDatasetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDatasetsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listDatasetsSettings().setRetrySettings(listDatasetsRetrySettings);

      RetrySettings updateDatasetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateDatasetSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateDatasetSettings().setRetrySettings(updateDatasetRetrySettings);

      RetrySettings getAnnotationSpecRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAnnotationSpecSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getAnnotationSpecSettings()
          .setRetrySettings(getAnnotationSpecRetrySettings);

      RetrySettings getModelRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getModelSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getModelSettings().setRetrySettings(getModelRetrySettings);

      RetrySettings listModelsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listModelsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listModelsSettings().setRetrySettings(listModelsRetrySettings);

      RetrySettings updateModelRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateModelSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateModelSettings().setRetrySettings(updateModelRetrySettings);

      RetrySettings getModelEvaluationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getModelEvaluationSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getModelEvaluationSettings()
          .setRetrySettings(getModelEvaluationRetrySettings);

      RetrySettings listModelEvaluationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listModelEvaluationsSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listModelEvaluationsSettings()
          .setRetrySettings(listModelEvaluationsRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry getDatasetRetry = clientProperties.getGetDatasetRetry();
    if (getDatasetRetry != null) {
      RetrySettings getDatasetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDatasetSettings().getRetrySettings(), getDatasetRetry);
      clientSettingsBuilder.getDatasetSettings().setRetrySettings(getDatasetRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getDataset from properties.");
      }
    }
    Retry listDatasetsRetry = clientProperties.getListDatasetsRetry();
    if (listDatasetsRetry != null) {
      RetrySettings listDatasetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDatasetsSettings().getRetrySettings(), listDatasetsRetry);
      clientSettingsBuilder.listDatasetsSettings().setRetrySettings(listDatasetsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listDatasets from properties.");
      }
    }
    Retry updateDatasetRetry = clientProperties.getUpdateDatasetRetry();
    if (updateDatasetRetry != null) {
      RetrySettings updateDatasetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateDatasetSettings().getRetrySettings(), updateDatasetRetry);
      clientSettingsBuilder.updateDatasetSettings().setRetrySettings(updateDatasetRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateDataset from properties.");
      }
    }
    Retry getAnnotationSpecRetry = clientProperties.getGetAnnotationSpecRetry();
    if (getAnnotationSpecRetry != null) {
      RetrySettings getAnnotationSpecRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAnnotationSpecSettings().getRetrySettings(),
              getAnnotationSpecRetry);
      clientSettingsBuilder
          .getAnnotationSpecSettings()
          .setRetrySettings(getAnnotationSpecRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getAnnotationSpec from properties.");
      }
    }
    Retry getModelRetry = clientProperties.getGetModelRetry();
    if (getModelRetry != null) {
      RetrySettings getModelRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getModelSettings().getRetrySettings(), getModelRetry);
      clientSettingsBuilder.getModelSettings().setRetrySettings(getModelRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getModel from properties.");
      }
    }
    Retry listModelsRetry = clientProperties.getListModelsRetry();
    if (listModelsRetry != null) {
      RetrySettings listModelsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listModelsSettings().getRetrySettings(), listModelsRetry);
      clientSettingsBuilder.listModelsSettings().setRetrySettings(listModelsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listModels from properties.");
      }
    }
    Retry updateModelRetry = clientProperties.getUpdateModelRetry();
    if (updateModelRetry != null) {
      RetrySettings updateModelRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateModelSettings().getRetrySettings(), updateModelRetry);
      clientSettingsBuilder.updateModelSettings().setRetrySettings(updateModelRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateModel from properties.");
      }
    }
    Retry getModelEvaluationRetry = clientProperties.getGetModelEvaluationRetry();
    if (getModelEvaluationRetry != null) {
      RetrySettings getModelEvaluationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getModelEvaluationSettings().getRetrySettings(),
              getModelEvaluationRetry);
      clientSettingsBuilder
          .getModelEvaluationSettings()
          .setRetrySettings(getModelEvaluationRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getModelEvaluation from properties.");
      }
    }
    Retry listModelEvaluationsRetry = clientProperties.getListModelEvaluationsRetry();
    if (listModelEvaluationsRetry != null) {
      RetrySettings listModelEvaluationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listModelEvaluationsSettings().getRetrySettings(),
              listModelEvaluationsRetry);
      clientSettingsBuilder
          .listModelEvaluationsSettings()
          .setRetrySettings(listModelEvaluationsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listModelEvaluations from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a AutoMlClient bean configured with AutoMlSettings.
   *
   * @param autoMlSettings settings to configure an instance of client bean.
   * @return a {@link AutoMlClient} bean configured with {@link AutoMlSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public AutoMlClient autoMlClient(AutoMlSettings autoMlSettings) throws IOException {
    return AutoMlClient.create(autoMlSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-auto-ml";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
