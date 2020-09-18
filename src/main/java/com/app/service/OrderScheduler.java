package com.app.service;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.dao.ProductRepository;
import com.app.entities.Product;

@Component
public class OrderScheduler {

	private final ProductRepository productRepository;

	@Autowired
	public OrderScheduler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Scheduled(cron = "0/10 * * * * *")
	public void cronJobSch() throws ParseException {
		for (Product product : productRepository.findAll()) {
			 product.updateOffer();
			 productRepository.save(product);
		}
	}
}


