package General;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aidan Nuzum-Clark on 10/17/2017.
 */
public class Runner2 {
    public static void main(String[] args){
        Info info = new Info();
        FinderInfo finderInfo = new FinderInfo(5);


/*

        ArrayList<Color> colors = new ArrayList<>();

        colors.add(Color.red);
        colors.add(Color.red);
        colors.add(Color.gray);
        colors.add(Color.green);
        colors.add(Color.gray);
        colors.add(Color.gray);
        colors.add(Color.green);
        colors.add(Color.gray);
        colors.add(Color.gray);
        colors.add(Color.green);
        colors.add(Color.gray);
        colors.add(Color.gray);
        colors.add(Color.green);
        colors.add(Color.gray);


        System.out.println(colors.size());

        colors = finderInfo.removeDuplicateColors(colors);

        System.out.println(colors.size());
        */


        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        long startTime = System.nanoTime();



        BufferedImage img = null;

        img = info.getScreenshot();
        img = null;
        try {
            img = ImageIO.read(new File("/Users/aidan/Documents/workspace/Screen Averager/test1.png"));

        } catch (IOException e) {
            System.out.println("fail");
        }
        ImageColorsAndSize big = finderInfo.imageToArrayList(img, 10, img.getWidth(), img.getHeight());




        img = null;
        try {
            img = ImageIO.read(new File("/Users/aidan/Documents/workspace/Screen Averager/test2.png"));

        } catch (IOException e) {
            System.out.println("fail");
        }


        ImageColorsAndSize small = finderInfo.imageToArrayList(img, 10, img.getWidth(), img.getHeight());

        //System.out.println(finderInfo.imageInImage(big, small));

        System.out.println(big);
        System.out.println(small);
        small.colors = finderInfo.removeDuplicateColors(small.colors);
        System.out.println(small);

        for(Color c: small.colors){
            System.out.println(c);
        }


        //System.out.println(finderInfo.compareImages(big, small));





        long endTime = System.nanoTime();
        //System.out.println("Took "+(endTime - startTime)/1000000000.0 + " seconds");


    }
}