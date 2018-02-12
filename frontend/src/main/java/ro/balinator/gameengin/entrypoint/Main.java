package ro.balinator.gameengin.entrypoint;

import ro.balinator.gameengin.display.DisplayManager;
import ro.balinator.gameengin.stage.StageEnum;
import ro.balinator.gameengin.stage.StageManager;

/**
 * Created by Balinator on 2017. 12. 28..
 */
public class Main {
    public static void main(String[] args) {
        DisplayManager displayManager = DisplayManager.INSTANCE;
        displayManager.createDisplay();

        StageManager stageManager = StageManager.INSTANCE;
        stageManager.setStage(StageEnum.TEST_STAGE);

        while (!displayManager.isCloseRequested()) {
            stageManager.frame();
            displayManager.updateDisplay();
        }

        stageManager.cleanUp();

        displayManager.closeDisplay();
    }
}
