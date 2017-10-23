package General;

import java.awt.*;

/**
 * Created by Aidan Nuzum-Clark on 10/19/2017.
 */
public class ColorAndCount {
    Color color;
    int count;
    public ColorAndCount(Color c){
        color = c;
        count = 1;
    }

    public void increaseCount(){
        count++;
    }

    public int getCount(){
        return count;
    }

    public Color getColor(){
        return color;
    }
}