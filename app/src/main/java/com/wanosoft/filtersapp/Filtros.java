package com.wanosoft.filtersapp;
import android.graphics.Bitmap;

/**
 * Created by davic on 03/02/17.
 */

public class Filtros {

    public Bitmap greyScale(Bitmap bitmap){
        //crear una copia de bitmap para modificar la imagen origen
        // la imagen origen no puede ser modificada por lo tanto se crea una copia
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        //se declaran las variables a utilizar para vaciar el pixel y los canales

        int pixel=0;
        int r=0;
        int g=0;
        int b=0;
        int a=0;
        //se declara la variable grey donde de descargara la sumatoria de los pixeles
        int grey=0;
        //se recorre la imagen de origen ancho por alto
        for (int x=0;x<bitmap.getWidth();x++){
            for(int y=0;y<bitmap.getHeight();y++){
                // se obtiene el pixel segun las coordenadas
                pixel=bitmap.getPixel(x,y);
                //se obtienen los canales del pixel por medio de corrimiento
                a=(pixel>>>24) & 0xff;
                r=(pixel>>16) & 0xff;
                g=(pixel>>8) & 0xff;
                b=pixel & 0xff;
                //se realiza la operacion por pixel
                grey=(r+g+b)/3;
                //se vuelve a codificar el pixel
                pixel=((a<<24)|(grey<<16)|(grey<<8)|grey);
                //se agrega el pixel en el bitmap destino
                bmp.setPixel(x,y, pixel);
            }
        }
        //retorna el bitmap final para ser renderizado por imageView
        return bmp;
    }

    public Bitmap inverse(Bitmap bitmap){
        //crear una copia de bitmap para modificar la imagen origen
        // la imagen origen no puede ser modificada por lo tanto se crea una copia
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        //se declaran las variables a utilizar para vaciar el pixel y los canales

        int pixel=0;
        int r=0;
        int g=0;
        int b=0;
        int a=0;
        //se declara la variable grey donde de descargara la sumatoria de los pixeles
        int grey=0;
        //se recorre la imagen de origen ancho por alto
        for (int x=0;x<bitmap.getWidth();x++){
            for(int y=0;y<bitmap.getHeight();y++){
                // se obtiene el pixel segun las coordenadas
                pixel=bitmap.getPixel(x,y);
                //se obtienen los canales del pixel por medio de corrimiento
                a=(pixel>>>24) & 0xff;
                r=(pixel>>16) & 0xff;
                g=(pixel>>8) & 0xff;
                b=pixel & 0xff;
                //se realiza la operacion por pixel
                r = 255-r;
                g = 255-g;
                b = 255-b;
                //se vuelve a codificar el pixel
                pixel=((a<<24)|(r<<16)|(g<<8)|b);
                //se agrega el pixel en el bitmap destino
                bmp.setPixel(x,y, pixel);
            }
        }
        //retorna el bitmap final para ser renderizado por imageView
        return bmp;
    }

    public Bitmap brillo(Bitmap bitmap, int brillo){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel=0;
        int r=0;
        int g=0;
        int b=0;
        int a=0;

        if(brillo<-255){
            brillo  = -10;
        } else if (brillo>255){
            brillo = 10;
        }

        for (int x=0;x<bitmap.getWidth();x++){
            for(int y=0;y<bitmap.getHeight();y++){
                // se obtiene el pixel segun las coordenadas
                pixel=bitmap.getPixel(x,y);
                //se obtienen los canales del pixel por medio de corrimiento
                a=(pixel>>>24) & 0xff;
                r=(pixel>>16) & 0xff;
                g=(pixel>>8) & 0xff;
                b=pixel & 0xff;
                //se realiza la operacion por pixel
                r = r + brillo;
                if(r>255){
                    r = 255;
                } if (r<0){
                    r = 0;
                }
                g = g + brillo;
                if(g>255){
                    g = 255;
                } if (g<0){
                    g = 0;
                }
                b = b + brillo;
                if(b>255){
                    b = 255;
                } if (b<0){
                    b = 0;
                }
                //se vuelve a codificar el pixel
                pixel=((a<<24)|(r<<16)|(g<<8)|b);
                //se agrega el pixel en el bitmap destino
                bmp.setPixel(x,y, pixel);
            }
        }
        //retorna el bitmap final para ser renderizado por imageView
        return bmp;
    }
}
