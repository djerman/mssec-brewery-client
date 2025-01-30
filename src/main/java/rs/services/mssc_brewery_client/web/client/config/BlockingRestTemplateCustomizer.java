package rs.services.mssc_brewery_client.web.client.config;

import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotalConnections;
    private final Integer defaultMaxPerRoute;
    private final Long connectionRequestTimeOut;
    
    public BlockingRestTemplateCustomizer(@Value("${sfg.maxtotalconnections}") Integer maxTotalConnections,
	    @Value("${sfg.defaultmaxperroute}")Integer defaultMaxPerRoute,
	    @Value("${sfg.connectionrequesttimeout}")Long connectionRequestTimeOut) {
	this.maxTotalConnections = maxTotalConnections;
	this.defaultMaxPerRoute = defaultMaxPerRoute;
	this.connectionRequestTimeOut = connectionRequestTimeOut;
    }
    
    public ClientHttpRequestFactory clientHttpRequestFactory() {
	PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	connectionManager.setMaxTotal(maxTotalConnections);
	connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
	
	RequestConfig requestConfig = RequestConfig
		.custom()
		.setConnectionRequestTimeout(connectionRequestTimeOut, TimeUnit.MILLISECONDS)
		.build();
	

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
    
    @Override
    public void customize(RestTemplate restTemplate) {
	restTemplate.setRequestFactory(clientHttpRequestFactory());
    }

}
