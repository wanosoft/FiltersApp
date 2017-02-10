package com.wanosoft.filtersapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView imageView;
    private Filtros filtros;
    private Button reset, grey, inverse, brillo;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.boton);
        grey = (Button) findViewById(R.id.grey);
        inverse = (Button) findViewById(R.id.inverse);
        brillo = (Button) findViewById(R.id.brillo);
        spinner = (Spinner) findViewById(R.id.spinner);



        imageView=(ImageView) findViewById(R.id.imagen);
        filtros=new Filtros();

        grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.greyScale(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            }
        });

        inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.inverse(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
                imageView.setImageResource(R.drawable.zedicon);
            }
        });

        brillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.brillo(bitmapDrawable.getBitmap(),55);
                imageView.setImageBitmap(bmp);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
