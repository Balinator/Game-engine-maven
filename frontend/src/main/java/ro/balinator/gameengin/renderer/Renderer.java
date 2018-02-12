package ro.balinator.gameengin.renderer;

import lombok.Getter;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import ro.balinator.gameengin.managers.DisplayManager;
import ro.balinator.gameengin.shader.ShaderMaths;
import ro.balinator.gameengin.shader.color.ColourShader;
import ro.balinator.gameengin.shader.texture.TextureShader;
import ro.balinator.gameengine.entity.ColouredModel;
import ro.balinator.gameengine.entity.StaticEntity;
import ro.balinator.gameengine.entity.TexturedModel;

/**
 * Created by Balinator on 2018. 01. 24..
 */
public class Renderer {

    private static final float FEALD_OF_VIEW = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

    @Getter
    private Matrix4f projectionMatrix;

    public Renderer() {
        createProjectonMatrix();
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1);
    }

    public void render(StaticEntity<TexturedModel> staticEntity, TextureShader shader) {
        GL30.glBindVertexArray(staticEntity.getModel().getRawModel().getVaoId());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        shader.loadTransformationMatrix(ShaderMaths.createTransformationMatrix(staticEntity.getPosition(),
                staticEntity.getRotation(), staticEntity.getScale()));
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, staticEntity.getModel().getModelTexture().getTextureId());
        GL11.glDrawElements(GL11.GL_TRIANGLES, staticEntity.getModel().getRawModel().getVertexCount(),
                GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void render(StaticEntity<ColouredModel> staticEntity, ColourShader shader) {
        GL30.glBindVertexArray(staticEntity.getModel().getRawModel().getVaoId());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);

        shader.loadTransformationMatrix(ShaderMaths.createTransformationMatrix(staticEntity.getPosition(),
                staticEntity.getRotation(), staticEntity.getScale()));
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, staticEntity.getModel().getRawModel().getVertexCount(),
                GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    private void createProjectonMatrix(){
        float aspectRatio = (float) DisplayManager.INSTANCE.getSWeight() / (float) DisplayManager.INSTANCE.getSHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FEALD_OF_VIEW / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
        projectionMatrix.m33(0);
    }
}
