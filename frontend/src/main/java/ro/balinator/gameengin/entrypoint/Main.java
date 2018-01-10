package ro.balinator.gameengin.entrypoint;

import ro.balinator.gameengin.display.DisplayManager;

/**
 * Created by Balinator on 2017. 12. 28..
 */
public class Main {
    public static void main(String[] args){
        DisplayManager displayManager = DisplayManager.INSTANCE;
        displayManager.createDisplay();

        while(!displayManager.isCloseRequested()){

            displayManager.updateDisplay();
        }

        displayManager.closeDisplay();
    }
}
