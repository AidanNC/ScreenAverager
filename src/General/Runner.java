package General;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aidan Nuzum-Clark on 10/17/2017.
 */
public class Runner {
    public static void main(String[] args){
        Info info = new Info();
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        long startTime = System.nanoTime();



        BufferedImage img = null;


        //String read = "C:\\Users\\Aidan Nuzum-Clark\\IdeaProjects\\LeagueBot2.0\\src\\ninja.jpg";
        int size = 10;
        /*
        try {
            img = ImageIO.read(new File(read));
        } catch (IOException e) {
            System.out.println("fuailtere");
        }
        */
        img = info.getScreenshot();
        //info.saveScreenshot(info.averageScreen(img,size,img.getWidth(), img.getHeight(), 1),1);
/*
        try {
            img = ImageIO.read(new File(read));
        } catch (IOException e) {
            System.out.println("fuailtere");
        }
        */
        img = info.getScreenshot();
        ///info.saveScreenshot(info.averageScreen(img,size,img.getWidth(), img.getHeight(), 2), 2);




        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime)/1000000000.0 + " seconds");
    }
}