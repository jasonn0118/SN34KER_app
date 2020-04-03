package com.example.sn34ker;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopFragment extends Fragment {
    DataBaseHelper dbHelper;
    RecyclerView product_recyclerView,nike_recycleView,adidas_recycleView;
    ProductAdapter productAdapter;
    private static final String TAG = "SHOP FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            View rootView = inflater.inflate(R.layout.fragment_shop, container, false);

            product_recyclerView = rootView.findViewById(R.id.recycleView_Product);
            dbHelper = new DataBaseHelper(getActivity());
            nike_recycleView=rootView.findViewById(R.id.recycleView_nike);
            adidas_recycleView=rootView.findViewById(R.id.recycleView_Adidas);

            //Get all Product data from db.
            getData(rootView);
            getNike(rootView);
            getAdidas(rootView);


            return rootView;
        }catch(Exception ex){
            Log.d("SHOP FRAGMENT", ex.getMessage());
            return null;
        }
    }

    private void getAdidas(View rootView) {
        try {
            productAdapter = new ProductAdapter(dbHelper.getAllAdidasData());
            adidas_recycleView.setAdapter(productAdapter);
            adidas_recycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        } catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void getNike(View rootView) {
        try {
            productAdapter = new ProductAdapter(dbHelper.getAllNikeData());
            nike_recycleView.setAdapter(productAdapter);
            nike_recycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        } catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(View view){
        try {
            productAdapter = new ProductAdapter(dbHelper.getAllProductData());
            product_recyclerView.setAdapter(productAdapter);
            product_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        } catch (Exception ex){
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
