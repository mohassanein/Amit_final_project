package com.example.amitnew.ui.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amitnew.data.model.product.Product;
import com.example.amitnew.helper.TokenManager;
import com.example.amitnew.ui.adapter.product.ProductAdapter;
import com.example.amitnew.R;
import com.example.amitnew.ui.adapter.product.ProductClickListener;

import java.util.List;


public class HomeFragment extends Fragment implements ProductClickListener {

 RecyclerView productRecycler;
 RecyclerView.LayoutManager layoutManager;
 ProductAdapter adapter;
// viewmodel
    private  HomeViewModel homeViewModel;

    //Navigation Controller
    private NavController navController;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
            initRecycler(view);
            homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
            homeViewModel.getProducts();
            homeViewModel.getProductsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    adapter.setProductList(products);
                }
            });

            homeViewModel.getMessageLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Toast.makeText(getActivity(),""+s,Toast.LENGTH_LONG).show();
                }
            });

            homeViewModel.getAddCartResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Toast.makeText(getContext(),""+s,Toast.LENGTH_LONG).show();
                }
            });

            homeViewModel.getMessageAddCartLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Toast.makeText(getContext(),""+s,Toast.LENGTH_LONG).show();
                }
            });

    }

        protected void initRecycler(View v){

        productRecycler= v.findViewById(R.id.product_recyclerView);
        adapter = new ProductAdapter(getContext(),this);
        layoutManager = new GridLayoutManager(getContext(),2);
        productRecycler.setAdapter(adapter);
        productRecycler.setLayoutManager(layoutManager);

        }


    @Override
    public void showProductDetails(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",product);
    navController.navigate(R.id.action_homeFragment_to_detailsFragment,bundle);

    }

    @Override
    public void addProductsToCart(Product product) {
    TokenManager tokenManager = new TokenManager(getContext());
    String token =   tokenManager.getToken();

    homeViewModel.addProductsToCart(product.getId(),token,1);


    }
}

