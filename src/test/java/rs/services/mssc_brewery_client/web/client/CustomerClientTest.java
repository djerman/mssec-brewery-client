package rs.services.mssc_brewery_client.web.client;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import rs.services.mssc_brewery_client.web.model.CustomerDto;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;
    
    @Test
    void testGetCustomerDto() {
	CustomerDto dto = client.getCustomerDto(UUID.randomUUID());
	assertNotNull(dto);
    }

    @Test
    void testSaveNewCustomer() {
	CustomerDto customerDto = CustomerDto.builder().name("Test Customer").build();
	
	URI uri = client.saveNewCustomer(customerDto);
	assertNotNull(uri);
	
	System.out.println(uri);
    }

    @Test
    void testUpdateCustomer() {
	CustomerDto customerDto = CustomerDto.builder().name("Test Customer").build();
	
	client.updateCustomer(UUID.randomUUID(), customerDto);
    }

    @Test
    void testDeleteBeer() {
	client.deleteBeer(UUID.randomUUID());
    }

}
