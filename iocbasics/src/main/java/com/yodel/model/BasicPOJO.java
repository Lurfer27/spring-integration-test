package com.yodel.model;

import com.yodel.model.ColorEnum;

public class BasicPOJO {

    public String name;
    public ColorEnum color;

    public BasicPOJO() {
    }

    public BasicPOJO(String name, ColorEnum color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }
}
