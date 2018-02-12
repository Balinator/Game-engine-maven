package ro.balinator.gameengin.entrypoint;

import ro.balinator.gameengin.managers.DisplayManager;
import ro.balinator.gameengin.managers.InputManager;
import ro.balinator.gameengin.stage.base.StageEnum;
import ro.balinator.gameengin.managers.StageManager;

/**
 * Created by Balinator on 2017. 12. 28..
 */
public class Main {
    public static void main(String[] args) {
        DisplayManager displayManager = DisplayManager.INSTANCE;
        displayManager.createDisplay();

        StageManager stageManager = StageManager.INSTANCE;
        stageManager.setStage(StageEnum.TEXTURED_STAGE);

        InputManager inputManager = InputManager.INSANCE;
        inputManager.initImput();

        while (!displayManager.isCloseRequested()) {
            stageManager.frame();
            displayManager.updateDisplay();
        }

        stageManager.cleanUp();

        displayManager.closeDisplay();
    }
}
