package rs.services.mssc_brewery_client.web.client;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import rs.services.mssc_brewery_client.web.model.BeerDto;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;
    
    @Test
    void testGetBeerDto() {
	BeerDto dto = client.getBeerDto(UUID.randomUUID());
	assertNotNull(dto);
    }

    @Test
    void testSaveNewBeer() {
	BeerDto beerDto = BeerDto.builder().beerName("New beer").build();
	
	URI uri = client.saveNewBeer(beerDto);
	assertNotNull(uri);
	
	System.out.println(uri);
    }
    
    @Test
    void testUpdateBeer() {
	BeerDto beerDto = BeerDto.builder().beerName("New beer").build();
	
	client.updateBeer(UUID.randomUUID(), beerDto);
    }
    
    @Test
    void deleteBeer() {
	client.deleteBeer(UUID.randomUUID());
    }
    
}
