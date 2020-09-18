package com.app.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.dao.ProductRepository;
import com.app.entities.Product;

@Controller
public class OrderController {
	@GetMapping("/")
	public String getProducts(Model model) {
		 model.addAttribute("products", productRepository.findAll());
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String listOffers(Model model) {
		 model.addAttribute("products", productRepository.findAll());
		return "index";
	}
	
	private final ProductRepository productRepository;

    @Autowired
    public OrderController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @GetMapping("/offer")
    public String showOfferForm(Product product, Model model) {
    	model.addAttribute("product", new Product());
        return "add-offer";
    }
    
    @PostMapping("/addOffer")
    public String addUser(@Valid @ModelAttribute("product") Product product, Errors errors, Model model) {
    	 if (null != errors && errors.getErrorCount() > 0) {
             return "add-offer";
         } else {
        	 product.updateOffer();
        	 productRepository.save(product);
        	 model.addAttribute("products", productRepository.findAll());
             model.addAttribute("successMsg", "Details saved successfully!!");
             return "redirect:/index";
         }
    }

	@GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("product", product);
        return "update-offer";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	product.setId(id);
            return "update-offer";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/index";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    	Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    	productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll());
        return "redirect:/index";
    }

}
