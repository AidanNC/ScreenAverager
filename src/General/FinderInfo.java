package General;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by aidan on 1/17/18.
 */
public class FinderInfo {

    int squareSize;

    public FinderInfo(int squareSize){
        this.squareSize = squareSize;
    }



    public ImageColorsAndSize imageToArrayList(BufferedImage bi, int squareSize, int imageWidth, int imageHeight){
        int xMeasure = imageWidth/squareSize;
        int yMeasure = imageHeight/squareSize;
        ArrayList<Color> returner = new ArrayList<>();
        for(int i = 0; i < xMeasure; i++){
            for(int j = 0; j < yMeasure; j++){

                returner.add(commonColorOfImageSection(bi, i, j, squareSize));

            }
        }

        return new ImageColorsAndSize(returner, imageWidth, imageHeight);
    }

    public Color commonColorOfImageSection(BufferedImage bi, int xstart, int ystart, int squareSize ){
        ArrayList<Color> colors = new ArrayList<>();
        for(int x = xstart; x < xstart + squareSize; x++){
            for(int y = ystart; y < ystart+squareSize; y++){
                colors.add(new Color(bi.getRGB(x,y)));
            }
        }
        Color useThis = null;
        //System.out.println(colors.size());
        try {
            int rgb = 0;
             useThis = mostCommonColor(colors);

        }catch(Exception e){
            e.printStackTrace();
            System.exit(99090);
        }
        return useThis;

    }

    //the parameters for this method are the two arraylists containing all of the colors in teh two images
    public int imageInImage(ImageColorsAndSize bigImage, ImageColorsAndSize smallImage){
        Color firstCheck = smallImage.colors.get(0);
        for(Color c: bigImage.colors){
            if(c.equals(firstCheck)){
                if(sameImage(bigImage, smallImage, bigImage.colors.indexOf(c))){
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean sameImage(ImageColorsAndSize bigImage, ImageColorsAndSize smallImage, int startPoint){
        for(int j = 0; j < smallImage.height; j ++) {
            for (int i = 0; i < smallImage.width; i++) {
                if (!(bigImage.colors.get(j * bigImage.width/squareSize + startPoint + i).equals(smallImage.colors.get(j * smallImage.width/squareSize + i)))) {
                    return false;
                }
            }
        }
        return true;
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

    public ArrayList<Color> removeDuplicateColors(ArrayList<Color> allColors){
        int temp = allColors.size();
        ArrayList<Color> returner = new ArrayList<>();
        //returner.add(null);

        boolean done = false;
        for(Color cc: allColors){
            if(returner.size() == 0){
                returner.add(cc);
            }
            for(Color c: returner){
                if(!(c.equals(cc))){
                    returner.add(c);
                    done = true;
                }
                if(done){
                    done = false;
                    break;
                }
            }
        }

        return returner;
    }


}
