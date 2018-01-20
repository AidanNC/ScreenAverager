package General;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * Created by Aidan Nuzum-Clark on 10/17/2017.
 */
public class Info {
    Robot robot;
    BufferedImage currentScreen;
    public Info(){
        try {
            robot = new Robot();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("robot creation failed in General.Info");
        }
    }


    public BufferedImage getScreenshot(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int xMeasure = (int)screenSize.getWidth();
        int yMeasure = (int)screenSize.getHeight();
        try {
            currentScreen = robot.createScreenCapture( new Rectangle(0, 0, xMeasure, yMeasure) );
            return currentScreen;
        } catch(Exception e) {
        }
        return currentScreen;
    }

    public BufferedImage getScreenshot(int x, int y, int width, int height){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int xMeasure = (int)screenSize.getWidth();
        int yMeasure = (int)screenSize.getHeight();
        try {
            currentScreen = robot.createScreenCapture( new Rectangle(x, y, width, height) );
            return currentScreen;
        } catch(Exception e) {
        }
        return currentScreen;
    }

    //if the mode is 1 then it will average the pixels in a box, if the mode is 2 then it will take the most prevelant color
    public BufferedImage averageScreen(BufferedImage bi, int squareSize, int imageWidth, int imageHeight, int mode){
        int xMeasure = imageWidth/squareSize;
        int yMeasure = imageHeight/squareSize;

        for(int i = 0; i < xMeasure; i++){
            for(int j = 0; j < yMeasure; j++){
                if(mode ==1) {
                    bi = simplify(bi, i * squareSize, j * squareSize, squareSize);
                }else if(mode ==2){
                    bi =simplifyCommonColor(bi, i * squareSize, j * squareSize, squareSize);
                }
            }
        }
        return bi;
    }


    //this takes an averaged picture and then makes it into a compressed pixel image of it
    public BufferedImage averageScreenCompress(BufferedImage bi, int squareSize, int imageWidth, int imageHeight){
        int xMeasure = imageWidth/squareSize;
        int yMeasure = imageHeight/squareSize;

        BufferedImage compressedIMG = new BufferedImage(xMeasure, yMeasure,TYPE_INT_ARGB);

        for(int i = 0; i < xMeasure; i++){
            for(int j = 0; j < yMeasure; j++){

                compressedIMG.setRGB(i,j,bi.getRGB(i*squareSize, j * squareSize));
            }
        }
        return compressedIMG;
    }

    //yo make sure that square size is a factor of both the height and the width
    // if mode is 1 then it will average, if the mode is 2 then it will take most common
    public BufferedImage averageScreen(int squareSize, int mode){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int xMeasure = (int)screenSize.getWidth()/squareSize;
        int yMeasure = (int)screenSize.getHeight()/squareSize;

        BufferedImage bi = getScreenshot();

        for(int i = 0; i < xMeasure; i++){
            for(int j = 0; j < yMeasure; j++){
                // bi = simplify(bi, i*squareSize,j*squareSize,squareSize);

                if(mode ==1) {
                    bi = simplify(bi, i * squareSize, j * squareSize, squareSize);
                }else if(mode ==2){
                    bi =simplifyCommonColor(bi, i * squareSize, j * squareSize, squareSize);
                }
            }
        }
        return bi;
    }


    public BufferedImage simplifyCommonColor(BufferedImage bi, int xstart, int ystart, int squareSize ){
        ArrayList<Color> colors = new ArrayList<>();
        for(int x = xstart; x < xstart + squareSize; x++){
            for(int y = ystart; y < ystart+squareSize; y++){
                colors.add(new Color(bi.getRGB(x,y)));
            }
        }
        //System.out.println(colors.size());
        try {
            int rgb = 0;
            Color useThis = mostCommonColor(colors);

            rgb = useThis.getRed() * 65536 + useThis.getGreen() * 256 + useThis.getBlue();


            for (int x = xstart; x < xstart + squareSize; x++) {
                for (int y = ystart; y < ystart + squareSize; y++) {

                    bi.setRGB(x, y, rgb);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(99090);
        }
        return bi;

    }





    public Color mostCommonColor(ArrayList<Color> colors){
        ArrayList<ColorAndCount> tally = new ArrayList<>();
        //tally.add(new ColorAndCount(new Color(224,0, 156)));
        for(Color c: colors){
            boolean addToTally = true;
            for(ColorAndCount cAC: tally){
                if(c.equals(cAC.getColor())){
                    cAC.increaseCount();
                    addToTally = false;
                }
            }
            if(addToTally){
                tally.add(new ColorAndCount(c));
            }
        }
        int largestCount = 0;
        Color returningColor = new Color(224,0, 156);
        for(ColorAndCount cAC: tally){
            //System.out.println("Checking tally");
            if(cAC.getCount() > largestCount){
                largestCount = cAC.getCount();
                returningColor = cAC.getColor();

            }
        }
        //System.out.println(returningColor);
        return returningColor;
    }

    public BufferedImage simplify(BufferedImage bi, int xstart, int ystart, int squareSize){
        int total = 0;
        int red =0;
        int blue =0;
        int green = 0;
        Color c;
        for(int x = xstart; x < xstart + squareSize; x++){
            for(int y = ystart; y < ystart+squareSize; y++){
                c = new Color(bi.getRGB(x,y));
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
        }
        red = red/ (squareSize*squareSize);
        blue = blue/(squareSize*squareSize);
        green = green/ (squareSize*squareSize);
        for(int x = xstart; x < xstart + squareSize; x++){
            for(int y = ystart; y < ystart+squareSize; y++){
                c = new Color(red, green, blue);
                int rgb=c.getRed()*65536+c.getGreen()*256+c.getBlue();
                bi.setRGB(x,y,rgb);
            }
        }
        return bi;
    }

    //at the moment this assumes that the img being passed is 1920 by 1080, so keep that in mind
    public BufferedImage simplify(BufferedImage bi){


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int xMeasure = (int)screenSize.getWidth();
        int yMeasure = (int)screenSize.getHeight();

        int total = 0;
        int red =0;
        int blue =0;
        int green = 0;
        Color c;
        for(int x = 0; x < xMeasure; x++){
            for(int y = 0; y < yMeasure; y++){
                c = new Color(bi.getRGB(x,y));
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
        }
        red = red/ (xMeasure*yMeasure);
        blue = blue/ (xMeasure*yMeasure);
        green = green/ (xMeasure*yMeasure);
        for(int x = 0; x < xMeasure; x++){
            for(int y = 0; y < yMeasure; y++){
                c = new Color(red, green, blue);
                int rgb=c.getRed()*65536+c.getGreen()*256+c.getBlue();
                bi.setRGB(x,y,rgb);
            }
        }
        return bi;
    }


    public void saveScreenshot(BufferedImage bi){
        try {
            File outputfile = new File("test1.png");
            ImageIO.write(bi, "png", outputfile);
        }catch(Exception e){

        }
    }

    public void saveScreenshot(BufferedImage bi, int i){
        try {
            File outputfile = new File("test" + i + ".png");
            ImageIO.write(bi, "png", outputfile);
        }catch(Exception e){

        }
    }


    /////this is stuff that we tried to do but I don't know if it actually will work, maybe we will come back to it later though///////
    /*
    //they should both be pixalized images
    public boolean imageWithinImage(BufferedImage wholeImage, BufferedImage target){
        int width = target.getWidth();
        int height = target.getHeight();
        //loop through the whole x and y in chunks that are the same size as the target
        for(int i = 0; i < wholeImage.getWidth() / width; i ++){
            for(int j = 0; j < wholeImage.getHeight() / height; j ++){
                BufferedImage temp = wholeImage.getSubimage(i * width, j * height, width, height);
                if(similarEnough(temp, target)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean similarEnough(BufferedImage a, BufferedImage b){
        Color aColor =

    }

    public int sumColor(BufferedImage a){
        for(int i = 0; i < a.getHeight(); i ++){
            for(int j = 0; j < a.getWidth(); j ++){

            }
        }
    }
    */

}