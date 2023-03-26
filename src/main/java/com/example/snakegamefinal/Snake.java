package com.example.snakegamefinal;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake {
    ArrayList<SnakeBlock> snakeBlocks = new ArrayList<>();
    SnakeBlock head;
    SnakeBlock tail;
    int speed = 1;

    public Snake(int initialLength, GameField field, int speed) {
        int initialPositionX = field.getW() / 2;
        int initialPositionY = field.getH() / 2;
        head = new SnakeBlock(initialPositionX, initialPositionY, null, field);
        snakeBlocks.add(head);
        this.speed = speed;

        head.setFill(Main.snakeColor);

        tail = head;
        for (int i = 1; i < initialLength; i++) {
            SnakeBlock block = new SnakeBlock(initialPositionX - i,
                    initialPositionY, tail, field);
            block.setFill(Main.snakeColor);
            snakeBlocks.add(block);
            tail = block;
        }
    }

    public int getDirection() {
        return head.direction;
    }

    public void setDirection(int direction) {
        head.direction = direction;
    }

}
