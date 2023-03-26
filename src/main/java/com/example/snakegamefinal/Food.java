package com.example.snakegamefinal;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {

    int posX;

    int posY;

    String type;

    String color;

    public Food(int x, int y, String type, String color) {
        super(Main.blockSize, Main.blockSize);
        posX = x;
        posY = y;
        setTranslateX(posX * Main.blockSize);
        setTranslateY(posY * Main.blockSize);
        this.type = type;
        this.color = color;
        setFill(Color.web(color));
    }



}
