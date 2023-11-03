package com.sirius.challenge.backend.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sendinblue.ApiClient;
import sendinblue.auth.ApiKeyAuth;

@Configuration
public class BrevoConfig {

    @Value("${brevo.api.key}")
    private String apikey;

    @Bean
    public ApiClient defaultClient(){
        ApiClient defaultClient = sendinblue.Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(apikey);
        defaultClient.buildUrl("smtp/email",null,null);
        return defaultClient;
    }

}
