package ro.balinator.gameengin.entrypoint;

import ro.balinator.gameengin.display.DisplayManager;
import ro.balinator.gameengin.renderer.Loader;
import ro.balinator.gameengin.renderer.Renderer;
import ro.balinator.gameengin.shader.StaticShader;
import ro.balinator.gameengine.entity.ModelTexture;
import ro.balinator.gameengine.entity.RawModel;
import ro.balinator.gameengine.entity.TexturedModel;

/**
 * Created by Balinator on 2017. 12. 28..
 */
public class Main {
    public static void main(String[] args){
        DisplayManager displayManager = DisplayManager.INSTANCE;
        displayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        };

        int[] indices = {
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        };

        float[] textureCordinates = {
                0,0, //v1
                0,1, //v2
                1,1, //v3
                1,0  //v4
        };

        ModelTexture modelTexture = new ModelTexture(loader.loadTexture("resources/git.png"));
        RawModel rawModel = loader.loadToVao(vertices,textureCordinates,indices);

        TexturedModel texturedModel = new TexturedModel(rawModel,modelTexture);

        while(!displayManager.isCloseRequested()){
            renderer.prepare();

            //TODO: gameLogic

            //TODO: render
            shader.start();
            renderer.render(texturedModel);
            shader.stop();

            displayManager.updateDisplay();
        }

        loader.cleanUp();
        shader.cleanUp();

        displayManager.closeDisplay();
    }
}
