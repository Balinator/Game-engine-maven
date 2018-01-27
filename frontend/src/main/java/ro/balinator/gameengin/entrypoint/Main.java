package ro.balinator.gameengin.entrypoint;

import ro.balinator.gameengin.display.DisplayManager;
import ro.balinator.gameengin.renderer.Loader;
import ro.balinator.gameengin.renderer.Renderer;
import ro.balinator.gameengin.shader.colorShader.ColourShader;
import ro.balinator.gameengin.shader.textureShader.TextureShader;
import ro.balinator.gameengine.entity.ColouredModel;
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
        TextureShader textureShader = new TextureShader();
        ColourShader colourShader = new ColourShader();

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

        float[] colors ={
                0,0,1, //v1
                0,0,0.5f, //v2
                1,0,0, //v3
                0.5f,0,0 //v4
        };

        TexturedModel texturedModel = loader.loadToTexturedModel(vertices,indices,textureCordinates,"resources/git.png");
        ColouredModel colouredModel = loader.loadToColouredModel(vertices,indices,colors);

        int counter = 0;

        while(!displayManager.isCloseRequested()){
            renderer.prepare();

            //TODO: gameLogic

            //TODO: render
            if(counter < 1000) {
                textureShader.start();
                renderer.render(texturedModel);
                textureShader.stop();
            }else {
                colourShader.start();
                renderer.render(colouredModel);
                colourShader.stop();
            }

            counter = (counter + 1) % 2000;
            displayManager.updateDisplay();
        }

        loader.cleanUp();
        textureShader.cleanUp();

        displayManager.closeDisplay();
    }
}
