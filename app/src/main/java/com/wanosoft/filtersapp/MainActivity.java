package com.wanosoft.filtersapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Filtros filtros;
    private Button reset, apply;
    private Spinner spinner;
    private SeekBar seekBar;
    public int estado = 0;
    public int valueprogress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.boton);
        apply = (Button) findViewById(R.id.apply);
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
                if (estado==4||estado==3){
                    apply.setEnabled(true);
                    seekBar.setEnabled(true);
                } else {
                    apply.setEnabled(false);
                    seekBar.setEnabled(false);
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado == 4){
                    //EdgeDetect
                    BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp=filtros.edgeDetect(bitmapDrawable.getBitmap(),valueprogress);
                    imageView.setImageBitmap(bmp);
                } else if (estado==3) {
                    //Brillo
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp = filtros.brillo(bitmapDrawable.getBitmap(), valueprogress);
                    imageView.setImageBitmap(bmp);
                }
            }
        });

        spinner.setOnItemSelectedListener(onItemSelectedListener);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    Spinner.OnItemSelectedListener onItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i==1){
                apply.setEnabled(false);
                seekBar.setEnabled(false);
                setState(i);
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.greyScale(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            } else if (i==2){
                apply.setEnabled(false);
                seekBar.setEnabled(false);
                setState(i);
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.inverse(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            } else if (i==3){
                apply.setEnabled(true);
                seekBar.setEnabled(true);
                setState(i);

            } else if (i==4){
                seekBar.setEnabled(true);
                apply.setEnabled(true);
                setState(i);
            }
        }

        public void setState(int state){
            estado = state;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    SeekBar.OnSeekBarChangeListener seekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueprogress=i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
