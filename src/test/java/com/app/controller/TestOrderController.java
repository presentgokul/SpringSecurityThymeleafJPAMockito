package com.app.controller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import com.app.controller.OrderController;
import com.app.dao.ProductRepository;
import com.app.entities.Product;

public class TestOrderController {
	
	private MockMvc mockMvc;
	
	private OrderController orderController;
	
	private ProductRepository productRepository;
	
	
	@Before
	public void setup(){
		productRepository = Mockito.mock(ProductRepository.class);
		orderController = new OrderController(productRepository);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	
	@Test
	public void testIndex() throws Exception{
		when(productRepository.findAll()).thenReturn(Stream.of(new Product("IPad","Apple Product",1000,500,"","")).collect(Collectors.toList()));
		mockMvc.perform(get("/"))
			.andExpect(view().name("redirect:/index"));
	}
	
	@Test
	public void testAddProductError() throws Exception{
		mockMvc.perform(post("/addOffer"))
			.andExpect(view().name("add-offer"));
	}
	
	
	@Test
	public void testUpdate() throws Exception{
		Optional<Product> opProduct = Optional.ofNullable(new Product("IPad","Apple Product",1000,500,"",""));
		when(productRepository.findById(new Long(1))).thenReturn(opProduct);
		mockMvc.perform(get("/edit/1"))
			.andExpect(view().name("update-offer"));
	}
	
	@Test
	public void testUpdateProductError() throws Exception{
		mockMvc.perform(post("/update/1"))
			.andExpect(view().name("update-offer"));
	}
	
	@Test
	public void testDelete() throws Exception{
		Optional<Product> opProduct = Optional.ofNullable(new Product("IPad","Apple Product",1000,500,"",""));
		when(productRepository.findById(new Long(1))).thenReturn(opProduct);
		mockMvc.perform(get("/delete/1"))
			.andExpect(view().name("redirect:/index"));
	}
	
	@Test
	public void testStatus() throws Exception{
		Product product = new Product("IPad","Apple Product",1000,500,"24/12/2019 16:11:12","24/12/2200 16:11:12");
		product.updateOffer();
		assertTrue(product.getStatus());
		product.setOfferexpirytime("24/12/2000 16:11:12");
		product.updateOffer();
		assertFalse(product.getStatus());
	}

}
