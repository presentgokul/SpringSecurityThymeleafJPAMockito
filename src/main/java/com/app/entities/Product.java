package com.app.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Description is mandatory")
	private String description;
	
	@NotNull(message = "Price is mandatory")
	@Min(value = 0, message = "Wrong Price, Please Correct them")
	private Integer price;

	@NotNull(message = "Offer Price is mandatory")
	@Min(value = 0, message = "Wrong Offer Price, Please Correct them")
	private Integer offerprice;

	@NotNull(message = "Please enter offerstarttime")
	private String  offerstarttime;

	@NotNull(message = "Please enter offerexpirytime")
	private String  offerexpirytime;
	
	private boolean status;
	
	private Integer onOfferPrice;
    
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Product() {
	}
	
	public Product(String name, String description, Integer price, Integer offerprice, String offerstarttime, String offerexpirytime) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.offerprice = offerprice;
		this.offerstarttime = offerstarttime;
		this.offerexpirytime = offerexpirytime;
	}

	public Product(String name) {
		this.name = name;
	}
    
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getOfferprice() {
		return offerprice;
	}

	public void setOfferprice(Integer offerprice) {
		this.offerprice = offerprice;
	}

	public String getOfferstarttime() {
		return offerstarttime;
	}

	public void setOfferstarttime(String offertarttime) {
		this.offerstarttime = offertarttime;
	}

	public String getOfferexpirytime() {
		return offerexpirytime;
	}

	public void setOfferexpirytime(String offerexpirytime) {
		this.offerexpirytime = offerexpirytime;;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", offerexpirytime=" + offerexpirytime+ ", status=" + status + '}';
    }
	
	public void updateOffer() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateExpiry;
		try {
			dateExpiry = sdf.parse(getOfferexpirytime());

			if (dateExpiry.before(new Date())) {
				setStatus(false);
			} else {
				setStatus(true);
			}
			setOnOfferPrice(getPrice() - getOfferprice());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Integer getOnOfferPrice() {
		return onOfferPrice;
	}

	public void setOnOfferPrice(Integer onOfferPrice) {
		this.onOfferPrice = onOfferPrice;
	}

}