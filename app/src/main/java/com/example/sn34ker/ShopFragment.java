package com.example.sn34ker;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    DataBaseHelper db;
    RecyclerView product_recyclerView;
    ArrayList<String> product_name, product_brand, product_price;
    ProductAdapter productAdapter;
    private static final String TAG = "SHOP FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_shop, container, false);

        product_recyclerView = rootView.findViewById(R.id.recycleView_Product);
        db = new DataBaseHelper(getActivity());
        product_name = new ArrayList<>();
        product_brand = new ArrayList<>();
        product_price = new ArrayList<>();

        storeProductDataInArrays();
        productAdapter = new ProductAdapter(getActivity(), product_name, product_brand, product_price);
        product_recyclerView.setAdapter(productAdapter);
        Log.e(TAG,"ERROR OCCUR");
        product_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));



       return rootView;

    }
    public void storeProductDataInArrays(){
        Log.d(TAG, "I'm in the storeProduct method.");
        Cursor cursor = db.readAllProductData();
        try{
            //TODO: make null point.
            if(cursor.getCount()==0){
                Toast.makeText(getActivity(), "Nothing on the list", Toast.LENGTH_SHORT).show();
            }
            //if there are data in the Product table.
            else {
                Log.d(TAG, "Adding data smoothly");
                while(cursor.moveToNext()){
                    //column index
                    product_name.add(cursor.getString(1));
                    product_brand.add(cursor.getString(2));
                    product_price.add(cursor.getString(4));
                    Log.d(TAG, "Product Name: " + product_name);
                }
            }


        }catch(Exception ex){
            Toast.makeText(getActivity(), "Something went wrong." + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
