package ro.balinator.gameengine.entity;

import lombok.Getter;

/**
 * Created by Balinator on 2018. 01. 24..
 */
@Getter
public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }
}
