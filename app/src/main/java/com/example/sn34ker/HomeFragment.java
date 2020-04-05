package com.example.sn34ker;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    View v;
    ViewFlipper imageFlip;
    TextView email;

    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        int img[] = {R.drawable.rsx, R.drawable.yeezy, R.drawable.nikeaf, R.drawable.jair, R.drawable.jretro};

        imageFlip = v.findViewById(R.id.imageFlipper);

        for (int i = 0; i < img.length; i++){
            flipperImages(img[i]);
        }

        email = v.findViewById(R.id.emailTxtView);
        email.setText(Html.fromHtml("<a href=\"mailto:sn34ker@gmail.com\">sn34ker@gmail.com</a>"));
        email.setMovementMethod(LinkMovementMethod.getInstance());


        return v;
    }

    public void flipperImages(int image){
        ImageView imgView = new ImageView(getContext());
        imgView.setBackgroundResource(image);

        imageFlip.addView(imgView);
        imageFlip.setFlipInterval(4000);
        imageFlip.setAutoStart(true);

        imageFlip.setInAnimation(getContext(), android.R.anim.slide_in_left);
        imageFlip.setOutAnimation(getContext(), android.R.anim.slide_out_right);

    }
}
