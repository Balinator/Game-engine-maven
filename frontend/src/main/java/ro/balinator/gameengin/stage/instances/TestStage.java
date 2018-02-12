package ro.balinator.gameengin.stage.instances;

import org.joml.Vector3f;
import ro.balinator.gameengin.renderer.Loader;
import ro.balinator.gameengin.renderer.Renderer;
import ro.balinator.gameengin.shader.color.ColourShader;
import ro.balinator.gameengin.shader.texture.TextureShader;
import ro.balinator.gameengin.stage.Stage;
import ro.balinator.gameengine.entity.ColouredModel;
import ro.balinator.gameengine.entity.StaticEntity;
import ro.balinator.gameengine.entity.TexturedModel;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public class TestStage extends Stage {
    private Loader loader;
    private Renderer renderer;
    private TextureShader textureShader;
    private ColourShader colourShader;
    private StaticEntity<TexturedModel> texturedStaticEntity;
    private StaticEntity<ColouredModel> colouredStaticEntity;

    private void initVariables(){
        this.loader = new Loader();
        this.renderer = new Renderer();
        this.textureShader = new TextureShader();
        this.colourShader = new ColourShader();
    }

    @Override
    protected void init() {
        initVariables();

        float[] vertices = {
                -0.5f, 0.5f,  0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f,  -0.5f, 0f,//v2
                0.5f,  0.5f,  0f,//v3
        };

        int[] indices = {
                0, 1, 3,//top left triangle (v0, v1, v3)
                3, 1, 2//bottom right triangle (v3, v1, v2)
        };

        float[] textureCordinates = {
                0, 0, //v1
                0, 1, //v2
                1, 1, //v3
                1, 0  //v4
        };

        float[] colors = {
                0, 0, 1, //v1
                0, 0, 0.5f, //v2
                1, 0, 0, //v3
                0.5f, 0, 0 //v4
        };

        if(loader == null){
            System.out.println("null");
        }
        TexturedModel texturedModel = loader.loadToTexturedModel(vertices, indices, textureCordinates, "resources/git.png");
        ColouredModel colouredModel = loader.loadToColouredModel(vertices, indices, colors);

        texturedStaticEntity = new StaticEntity<>(texturedModel,
                new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 1);

        colouredStaticEntity = new StaticEntity<>(colouredModel,
                new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 1);
    }

    @Override
    public void prepare() {
        renderer.prepare();
    }

    @Override
    public void logic() {

    }

    @Override
    public void render() {
        if (getStageTime() % 2000 < 1000) {
            textureShader.start();
            renderer.render(texturedStaticEntity, textureShader);
            textureShader.stop();
        } else {
            colourShader.start();
            renderer.render(colouredStaticEntity, colourShader);
            colourShader.stop();
        }
    }

    @Override
    public void cleanUp() {
        loader.cleanUp();
        textureShader.cleanUp();
        colourShader.cleanUp();
    }
}
