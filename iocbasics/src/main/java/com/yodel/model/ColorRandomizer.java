package com.yodel.model;

import java.util.Random;

public class ColorRandomizer {

    ColorEnum colorException;

    public ColorEnum randomColor() {
        ColorEnum[] allColors = ColorEnum.values();
        ColorEnum ret = null;
        do {
            ret = allColors[new Random().nextInt(allColors.length - 1)];
        }
        while (colorException != null && colorException == ret);

        return ret;
    }

    public ColorEnum exceptColor(ColorEnum ex) {
        ColorEnum ret = null;
        do {
            ret = randomColor();
        }
        while (ex != null && ex == ret);
        return ret;
    }

    public void setColorException(ColorEnum colorException) {
        this.colorException = colorException;
    }

}
