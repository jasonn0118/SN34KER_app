package com.example.sn34ker;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    final List<String> sneakerBrands = new ArrayList<>();
    DataBaseHelper dbHelper;
    RecyclerView product_recyclerView,nike_recycleView;
    Spinner spinner_Brands;
    String selectedType;
//    ArrayList<String> product_name, product_brand, product_price;
//    ArrayList<Byte[]> product_img;
    ProductAdapter productAdapter;
    private static final String TAG = "SHOP FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{

            final View rootView = inflater.inflate(R.layout.fragment_shop, container, false);

            product_recyclerView = rootView.findViewById(R.id.recycleView_Product);
            dbHelper = new DataBaseHelper(getActivity());
            nike_recycleView=rootView.findViewById(R.id.recycleView_nike);
            spinner_Brands=rootView.findViewById(R.id.select_List);
            add();
            ArrayAdapter<String> brandAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,sneakerBrands);
            brandAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner_Brands.setAdapter(brandAdapter);
            spinner_Brands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        Toast.makeText(getActivity(), "Please Select one of sneaker type.", Toast.LENGTH_SHORT).show();
                    }else {
                        selectedType = parent.getItemAtPosition(position).toString();
                        Toast.makeText(getActivity(), selectedType + " Selected.", Toast.LENGTH_SHORT).show();
                        getNike(rootView);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            //Get all Product data from db.
            getData(rootView);




            return rootView;
        }catch(Exception ex){
            Log.d("SHOP FRAGMENT", ex.getMessage());
            return null;
        }
    }

    private void add() {
        sneakerBrands.add(0,"Select Sneaker Brand");
        sneakerBrands.add("Adidas");
        sneakerBrands.add("Nike");
        sneakerBrands.add("Under Armour");
        sneakerBrands.add("Jordan");
        sneakerBrands.add("Puma");
        sneakerBrands.add("New Balance");

    }


    private void getNike(View rootView) {
        try {
            productAdapter = new ProductAdapter(dbHelper.getAllNikeData(selectedType));
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
