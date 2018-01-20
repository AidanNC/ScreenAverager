package General;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aidan Nuzum-Clark on 10/17/2017.
 */
public class Runner2 {
    public static void main(String[] args){
        Info info = new Info();
        FinderInfo finderInfo = new FinderInfo(1);
        try{
            Thread.sleep(2000);
        }catch(Exception e){

        }
        long startTime = System.nanoTime();



        BufferedImage img = null;

        img = info.getScreenshot();
        ImageColorsAndSize big = finderInfo.imageToArrayList(img, 1, img.getWidth(), img.getHeight());




        img = null;
        try {
            img = ImageIO.read(new File("/Users/aidan/Documents/workspace/Screen Averager/src/Screen Shot 2018-01-09 at 12.35.52 AM.png"));

        } catch (IOException e) {
            System.out.println("fail");
        }


        ImageColorsAndSize small = finderInfo.imageToArrayList(img, 1, img.getWidth(), img.getHeight());

        System.out.println(finderInfo.imageInImage(big, small));

        //System.out.println(big);
        System.out.println(small);
        small.colors = finderInfo.removeDuplicateColors(small.colors);
        System.out.println(small);




        long endTime = System.nanoTime();
        //System.out.println("Took "+(endTime - startTime)/1000000000.0 + " seconds");
    }
}