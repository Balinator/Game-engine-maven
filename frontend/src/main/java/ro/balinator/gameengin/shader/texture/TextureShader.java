package ro.balinator.gameengin.shader.texture;

import org.joml.Matrix4f;
import ro.balinator.gameengin.shader.UniformEnum;
import ro.balinator.gameengin.shader.ShaderProgram;

/**
 * Created by Balinator on 2018. 01. 24..
 */
public class TextureShader extends ShaderProgram {
    private static final String VERTEX_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/texture/vertexShader.vert";
    private static final String FRAGMENT_FILE = "frontend/src/main/java/ro/balinator/gameengin/shader/texture/fragmentShader.frag";

    public TextureShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }

    @Override
    protected void getAllUniformLocations() {
        putUniformId(UniformEnum.TRANSFORMATION_MATRIX);
        putUniformId(UniformEnum.PROJECTION_MATRIX);
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix4f(super.getUniformId(UniformEnum.TRANSFORMATION_MATRIX), matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix4f(super.getUniformId(UniformEnum.PROJECTION_MATRIX), matrix);
    }
}
