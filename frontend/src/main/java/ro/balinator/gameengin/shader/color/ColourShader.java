package ro.balinator.gameengin.shader.color;

import org.joml.Matrix4f;
import ro.balinator.gameengin.renderer.Camera;
import ro.balinator.gameengin.shader.ShaderMaths;
import ro.balinator.gameengin.shader.ShaderProgram;
import ro.balinator.gameengin.shader.UniformEnum;

/**
 * Created by Balinator on 2018. 01. 24..
 */
public class ColourShader extends ShaderProgram{
    private static final String VERTEX_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/color/vertexShader.vert";
    private static final String FRAGMENT_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/color/fragmentShader.frag";

    public ColourShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "colour");
    }

    @Override
    protected void getAllUniformLocations() {
        putUniformId(UniformEnum.TRANSFORMATION_MATRIX);
        putUniformId(UniformEnum.PROJECTION_MATRIX);
        putUniformId(UniformEnum.VIEW_MATRIX);
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix4f(super.getUniformId(UniformEnum.TRANSFORMATION_MATRIX), matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix4f(super.getUniformId(UniformEnum.PROJECTION_MATRIX), matrix);
    }

    public void loadViewMatrix(Camera camera){
        super.loadMatrix4f(super.getUniformId(UniformEnum.VIEW_MATRIX), ShaderMaths.createViewMatrix(camera));
    }
}
