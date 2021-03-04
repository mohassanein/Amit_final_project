package com.example.amitnew.ui.adapter.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amitnew.R;
import com.example.amitnew.data.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.productHolder> {
    //private List<ProductModel> products;
    private List<Product> productList = new ArrayList<>();

    private Context context;

    private ProductClickListener productClickListener;

    public ProductAdapter(Context context , ProductClickListener productClickListener) {
        this.context = context;
        this.productClickListener = productClickListener;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public productHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_items,parent,false);

        return new productHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull productHolder holder, int position) {

        Product product = productList.get(position);
        if (product!= null) {
            Glide.with(context).load(product.getAvatar()).into(holder.productImage);
            holder.productName.setText(product.getTitle());
            holder.productTitle.setText(product.getName());
            holder.productPrice.setText(String.valueOf(product.getPriceFinal()+" "+product.getCurrency()));
        }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productClickListener.showProductDetails(product);

                }
            });

            holder.addProductToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productClickListener.addProductsToCart(product);
                }
            });



        }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class productHolder extends RecyclerView.ViewHolder {

     ImageView productImage;
     TextView  productName,productTitle,productPrice;
     ImageButton addProductToCart;

     public productHolder(@NonNull View itemView) {
         super(itemView);

         productImage= itemView.findViewById(R.id.product_image);
         productName= itemView.findViewById(R.id.product_name);
         productTitle= itemView.findViewById(R.id.product_desc);
         productPrice= itemView.findViewById(R.id.product_price);
        addProductToCart = itemView.findViewById(R.id.add_product_to_cart_btn);
     }
 }
}
