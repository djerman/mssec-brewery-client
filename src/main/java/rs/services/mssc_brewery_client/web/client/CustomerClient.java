package rs.services.mssc_brewery_client.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.services.mssc_brewery_client.web.model.CustomerDto;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields =  false)
public class CustomerClient {

    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    
    private final RestTemplate restTemplate;
    
    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
	this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerDto(UUID uuid) {
	return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
    }
    
    public URI saveNewCustomer(CustomerDto customerDto) {
	return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }
    
    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
	restTemplate.put(apihost + CUSTOMER_PATH_V1 + uuid, customerDto);
    }
    
    public void deleteBeer(UUID uuid) {
	restTemplate.delete(apihost + CUSTOMER_PATH_V1 + uuid);
    }

    /**
     * potrebno 
     * @param apihost
     */
    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
    
    
}
