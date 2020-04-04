package com.example.sn34ker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sn34ker.datamodels.ProductModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Dialog myDialog;

    private static final String TAG = "Product Adapter";

    private ArrayList<ProductModel> productModelList;
    ProductModel productModel;
    int cc;


    public ProductAdapter(ArrayList<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.single_sneaker, parent, false);
        final ProductViewHolder vHolder=new ProductViewHolder(view);

        myDialog=new Dialog(parent.getContext());
        myDialog.setContentView(R.layout.layout_popup);


        vHolder.myRowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productModel = productModelList.get(vHolder.getAdapterPosition());
                //cc=vHolder.getAdapterPosition();
                Toast.makeText(parent.getContext(), " "+vHolder.getAdapterPosition(), Toast.LENGTH_LONG).show();
                final TextView productName= myDialog.findViewById(R.id.product_Name_popup);
                final TextView productBrand= myDialog.findViewById(R.id.product_Brand_popup);
                final TextView productPrice= myDialog.findViewById(R.id.product_Price_popup);
                final TextView productSize= myDialog.findViewById(R.id.product_Size_popup);
                final ImageView productImage=myDialog.findViewById(R.id.product_image_popup);
                Button buyButton = myDialog.findViewById(R.id.buyButton);
                productName.setText(productModel.getName());
                productBrand.setText(productModel.getBrand());
                productPrice.setText(String.valueOf(productModel.getCA_price()));
                productSize.setText("SIZE : "+productModel.getUS_Size());
                productImage.setImageBitmap(productModel.getProduct_image());
                TextView txtClose=(TextView) myDialog.findViewById(R.id.closeText);
                txtClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                buyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = productName.getText().toString();
                        String brand = productBrand.getText().toString();
                        Double size = productModel.getUS_Size();
                        String proSize=""+size;
                        String price = productPrice.getText().toString();
                        int productId=productModel.getId();
                        String proId=""+productId;
                        //Toast.makeText(parent.getContext(), ""+productId, Toast.LENGTH_SHORT).show();

                        Intent orderPage = new Intent(parent.getContext(), com.example.sn34ker.OrderPage.class);
                        orderPage.putExtra("PRODUCTID",proId);
                        orderPage.putExtra("NAME", name);
                        orderPage.putExtra("BRAND", brand);
                        orderPage.putExtra("SIZE", proSize);
                        orderPage.putExtra("PRICE", price);
                        parent.getContext().startActivity(orderPage);
                    }
                });

                myDialog.show();


            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        productModel = productModelList.get(position);
        holder.txtProductName.setText(productModel.getName());
        holder.txtProductBrand.setText(productModel.getBrand());
        holder.txtProductPrice.setText(String.valueOf(productModel.getCA_price()));
        holder.productCirImg.setImageBitmap(productModel.getProduct_image());
        //holder.productName.setText(productModel.getName());
        //holder.productBrand.setText(productModel.getBrand());
    }

    @Override
    public int getItemCount() {

        return null != productModelList ? productModelList.size(): 0;
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        Dialog myDialog;

        CircleImageView productCirImg;
        TextView txtProductName, txtProductBrand, txtProductPrice;
        LinearLayout myRowLayout;
        //TextView productName,productBrand;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productCirImg = itemView.findViewById(R.id.product_image);
            txtProductName = itemView.findViewById(R.id.product_name);
            txtProductBrand = itemView.findViewById(R.id.product_brand);
            txtProductPrice = itemView.findViewById(R.id.product_price);
            myRowLayout=itemView.findViewById(R.id.productRowView);
            //productName= myDialog.findViewById(R.id.product_Name_popup);
           // productBrand= myDialog.findViewById(R.id.product_Brand_popup);
            //productImage=myDialog.findViewById(R.id.product_image_popup);
        }
    }

}
