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


        int size = 2;


        //img = info.getScreenshot();
        //info.saveScreenshot(info.averageScreenCompress(info.averageScreen(img,size,img.getWidth(), img.getHeight(), 2),size,img.getWidth(),img.getHeight()));


        img = info.getScreenshot();
        //info.averageScreen(img,size,img.getWidth(), img.getHeight(), 1);
        info.saveScreenshot(info.averageScreen(img,size,img.getWidth(), img.getHeight(), 2),1);

        img = info.getScreenshot(1218, 122, 50, 50);
        img = null;
        try {
            img = ImageIO.read(new File("/Users/aidan/Documents/workspace/Screen Averager/src/Screen Shot 2018-01-09 at 12.35.52 AM.png"));

        } catch (IOException e) {
            System.out.println("fail");
        }

        //info.averageScreen(img,size,img.getWidth(), img.getHeight(), 2);
        img = info.getScreenshot(443, 143, 63, 63);
        info.saveScreenshot(info.averageScreen(img,size,img.getWidth(), img.getHeight(), 2), 2);




        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime)/1000000000.0 + " seconds");
    }
}