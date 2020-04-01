package com.example.sn34ker;

import android.content.Context;
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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
//    Fragment fragment;
    private ArrayList product_name, product_brand, product_img, product_price;
    Animation translate_anim;
    private static final String TAG = "Product Adapter";

    // TODO: img handle.
    public ProductAdapter(Context context, ArrayList product_name, ArrayList product_brand, ArrayList product_price) {
        this.context = context;
//        this.fragment = fragment;
        this.product_name = product_name;
        this.product_brand = product_brand;
//        this.product_img = product_img;
        this.product_price = product_price;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_sneaker, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.txtProductName.setText(String.valueOf(product_name.get(position)));
        holder.txtProductBrand.setText(String.valueOf(product_brand.get(position)));
        holder.txtProductPrice.setText(String.valueOf(product_price.get(position)));

//        Glide.with(context)
//                .asBitmap()
//                .load()

//        holder.productCirImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: clicked img. " + product_name.get(position));
//                Toast.makeText(context, "product: "+product_name.get(position) , Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return product_name.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CircleImageView productCirImg;
        TextView txtProductName, txtProductBrand, txtProductPrice;
        LinearLayout myRowLayout;

        public ProductViewHolder(View itemView){
            super(itemView);
//            productCirImg = itemView.findViewById(R.id.product_image);
            txtProductName = itemView.findViewById(R.id.product_name);
            txtProductBrand = itemView.findViewById(R.id.product_brand);
            txtProductPrice = itemView.findViewById(R.id.product_price);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.animation);
            myRowLayout = itemView.findViewById(R.id.productRowView);

        }
    }
}
