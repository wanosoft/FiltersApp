package com.wanosoft.filtersapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private SeekBar seekBarR, seekBarG, seekBarB;
    public int estado = 0;
    public int valueR = 0;
    public int valueG = 0;
    public int valueB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = (Button) findViewById(R.id.boton);
        apply = (Button) findViewById(R.id.apply);
        spinner = (Spinner) findViewById(R.id.spinner);
        seekBarR = (SeekBar) findViewById(R.id.seekBar);
        seekBarG = (SeekBar) findViewById(R.id.seekBar2);
        seekBarB = (SeekBar) findViewById(R.id.seekBar3);
        imageView=(ImageView) findViewById(R.id.imagen);

        seekBarR.setEnabled(false);
        seekBarG.setEnabled(false);
        seekBarB.setEnabled(false);
        filtros=new Filtros();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(null);
                imageView.setImageResource(R.drawable.zedicon);
                if (estado==4||estado==3||estado==5||estado==6||estado==8){
                    apply.setEnabled(true);
                    seekBarR.setEnabled(true);
                } else {
                    apply.setEnabled(false);
                    seekBarR.setEnabled(false);
                }
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estado == 4){
                    //EdgeDetect
                    BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp=filtros.edgeDetect(bitmapDrawable.getBitmap(), valueR);
                    imageView.setImageBitmap(bmp);
                } else if (estado==3) {
                    //Brillo
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp = filtros.brillo(bitmapDrawable.getBitmap(), valueR);
                    imageView.setImageBitmap(bmp);
                } else if (estado==5){
                    //Contrast
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp = filtros.contrast(bitmapDrawable.getBitmap(), valueR);
                    imageView.setImageBitmap(bmp);
                } else if (estado==6){
                    //Modify RGB
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp = filtros.modifyRGB(bitmapDrawable.getBitmap(), valueR, valueG, valueB);
                    imageView.setImageBitmap(bmp);
                } else if (estado==7){
                    //Gamma
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bmp = filtros.gamma(bitmapDrawable.getBitmap());
                    imageView.setImageBitmap(bmp);
                } else if (estado==8){
                    //Rotar
                }
            }
        });

        spinner.setOnItemSelectedListener(onItemSelectedListener);
        seekBarR.setOnSeekBarChangeListener(seekBarRedListener);
        seekBarG.setOnSeekBarChangeListener(seekBarRedListener);
        seekBarB.setOnSeekBarChangeListener(seekBarRedListener);
    }

    Spinner.OnItemSelectedListener onItemSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i==1){
                apply.setEnabled(false);
                seekBarR.setEnabled(false);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                setState(i);
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.greyScale(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            } else if (i==2){
                apply.setEnabled(false);
                seekBarR.setEnabled(false);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                setState(i);
                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
                Bitmap bmp=filtros.inverse(bitmapDrawable.getBitmap());
                imageView.setImageBitmap(bmp);
            } else if (i==3){
                apply.setEnabled(true);
                seekBarR.setEnabled(true);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                setState(i);

            } else if (i==4){
                seekBarR.setEnabled(true);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                apply.setEnabled(true);
                setState(i);
            } else if (i==5){
                seekBarR.setEnabled(true);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                apply.setEnabled(true);
                setState(i);
            } else if (i==6) {
                seekBarR.setEnabled(true);
                seekBarG.setEnabled(true);
                seekBarB.setEnabled(true);
                apply.setEnabled(true);
                setState(i);
            } else if (i==7){
                seekBarR.setEnabled(false);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                apply.setEnabled(true);
                setState(i);
            } else if (i==8){
                seekBarR.setEnabled(false);
                seekBarG.setEnabled(false);
                seekBarB.setEnabled(false);
                apply.setEnabled(true);
            }
        }

        public void setState(int state){
            estado = state;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    SeekBar.OnSeekBarChangeListener seekBarRedListener =new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                valueR =i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener seekBarGreenListener = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            valueG =i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    } ;

    SeekBar.OnSeekBarChangeListener seekBarBlueListener = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            valueB =i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    } ;

}
