package ro.balinator.gameengin.shader;

import lombok.Getter;

/**
 * Created by Balinator on 2018. 01. 27..
 */
@Getter
public enum UniformEnum {

    TRANSFORMATION_MATRIX("transformationMatrix")

    ;

    UniformEnum(String name) {
        this.name = name;
    }

    private String name;
}
