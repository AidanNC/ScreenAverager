package General;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * d0ge d0ge Anthony Gao d0ge 1/21/2018
*/

public class EnterLeague {

    Robot robot;

    public EnterLeague(){
        try {
            robot = new Robot();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("robot creation failed in General.EnterLeague");
        }
    }



    //SHORTCUTS:

    public void typeString(String s) //types out a String - most of this code is case-by-case for special characters
    {

        char exclamation = '!';
        char arroba = '@';
        char octothorpe = '#';
        char dollar = '$';
        char percent = '%';
        char caret = '^';
        char ampersand = '&';
        char star = '*';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            //most cases:
            if(Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if (Character.isLetterOrDigit(c)) {
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));
            }
            else if(Character.isSpaceChar(c)) {
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
            }

            //special individual characters:
            else if(c == exclamation){
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == arroba) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == octothorpe) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_3);
                robot.keyRelease(KeyEvent.VK_3);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == dollar) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_4);
                robot.keyRelease(KeyEvent.VK_4);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == percent) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == caret) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_6);
                robot.keyRelease(KeyEvent.VK_6);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == ampersand) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_7);
                robot.keyRelease(KeyEvent.VK_7);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if(c == star) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(KeyEvent.VK_8);
                robot.keyRelease(KeyEvent.VK_8);
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }

            robot.delay(50);
        }
    }

    public void mouseClick(int x, int y) //moves to, then clicks on a given location
    {
        robot.mouseMove(x,y);
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
    }





    //CODE TO ACTUALLY OPEN THE APP, LOG IN, AND START A GAME:

    public void openApp()          //uses spotlight to find and open the League app on an Apple computer
    {

        robot.keyPress(KeyEvent.VK_META);
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_META);
        robot.delay(25);
        typeString("League of Legends");
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(15000);    //time can be reduced if your computer is faster
    }


    public void loginCredentials(String username, String password)  //enters username and password provided in Runner
    {
        typeString(username);
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(200);
        typeString(password);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(20000);    //20 second delay to account for lag - can be reduced if your computer is faster
    }

    public void startBotGame()      //enters queue for a beginner co-op vs AI game
    {
        mouseClick(210,130); //PLAY button
        robot.delay(1000);
        mouseClick(230,190); //Co-op vs AI
        robot.delay(500);
        mouseClick(475,620); //Beginner
        robot.delay(500);
        mouseClick(600,780); //Confirm
        robot.delay(5000);
        mouseClick(600,780); //Find Match
        robot.delay(1000);     //again, this can be reduced slightly if your computer is fast
    }

    public void acceptMatch()       //looks for, then accepts a match
    {
        boolean matchEntered = false;
        Color defaultColor = robot.getPixelColor(725,650);
        Color currentColor;
        robot.mouseMove(725, 650); //this is a spot on the accept match button

        while(!matchEntered) {
            currentColor = robot.getPixelColor(725,650);
            if(currentColor.equals(defaultColor)){
                robot.delay(1000);   //checks if a match has appeared every 1 second - can be reduced
                System.out.println("Not ready yet");
            }
            else {                        //if (725, 650) becomes a new color, the robot hits accept
                robot.delay(300);
                mouseClick(725,650);
                matchEntered = true;
                robot.delay(10000);  //waiting for match to load in: can be reduced or increased based on max time
            }
        }
    }

    public void selectChamp(String champName)
    {
        mouseClick(850,200);
        robot.delay(200);
        typeString(champName);
        robot.delay(200);
        mouseClick(450,250);
        robot.delay(200);
        mouseClick(725,700);
        robot.delay(200);
    }
}
