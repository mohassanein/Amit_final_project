package com.example.amitnew.ui.fragments.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amitnew.data.model.cart.ProductsItem;
import com.example.amitnew.data.reprository.CartReprository;

import java.util.List;

public class CartViewModel extends ViewModel {

    private LiveData<List<ProductsItem>>CartLiveData ;
    private LiveData<String>messageCartLiveData;

    public LiveData<List<ProductsItem>> getCartLiveData() {
        return CartLiveData;
    }

    public LiveData<String> getMessageCartLiveData() {
        return messageCartLiveData;
    }

    private CartReprository cartReprository = new CartReprository();

    public void getCartProducts(String token){
        cartReprository.Cartproducts(token);
     CartLiveData = cartReprository.getCartLiveData();
     messageCartLiveData = cartReprository.getMessageCartLiveData();
    }



}
