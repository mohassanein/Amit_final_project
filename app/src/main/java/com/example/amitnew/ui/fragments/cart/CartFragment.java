package com.example.amitnew.ui.fragments.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.amitnew.R;
import com.example.amitnew.data.model.cart.ProductsItem;
import com.example.amitnew.data.model.product.Product;
import com.example.amitnew.helper.TokenManager;
import com.example.amitnew.ui.adapter.cart.CartAdapter;
import com.example.amitnew.ui.adapter.category.CategoriesAdapter;
import com.example.amitnew.ui.adapter.product.ProductClickListener;

import java.util.List;


public class CartFragment extends Fragment  {


    RecyclerView cart_recycler;
    RecyclerView.LayoutManager layoutManager;
    CartAdapter adapter;

    private CartViewModel cartViewModel;

    Button cart_clear;


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TokenManager tokenManager = new TokenManager(getContext());
        String token =   tokenManager.getToken();
        initRecycler(view);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartProducts(token);
        cartViewModel.getCartLiveData().observe(requireActivity(), new Observer<List<ProductsItem>>() {
            @Override
            public void onChanged(List<ProductsItem> productsItems) {
                adapter.setProductsItems(productsItems);

            }
        });

        cartViewModel.getMessageCartLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),""+s,Toast.LENGTH_LONG).show();
            }
        });
    cart_clear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(),"Cart cleared",Toast.LENGTH_SHORT).show();
        }
    });

    }

    protected void initRecycler(View v){

        cart_recycler= v.findViewById(R.id.cart_recycler_items);
        adapter = new CartAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        cart_recycler.setAdapter(adapter);
        cart_recycler.setLayoutManager(layoutManager);
        cart_clear = v.findViewById(R.id.clear_Cart);

    }





}