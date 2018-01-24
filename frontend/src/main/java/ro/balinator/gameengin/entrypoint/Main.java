package ro.balinator.gameengin.entrypoint;

import ro.balinator.gameengin.display.DisplayManager;
import ro.balinator.gameengin.renderer.Loader;
import ro.balinator.gameengin.renderer.Renderer;
import ro.balinator.gameengine.entity.RawModel;

/**
 * Created by Balinator on 2017. 12. 28..
 */
public class Main {
    public static void main(String[] args){
        DisplayManager displayManager = DisplayManager.INSTANCE;
        displayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        RawModel rawModel = loader.loadToVao(vertices);

        while(!displayManager.isCloseRequested()){
            renderer.prepare();

            //TODO: gameLogic

            //TODO: render
            renderer.render(rawModel);

            displayManager.updateDisplay();
        }

        loader.cleanUp();

        displayManager.closeDisplay();
    }
}
