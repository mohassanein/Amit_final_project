package com.example.amitnew.data.model.product;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProudctsResponse{

	@SerializedName("products")
	private List<Product> products;

	public List<Product> getProducts(){
		return products;
	}
}