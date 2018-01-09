package com.yodel.model;

public class BasicPOJO {

    private String name;
    private ColorEnum color;

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
        System.out.println("setName:" + name);
        this.name = name;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        System.out.println("setColor:" + color);
        this.color = color;
    }
}
