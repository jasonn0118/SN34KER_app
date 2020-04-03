package com.example.sn34ker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AboutFragment extends Fragment {

    View v;
    List<Integer> devsPicList = new ArrayList<>();

    private void addItems(){
        devsPicList = Arrays.asList(R.drawable.jair, R.drawable.nikeaf, R.drawable.rsx);
    }

    public AboutFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_about, container, false);
        super.onCreate(savedInstanceState);
        addItems();

        final AboutCustomAdapter aboutAdapter = new AboutCustomAdapter(devsPicList);
        ListView devsPiclistView = v.findViewById(R.id.devsPicListView);
        devsPiclistView.setAdapter(aboutAdapter);

        devsPiclistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/jeamin.shin.7")));
                        break;
                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/shounak.banerjee.94?ref=bookmarks")));
                        break;
                    case 2:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/lamnguyen210")));
                        break;

                }
            }
        });

        return  v;
    }
}
