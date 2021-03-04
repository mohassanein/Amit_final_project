package com.example.amitnew.ui.adapter.product;

import com.example.amitnew.data.model.product.Product;

public interface ProductClickListener {
    void showProductDetails(Product product);
    void addProductsToCart(Product product);
}
