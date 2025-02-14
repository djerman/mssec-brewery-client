package rs.services.mssc_brewery_client.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import rs.services.mssc_brewery_client.web.model.BeerDto;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields =  false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    private String apihost;
    
    private final RestTemplate restTemplate;
    
    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
	this.restTemplate = restTemplateBuilder.build();
    }
    
    public BeerDto getBeerDto(UUID uuid) {
	return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }
    
    public URI saveNewBeer(BeerDto beerDto) {
	return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }
    
    public void updateBeer(UUID uuid, BeerDto beerDto) {
	restTemplate.put(apihost + BEER_PATH_V1 + uuid.toString(), beerDto);
    }
    
    public void deleteBeer(UUID uuid) {
	restTemplate.delete(apihost + BEER_PATH_V1 + uuid);
    }
    
    /**
     * potrebno 
     * @param apihost
     */
    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
    
}
