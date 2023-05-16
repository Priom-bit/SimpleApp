package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageview;
    Button buttonid;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int imageid=getimageid();
        i=imageid;
        String imageUrl = "https://picsum.photos/id/"+i+"/600";
        imageview = findViewById(R.id.imageid);
        buttonid = findViewById(R.id.buttonid);

        Picasso.get()
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) // Disable memory caching
                .networkPolicy(NetworkPolicy.NO_CACHE) // Disable network caching
                .into(imageview);



        buttonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i++;
                Log.v("priom","NIROB");
                String imageUrl = "https://picsum.photos/id/"+i+"/600";

                buttonid();
                Picasso.get()
                        .load(imageUrl)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) // Disable memory caching
                        .networkPolicy(NetworkPolicy.NO_CACHE) // Disable network caching
                        .into(imageview);
                saveimageid(i);

            }
        });
    }
    protected boolean isOnline(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }
    }
    public void buttonid(){
        if (isOnline()){

        }else {
            Toast.makeText(MainActivity.this, "You are not connected to internet", Toast.LENGTH_SHORT).show();
        }
    }
    private void saveimageid(int id){
        SharedPreferences settings = getSharedPreferences("IB_SimpleApp", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("image_id",id);
        editor.apply();
    }

    private int getimageid(){

        SharedPreferences settings = getSharedPreferences("IB_SimpleApp", 0);
        int image_id = settings.getInt("image_id", 0); //0 is the default value
        return image_id;
    }
}
