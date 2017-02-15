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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import static com.wanosoft.filtersapp.R.id.seekBar;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Filtros filtros;
    private Button reset, grey, inverse, brillo;
    private Spinner spinner;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.boton);
        spinner = (Spinner) findViewById(R.id.spinner);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        imageView=(ImageView) findViewById(R.id.imagen);

        seekBar.setEnabled(false);
        filtros=new Filtros();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
                imageView.setImageResource(R.drawable.zedicon);
            }
        });


        spinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    Spinner.OnItemSelectedListener onItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i==1){
                seekBar.setEnabled(false);
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.greyScale(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            } else if (i==2){
                seekBar.setEnabled(false);
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.inverse(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            } else if (i==3){
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.brillo(bitmapDrawable.getBitmap(),55);
                imageView.setImageBitmap(bmp);
                seekBar.setEnabled(true);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

}
