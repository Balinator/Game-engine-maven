package ro.balinator.gameengine.entity.model;

import lombok.Getter;

/**
 * Created by Balinator on 2018. 01. 27..
 */
@Getter
public class ModelTexture {
    private int textureId;

    public ModelTexture(int textureId) {
        this.textureId = textureId;
    }
}
