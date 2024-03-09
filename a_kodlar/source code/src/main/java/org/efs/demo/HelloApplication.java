package org.efs.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import static org.efs.demo.HareketliEngel.hareketEttir;
import static org.efs.demo.HareketliEngel.hareketliEngelOlustur;
import static org.efs.demo.HareketsizEngelKis.KisEngelOlustur;
import static org.efs.demo.HareketsizEngelYaz.YazEngelOlustur;
import static org.efs.demo.Hazine.HazineOlustur;
import static org.efs.demo.Karakter.KarakterOlustur;
import static org.efs.demo.Uygulama.KarkaterHareketEttir;


public class HelloApplication extends Application{

     static final int GENISLIK = 1000;
     static final int YUKSEKLIK = 1000;
     static final int KARE_YUKSEKLIK = 50;
     static final int KARE_GENISLIK = 50;
     static final int KARE_BOYUTU = GENISLIK / KARE_YUKSEKLIK;
    static GraphicsContext gc;
    static Clip clip1;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage){


        Group group = new Group();
        primaryStage.setTitle("ANA EKRAN");
        Canvas canvas0 = new Canvas(GENISLIK,YUKSEKLIK);

        Button buttonIlk = new Button("Oyuna başla");
        buttonIlk.setLayoutX(700);
        buttonIlk.setLayoutY(850);

        Text textBaslangic = new Text("AKASYA DURAĞI");
        textBaslangic.setFont(Font.font("Arial",60));
        textBaslangic.setX(250);
        textBaslangic.setY(100);

        Text textAlt =new Text(" Hoşgeldin oyuncu. Nasılsın,\n bugün seninle beraber amansız \n bir yolculuğa çıkacağız. Hazır mısın ? \n O zaman BAŞLAAAA ");
        textAlt.setFont(Font.font("Arial",18));
        textAlt.setX(600);
        textAlt.setY(720);


        group.getChildren().add(canvas0);
        Scene scene0 = new Scene(group);
        primaryStage.setScene(scene0);
        primaryStage.show();
        gc = canvas0.getGraphicsContext2D();

        anaEkranTasarimi(group);
        group.getChildren().add(buttonIlk);
        group.getChildren().add(textBaslangic);
        group.getChildren().add(textAlt);


        File a = new File("C:\\BEN\\Kodlar\\Proje\\Proje_9_Uni_ProLab2_1\\a_vaw\\a.wav");
        muzikCal1(a);


        buttonIlk.setOnAction(actionEventIlk ->{

            muzikDurdur1();

            File b = new File("C:\\BEN\\Kodlar\\Proje\\Proje_9_Uni_ProLab2_1\\a_vaw\\b.wav");
            muzikCal2(b);

            Group root = new Group();
            primaryStage.setTitle("OTONOM HAZİNE AVCISI");
            Canvas canvas = new Canvas(GENISLIK, YUKSEKLIK);
            root.getChildren().add(canvas);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            gc = canvas.getGraphicsContext2D();

            run();

            Lokasyon lokasyon = new Lokasyon();

            try {
                HazineOlustur(lokasyon,root);
                hareketliEngelOlustur(lokasyon,root);
                YazEngelOlustur(lokasyon,root);
                KisEngelOlustur(lokasyon,root);
                KarakterOlustur(lokasyon,root);

                KarkaterHareketEttir();
                //hareketEttir();

                lokasyon.HaritaMatrisYazdir();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

        });

    }
    private void run() {
        drawBackground(gc);
    }
    private void drawBackground(GraphicsContext gc) {

        for (int i = 0; i < KARE_YUKSEKLIK; i++) {
            for (int j = 0; j < KARE_GENISLIK; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("#808080"));
                } else {
                    gc.setFill(Color.web("#ffffff"));
                }
                gc.fillRect(i * KARE_BOYUTU, j * KARE_BOYUTU, KARE_BOYUTU, KARE_BOYUTU);
            }
        }
    }

    private void anaEkranTasarimi(Group group){

        Image imageUst = new Image("file:///C:/BEN/Kodlar/Proje/Proje_9_Uni_ProLab2_1/a_png/AnaEkran/" + "fotoUst.jpg");
        ImageView imageViewUst = new ImageView(imageUst);
        imageViewUst.setFitWidth(799);
        imageViewUst.setFitHeight(444);
        imageViewUst.setX(100);
        imageViewUst.setY(150);
        group.getChildren().add(imageViewUst);

        Image imageAlt = new Image("file:///C:/BEN/Kodlar/Proje/Proje_9_Uni_ProLab2_1/a_png/AnaEkran/" + "doblo.png");
        ImageView imageViewAlt = new ImageView(imageAlt);
        imageViewAlt.setX(50);
        imageViewAlt.setY(550);
        group.getChildren().add(imageViewAlt);
    }



    public static void muzikCal1(File musicFilePath) {

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicFilePath);
            clip1 = AudioSystem.getClip();
            clip1.open(audioIn);
            clip1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void muzikCal2(File musicFilePath) {

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicFilePath);
            Clip clip2 = AudioSystem.getClip();
            clip2.open(audioIn);
            clip2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void muzikDurdur1(){
        clip1.stop();
    }

}