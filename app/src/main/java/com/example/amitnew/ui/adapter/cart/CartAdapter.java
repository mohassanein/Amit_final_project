package com.example.amitnew.ui.adapter.cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amitnew.R;
import com.example.amitnew.data.model.cart.ProductsItem;
import com.example.amitnew.ui.adapter.category.CategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.CartHolder>{

    private List<ProductsItem>productsItems = new ArrayList<>();

    private Context context;


    public CartAdapter(Context context) {
        this.context = context;
    }

    public void setProductsItems(List<ProductsItem> productsItems) {
        this.productsItems = productsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);

        return new CartHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
            ProductsItem productsItem = productsItems.get(position);
            if(productsItem!=null){
                Glide.with(context).load(productsItem.getProduct().getAvatar()).into(holder.cartImage);
                holder.cartname.setText(productsItem.getProduct().getName());
                holder.cartdesc.setText(productsItem.getProduct().getTitle());
                holder.cartprice.setText(String.valueOf(productsItem.getTotal()+" "+productsItem.getProduct().getCurrency()));
                holder.cartcount.setText(String.valueOf(productsItem.getAmount()));



            }
    }

    @Override
    public int getItemCount() {
        return productsItems.size();
    }

    static class CartHolder extends RecyclerView.ViewHolder{
            ImageView cartImage;

            TextView  cartname,cartdesc,cartprice,cartcount;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            cartImage = itemView.findViewById(R.id.cart_image);
            cartname = itemView.findViewById(R.id.cart_product_name);
            cartdesc = itemView.findViewById(R.id.cart_product_desc);
            cartprice = itemView.findViewById(R.id.cart_price_final);
            cartcount = itemView.findViewById(R.id.cart_count);


        }
    }

}
