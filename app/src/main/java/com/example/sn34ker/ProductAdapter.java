package com.example.sn34ker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sn34ker.datamodels.ProductModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static final String TAG = "Product Adapter";

    private ArrayList<ProductModel> productModelList;

    public ProductAdapter(ArrayList<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_sneaker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel productModel = productModelList.get(position);
        holder.txtProductName.setText(productModel.getName());
        holder.txtProductBrand.setText(productModel.getBrand());
        holder.txtProductPrice.setText(String.valueOf(productModel.getCA_price()));
        holder.productCirImg.setImageBitmap(productModel.getProduct_image());
    }

    @Override
    public int getItemCount() {

        return null != productModelList ? productModelList.size(): 0;
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        CircleImageView productCirImg;
        TextView txtProductName, txtProductBrand, txtProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productCirImg = itemView.findViewById(R.id.product_image);
            txtProductName = itemView.findViewById(R.id.product_name);
            txtProductBrand = itemView.findViewById(R.id.product_brand);
            txtProductPrice = itemView.findViewById(R.id.product_price);
        }
    }

}
