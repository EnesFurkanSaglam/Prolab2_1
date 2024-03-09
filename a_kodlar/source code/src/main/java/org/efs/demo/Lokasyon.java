package org.efs.demo;
import java.util.ArrayList;

import static org.efs.demo.HelloApplication.*;

public class Lokasyon {
        // Kodinatlar sol üsten itibaren yazılıyor

    /*

    LOKASYON İÇİN NOT:
    0-Hazine
    1-Boş Yerler
    2-Karakter
    3-Harketli Engel
    4-HareketsizEngelYaz
    5-HareketsizEngelKis

    6-Çıkış

     */

    private int X;
    private int Y;
    static int [][] KORDINATLAR = new int[KARE_YUKSEKLIK][KARE_GENISLIK];


    public void KarakterKordinatYaz(int boy,int genislik,int solUstKordinatX,int solUstKordinatY){

        int x1 = solUstKordinatX- 1;
        int x2 = solUstKordinatX + genislik -2;
        int y1 = solUstKordinatY -1;
        int y2 = solUstKordinatY + boy -2 ;

        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                KORDINATLAR[i][j] = 2;
            }
        }

    }

    public void HazineKordinatYaz(int boy,int genislik,int solUstKordinatX,int solUstKordinatY){

        int x1 = solUstKordinatX- 1;
        int x2 = solUstKordinatX + genislik -2;
        int y1 = solUstKordinatY -1;
        int y2 = solUstKordinatY + boy -2 ;

        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                this.KORDINATLAR[i][j] = 0 ;
            }
        }

    }
    public void HareketliEngelKordinatYaz(int boy, int genislik, int solUstKordinatX, int solUstKordinatY, int hareketBoyutu, String hareketYonu) {

        int x1 = solUstKordinatX - 1;
        int x2 = solUstKordinatX + genislik - 2;
        int y1 = solUstKordinatY - 1;
        int y2 = solUstKordinatY + boy - 2;

        switch (hareketYonu) {

            case "X":

                for (int i = y1; i <= y2; i++) {
                    for (int j = x1; j <= x2; j++) {
                        this.KORDINATLAR[i][j] = 3;
                        for (int k = 1; k <= hareketBoyutu; k++) {
                            this.KORDINATLAR[i][j - k] = 3;
                            this.KORDINATLAR[i][j + k] = 3;
                        }
                    }
                }

                break;
            case "Y":

                for (int i = y1; i <= y2; i++) {
                    for (int j = x1; j <= x2; j++) {
                        this.KORDINATLAR[i][j] = 3;
                        for (int k = 1; k <= hareketBoyutu; k++) {
                            this.KORDINATLAR[i - k][j] = 3; // Üstteki hareketli engel
                            this.KORDINATLAR[i + k][j] = 3; // Altındaki hareketli engel
                        }
                    }
                }

                break;
        }

    }

    public void HareketsizEngelYazKordinatYaz(int boy,int genislik,int solUstKordinatX,int solUstKordinatY) {

        int x1 = solUstKordinatX - 1;
        int x2 = solUstKordinatX + genislik - 2;
        int y1 = solUstKordinatY - 1;
        int y2 = solUstKordinatY + boy - 2;

        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                this.KORDINATLAR[i][j] = 3;
            }
        }
    }

    public void HareketsizEngelKisKordinatYaz(int boy,int genislik,int solUstKordinatX,int solUstKordinatY){

        int x1 = solUstKordinatX- 1;
        int x2 = solUstKordinatX + genislik -2;
        int y1 = solUstKordinatY -1;
        int y2 = solUstKordinatY + boy -2 ;

        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                this.KORDINATLAR[i][j] = 3;
            }
        }


    }
    public void HaritaMatrisYazdir(){

        for (int i = 0;i<KARE_YUKSEKLIK;i++){
            for (int j = 0;j<KARE_GENISLIK;j++){
                System.out.print(this.KORDINATLAR[i][j]);
            }
            System.out.println();
        }
    }

    public int Kontrol(int x1, int x2, int x3, int x4, int y1, int y2, int y3, int y4) {

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y4; j++) {
                if (this.KORDINATLAR[j][i] != 1) {
                    return -1;
                }
            }
        }
        return 1;
    }


    public void MatrisiBirle(){
        for (int i = 0;i<KARE_YUKSEKLIK;i++){
            for (int j = 0;j<KARE_GENISLIK;j++){
                KORDINATLAR[i][j] = 1;
            }
        }

    }



}