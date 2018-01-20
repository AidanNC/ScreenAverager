package General;
import java.util.ArrayList;
import java.awt.Color;
/**
 * Created by aidan on 1/17/18.
 */
public class ImageColorsAndSize {
    ArrayList<Color> colors;
    int width;
    int height;

    public ImageColorsAndSize(ArrayList<Color> colors, int width, int height){
        this.colors = colors;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString(){

        return "" + colors.size();
    }
}
