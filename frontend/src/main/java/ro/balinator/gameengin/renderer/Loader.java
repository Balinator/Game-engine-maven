package ro.balinator.gameengin.renderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import ro.balinator.gameengine.entity.ColouredModel;
import ro.balinator.gameengine.entity.TexturedModel;
import ro.balinator.gameengine.entity.model.ModelTexture;
import ro.balinator.gameengine.entity.model.RawModel;


public class Loader {

    private ArrayList<Integer> vaos = new ArrayList<>();
    private ArrayList<Integer> vbos = new ArrayList<>();
    private ArrayList<Integer> textures = new ArrayList<>();

    public RawModel loadToRawModel(float[] positions, int[] indices) {
        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        unbindVAO();
        return new RawModel(vaoId, indices.length);
    }

    public TexturedModel loadToTexturedModel(float[] positions, int[] indices, float[] textureCoordinates, String fileName) {
        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1,2, textureCoordinates);
        unbindVAO();
        return new TexturedModel(new RawModel(vaoId,indices.length), new ModelTexture(loadTexture(fileName)));
    }

    public ColouredModel loadToColouredModel(float[] positions, int[] indices, float[] colors) {
        int vaoId = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, positions);
        storeDataInAttributeList(1,3, colors);
        unbindVAO();
        return new ColouredModel(new RawModel(vaoId,indices.length));
    }

    public int loadTexture(String fileName){
        int textureID = TextureLoader.loadTexture(fileName);
        textures.add(textureID);
        return textureID;
    }

    public void cleanUp() {
        for (int i : vaos) {
            GL30.glDeleteVertexArrays(i);
        }
        for (int i : vbos) {
            GL15.glDeleteBuffers(i);
        }
        for (int i : textures){
            GL11.glDeleteTextures(i);
        }
    }

    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices)
    {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private IntBuffer storeDataInIntBuffer(int[] data)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
