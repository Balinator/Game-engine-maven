package ro.balinator.gameengin.stage;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import ro.balinator.gameengin.managers.InputManager;
import ro.balinator.gameengin.managers.StageManager;
import ro.balinator.gameengin.renderer.Camera;
import ro.balinator.gameengin.renderer.loader.Loader;
import ro.balinator.gameengin.renderer.Renderer;
import ro.balinator.gameengin.shader.color.ColourShader;
import ro.balinator.gameengin.stage.base.Stage;
import ro.balinator.gameengin.stage.base.StageEnum;
import ro.balinator.gameengine.entity.ColouredModel;
import ro.balinator.gameengine.entity.StaticEntity;

/**
 * Created by Balinator on 2018. 02. 12..
 */
public class ColoredStage extends Stage {
    private Loader loader;
    private Renderer renderer;
    private ColourShader colourShader;
    private StaticEntity<ColouredModel> colouredStaticEntity;
    private Camera camera;

    @Override
    protected void init() {
        initVariables();

        float[] colors = {
                0,    0, 1, //v1
                0,    0, 0.5f, //v2
                1,    0, 0, //v3
                0.5f, 0, 0 //v4
        };

        if (loader == null) {
            System.out.println("null");
        }
        ColouredModel colouredModel = loader.loadToColouredModel("resources/rect.obj", colors);

        colouredStaticEntity = new StaticEntity<>(colouredModel,
                new Vector3f(0, 0, -1), new Vector3f(0, 0, 0), 1);

        InputManager.INSANCE.registerInput((window, key, scancode, action, mods) -> {
            if (StageManager.INSTANCE.isPrimaryStage(ColoredStage.this)) {
                switch (key) {
                    case GLFW.GLFW_KEY_W:
                        camera.getPosition().add(0, -1, 0);
                        break;
                    case GLFW.GLFW_KEY_S:
                        camera.getPosition().add(0, 1, 0);
                        break;
                    case GLFW.GLFW_KEY_A:
                        camera.getPosition().add(1, 0, 0);
                        break;
                    case GLFW.GLFW_KEY_D:
                        camera.getPosition().add(-1, 0, 0);
                        break;
                    case GLFW.GLFW_KEY_P:
                        if (action == GLFW.GLFW_RELEASE) {
                            StageManager.INSTANCE.addCallLater(() -> StageManager.INSTANCE.setStage(StageEnum.TEXTURED_STAGE));
                        }
                        break;
                }
            }
        });
        InputManager.INSANCE.registerInput((window, xOffset, yOffset) -> {
            if (StageManager.INSTANCE.isPrimaryStage(ColoredStage.this)) {
                if (yOffset < 0) {
                    camera.getPosition().add(0, 0, -1);
                } else if (yOffset > 0) {
                    camera.getPosition().add(0, 0, 1);
                }
            }
        });
    }

    private void initVariables() {
        this.loader = new Loader();
        this.renderer = new Renderer();
        this.colourShader = new ColourShader();
        this.camera = new Camera();

        this.colourShader.start();
        this.colourShader.loadProjectionMatrix(this.renderer.getProjectionMatrix());
        this.colourShader.stop();
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
        colourShader.start();
        colourShader.loadViewMatrix(camera);
        renderer.render(colouredStaticEntity, colourShader);
        colourShader.stop();
    }

    @Override
    public void cleanUp() {
        loader.cleanUp();
        colourShader.cleanUp();
    }
}
