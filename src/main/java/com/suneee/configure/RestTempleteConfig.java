package com.suneee.configure;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempleteConfig {

    @Value("${http.client.connectionRequestTimout:1000}")
    private int connectionRequestTimout;

    @Value("${http.client.connectTimeout:1000}")
    private int connectTimeout;

    @Value("${http.client.readTimeout:1000}")
    private int readTimeout;

    @Value("${http.client.maxTotle:50}")
    private int maxTotle;

    @Value("${http.client.maxPerRoute:50}")
    private int maxPerRoute;

    @Bean
    public RestTemplate customRestTemplate(RestTemplateBuilder builder)
    {
        RestTemplate restTemplate = builder.build();
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public HttpClientConnectionManager poolingConnectionManager() {
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        poolingConnectionManager.setMaxTotal(this.maxTotle);
        poolingConnectionManager.setDefaultMaxPerRoute(this.maxPerRoute);
        return poolingConnectionManager;
    }
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        clientHttpRequestFactory.setConnectionRequestTimeout(this.connectionRequestTimout);
        clientHttpRequestFactory.setConnectTimeout(this.connectTimeout);
        clientHttpRequestFactory.setReadTimeout(this.readTimeout);
        return clientHttpRequestFactory;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setConnectionManager(poolingConnectionManager());
        return httpClientBuilder;
    }


}
