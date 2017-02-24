package com.wanosoft.filtersapp;
import android.graphics.Bitmap;
import android.util.Log;

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

    public Bitmap edgeDetect(Bitmap bitmap, int umbral){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel=0;
        //Declaración de las variables de distancia
        int D1=0;
        int D2=0;
        //se declaran las variables a utilizar para vaciar el pixel y los canales
        int c1=0;
        int c2=0;
        int c3=0;
        //C1
        int r1=0;
        int g1=0;
        int b1=0;
        int a1=0;
        //C2
        int r2=0;
        int g2=0;
        int b2=0;
        int a2=0;
        //C3
        int r3=0;
        int g3=0;
        int b3=0;
        int a3=0;
        //se declara la variable grey donde de descargara la sumatoria de los pixeles
        int grey=0;
        //se recorre la imagen de origen ancho por alto
        for (int x=0;x<bitmap.getWidth()-2;x++) {
            for (int y = 0; y < bitmap.getHeight()-2; y++) {
                // se obtiene el pixel segun las coordenadas

                c1 = bitmap.getPixel(x, y);
                c2 = bitmap.getPixel(x+1, y);
                c3 = bitmap.getPixel(x, y+1);

                //se obtienen los canales del pixel por medio de corrimiento

                a1 = (c1 >>> 24) & 0xff;
                r1 = (c1 >> 16) & 0xff;
                g1 = (c1 >> 8) & 0xff;
                b1 = c1 & 0xff;

                a2 = (c2 >>> 24) & 0xff;
                r2 = (c2 >> 16) & 0xff;
                g2 = (c2 >> 8) & 0xff;
                b2 = c2 & 0xff;

                a3 = (c3 >>> 24) & 0xff;
                r3 = (c3 >> 16) & 0xff;
                g3 = (c3 >> 8) & 0xff;
                b3 = c3 & 0xff;

                //se realiza la operacion por pixel
                D1 = (int) Math.pow(r1 - r2, 2.0) + (int) Math.pow(g1 - g2, 2.0) + (int) Math.pow(b1 - b2, 2.0);
                D1 = (int) Math.sqrt(D1);
                D2 = (int) Math.pow(r1 - r3, 2.0) + (int) Math.pow(g1 - g3, 2.0) + (int) Math.pow(b1 - b3, 2.0);
                D2 = (int) Math.sqrt(D2);

                if (D1 >= umbral || D2 >= umbral) {
                    D1 = 255;
                } else {
                    D1 = 0;
                }
                //se vuelve a codificar el pixel
                pixel=((a1<<24)|(D1<<16)|(D1<<8)|D1);
                //se agrega el pixel en el bitmap destino
                bmp.setPixel(x, y, pixel);
            }
        }
        //retorna el bitmap final para ser renderizado por imageView
        return bmp;
    }

    public Bitmap contrast(Bitmap bitmap, int brillo){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel=0;
        int r=0;
        int g=0;
        int b=0;
        int a=0;

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
                double teta = brillo/Math.PI*180;
                r = (int) ((r-128)*Math.tan(teta)+180) >= 255 ? 255:(int) ((r-128)*Math.tan(teta)+180);
                g = (int) ((g-128)*Math.tan(teta)+180) >= 255 ? 255:(int) ((g-128)*Math.tan(teta)+180);
                b = (int) ((b-128)*Math.tan(teta)+180) >= 255 ? 255:(int) ((b-128)*Math.tan(teta)+180);

                //limite de rango
                if (r>=255){
                    r=255;
                } else if (r<=0) {
                    r = 0;
                }

                if (g>=255){
                    g=255;
                } else if (g<=0) {
                    g = 0;
                }

                if (b>=255){
                    b=255;
                } else if (b<=0) {
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

    public Bitmap modifyRGB(Bitmap bitmap, int seekR, int seekG, int seekB){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel=0;
        int r=0;
        int g=0;
        int b=0;
        int a=0;

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
                r = r + seekR;
                if(r>255){
                    r = 255;
                } if (r<0){
                    r = 0;
                }
                g = g + seekG;
                if(g>255){
                    g = 255;
                } if (g<0){
                    g = 0;
                }
                b = b + seekB;
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

    public Bitmap Gamma(Bitmap bitmap, int brillo){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel=0;
        int r=0;
        int g=0;
        int b=0;
        int a=0;

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
                double teta = brillo/Math.PI*180;
                r = (int) ((r-128)*Math.tan(teta)+180) >= 255 ? 255:(int) ((r-128)*Math.tan(teta)+180);
                g = (int) ((g-128)*Math.tan(teta)+180) >= 255 ? 255:(int) ((g-128)*Math.tan(teta)+180);
                b = (int) ((b-128)*Math.tan(teta)+180) >= 255 ? 255:(int) ((b-128)*Math.tan(teta)+180);

                //limite de rango
                if (r>=255){
                    r=255;
                } else if (r<=0) {
                    r = 0;
                }

                if (g>=255){
                    g=255;
                } else if (g<=0) {
                    g = 0;
                }

                if (b>=255){
                    b=255;
                } else if (b<=0) {
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
