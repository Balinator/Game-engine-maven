package ro.balinator.gameengin.renderer;

import lombok.Getter;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import ro.balinator.gameengin.display.DisplayManager;

/**
 * Created by Balinator on 2018. 02. 12..
 */
@Getter
public class Camera {

    private Vector3f position;
    private Vector3f rotation;

    public Camera() {
        this.position = new Vector3f();
        this.rotation = new Vector3f();

        GLFW.glfwSetKeyCallback(DisplayManager.INSTANCE.getWindow(), (window, key, scancode, action, mods) -> {
            switch (key){
                case GLFW.GLFW_KEY_W:
                    position.add(0,-1,0);
                    break;
                case GLFW.GLFW_KEY_S:
                    position.add(0,1,0);
                    break;
                case GLFW.GLFW_KEY_A:
                    position.add(1,0,0);
                    break;
                case GLFW.GLFW_KEY_D:
                    position.add(-1,0,0);
                    break;
            }
        });
        GLFW.glfwSetScrollCallback(DisplayManager.INSTANCE.getWindow(), (window, xOffset, yOffset) -> {
            if(yOffset < 0){
                position.add(0,0,-1);
            }else if (yOffset > 0){
                position.add(0,0,1);
            }
        });
    }

    public void move(){
        GL.createCapabilities();
    }
}
