package ro.balinator.gameengine.entity;

import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;
import ro.balinator.gameengine.entity.interfaces.Entity;
import ro.balinator.gameengine.entity.interfaces.Model;

/**
 * Created by Balinator on 2018. 01. 27..
 */
@Getter
@Setter
public class StaticEntity<T extends Model> implements Entity {
    private T model;
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public StaticEntity(T model, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public StaticEntity(T model, Vector3f position, Vector3f rotation, float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = new Vector3f(scale);
    }

    public void translate(Vector3f position){
        this.position.add(position);
    }

    public void rotate(Vector3f rotation){
        this.rotation.add(rotation);
    }

    public void scale(Vector3f scale){
        this.scale.add(scale);
    }

}
