package General;
import java.awt.*;
import java.awt.event.KeyEvent;



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

    public void openApp()
    {
        try {
            robot = new Robot();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("robot creation failed in General.EnterLeague");
        }

        robot.keyPress(KeyEvent.VK_META);
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_META);
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_L);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_L);
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(25);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(12000);
    }

    public void typeString(String s){

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(25);
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));
                robot.delay(25);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if (Character.isLetterOrDigit(c)){
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));
            }
            else {
                //what do I do here?
            }
        }
        robot.delay(10);
    }

    public void loginCredentials(String username, String password)
    {
        typeString(username);
        robot.delay(25);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(25);
        typeString(password);
        robot.delay(25);
    }

}
