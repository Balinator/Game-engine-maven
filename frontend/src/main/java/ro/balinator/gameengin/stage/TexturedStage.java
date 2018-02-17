package ro.balinator.gameengin.stage;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import ro.balinator.gameengin.managers.InputManager;
import ro.balinator.gameengin.managers.StageManager;
import ro.balinator.gameengin.renderer.Camera;
import ro.balinator.gameengin.renderer.loader.Loader;
import ro.balinator.gameengin.renderer.Renderer;
import ro.balinator.gameengin.shader.texture.TextureShader;
import ro.balinator.gameengin.stage.base.Stage;
import ro.balinator.gameengin.stage.base.StageEnum;
import ro.balinator.gameengine.entity.StaticEntity;
import ro.balinator.gameengine.entity.TexturedModel;

/**
 * Created by Balinator on 2018. 02. 06..
 */
public class TexturedStage extends Stage {
    private Loader loader;
    private Renderer renderer;
    private TextureShader textureShader;
    private StaticEntity<TexturedModel> texturedStaticEntity;
    private Camera camera;

    private void initVariables(){
        this.loader = new Loader();
        this.renderer = new Renderer();
        this.textureShader = new TextureShader();
        this.camera = new Camera();

        this.textureShader.start();
        this.textureShader.loadProjectionMatrix(this.renderer.getProjectionMatrix());
        this.textureShader.stop();
    }

    @Override
    protected void init() {
        initVariables();

        if(loader == null){
            System.out.println("null");
        }
        TexturedModel texturedModel = loader.loadToTexturedModel("resources/rect.obj", "resources/git.png");

        texturedStaticEntity = new StaticEntity<>(texturedModel,
                new Vector3f(0, 0, -1), new Vector3f(0, 0, 0), 1);

        InputManager.INSANCE.registerInput((window, key, scancode, action, mods) -> {
            if(StageManager.INSTANCE.isPrimaryStage(TexturedStage.this)) {
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
                            StageManager.INSTANCE.addCallLater(() -> StageManager.INSTANCE.setStage(StageEnum.COLORED_STAGE));
                        }
                        break;
                }
            }
        });
        InputManager.INSANCE.registerInput((window, xOffset, yOffset) -> {
            if(StageManager.INSTANCE.isPrimaryStage(TexturedStage.this)) {
                if (yOffset < 0) {
                    camera.getPosition().add(0, 0, -1);
                } else if (yOffset > 0) {
                    camera.getPosition().add(0, 0, 1);
                }
            }
        });
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
            textureShader.start();
            textureShader.loadViewMatrix(camera);
            renderer.render(texturedStaticEntity, textureShader);
            textureShader.stop();
    }

    @Override
    public void cleanUp() {
        loader.cleanUp();
        textureShader.cleanUp();
    }
}
