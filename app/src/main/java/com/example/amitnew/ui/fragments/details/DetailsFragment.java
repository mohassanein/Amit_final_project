package com.example.amitnew.ui.fragments.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amitnew.R;
import com.example.amitnew.data.model.cart.ProductsItem;
import com.example.amitnew.data.model.product.Product;
import com.example.amitnew.data.source.api.ApiManager;
import com.example.amitnew.helper.TokenManager;
import com.example.amitnew.ui.adapter.product.ProductClickListener;
import com.example.amitnew.ui.fragments.home.HomeViewModel;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {
    private HomeViewModel homeViewModel;
    Product product;

    ImageView image_details;
    TextView  item_details,title_details,price_final,product_count,desc_details;
    Button addToCart;
    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            product = (Product) getArguments().getSerializable("product");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       initRecycler(view);
        Glide.with(getContext()).load(product.getAvatar()).into(image_details);
        item_details.setText(product.getName());
        title_details.setText(product.getTitle());
        price_final.setText(String.valueOf(product.getPriceFinal()));
        desc_details.setText(product.getDescription());



        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Amount has been added to cart", Toast.LENGTH_SHORT).show();
            }
        });


    }

protected  void  initRecycler(View view){
    image_details = view.findViewById(R.id.image_details);
    item_details = view.findViewById(R.id.item_details);
    title_details = view.findViewById(R.id.title_details);
    price_final = view.findViewById(R.id.price_final);
    product_count = view.findViewById(R.id.product_count);
    desc_details = view.findViewById(R.id.desc_details);
    addToCart = view.findViewById(R.id.add_to_cart_btn);
}


}