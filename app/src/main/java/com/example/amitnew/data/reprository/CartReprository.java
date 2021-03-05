package com.example.amitnew.data.reprository;

import androidx.lifecycle.MutableLiveData;

import com.example.amitnew.data.model.AddCartResponse;
import com.example.amitnew.data.model.cart.CartResponse;
import com.example.amitnew.data.model.cart.ProductsItem;
import com.example.amitnew.data.source.api.ApiManager;
import com.example.amitnew.helper.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartReprository {

   private MutableLiveData<List<ProductsItem>>CartLiveData;
    private MutableLiveData<String>messageCartLiveData;

    public CartReprository() {
    CartLiveData = new MutableLiveData<>();
    messageCartLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<ProductsItem>> getCartLiveData() {
        return CartLiveData;
    }

    public MutableLiveData<String> getMessageCartLiveData() {
        return messageCartLiveData;
    }

    public void Cartproducts(String token){

        ApiManager.getProductService().CartProducts(token).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                CartLiveData.setValue(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                messageCartLiveData.setValue(t.getLocalizedMessage());
            }
        });



    }
}
