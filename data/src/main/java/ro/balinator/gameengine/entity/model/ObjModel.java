package ro.balinator.gameengine.entity.model;

import lombok.Getter;

/**
 * Created by Balinator on 2018. 02. 17..
 */
@Getter
public class ObjModel {
    private float[] texturesArray = null;
    private float[] normalsArray = null;
    private float[] verticesArray;
    private int[] indicesArray;

    public ObjModel(int[] indicesArray, float[] verticesArray, float[] normalsArray, float[] texturesArray) {
        this.texturesArray = texturesArray;
        this.normalsArray = normalsArray;
        this.verticesArray = verticesArray;
        this.indicesArray = indicesArray;
    }
}
