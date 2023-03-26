package com.example.snakegamefinal;

import javafx.scene.shape.Rectangle;

public class SnakeBlock extends Rectangle {

    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    boolean velocity = false;
    int speed = 1;
    boolean bigHead;
    int posX, posY, oldPosX, oldPosY;

    SnakeBlock previousBlock;
    int direction = RIGHT;

    int maxX, maxY;

    public SnakeBlock(int x, int y, SnakeBlock previousBlock, GameField field) {
        super(Main.blockSize, Main.blockSize);
        posX = x;
        posY = y;
        setTranslateX(posX * Main.blockSize);
        setTranslateY(posY * Main.blockSize);
        this.previousBlock = previousBlock;
        maxX = field.getW();
        maxY = field.getH();
    }

    public void update() {
        // Logical update
        oldPosX = posX;
        oldPosY = posY;
        if (previousBlock == null) {
            switch (direction) {
                case UP:
                    moveUp();
                    break;
                case RIGHT:
                    moveRight();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case LEFT:
                    moveLeft();
                    break;
            }
        }
        else {
            posX = previousBlock.oldPosX;
            posY = previousBlock.oldPosY;
        }
        // Visual update
        updatePosition();
    }

    public void updatePosition() {
        setTranslateX(posX * Main.blockSize);
        setTranslateY(posY * Main.blockSize);
    }

    public void moveUp() {
        posY--;
        if (posY < 0) {
            posY = maxY - 1;
        }
    }

    public void moveRight() {
        posX++;
        if (posX > maxX - 1) {
            posX = 0;
        }
    }

    public void moveDown() {
        posY++;
        if (posY > maxY - 1) {
            posY = 0;
        }
    }

    public void moveLeft() {
        posX--;
        if (posX < 0) {
            posX = maxX - 1;
        }
    }

    public void setBigHead(boolean bigHead) {
        this.bigHead = bigHead;
        if (bigHead) {
            setWidth(Main.blockSize * 2);
            setHeight(Main.blockSize * 2);
        }
        else {
            setWidth(Main.blockSize);
            setHeight(Main.blockSize);
        }
    }

    public void setVelocity(Boolean velocity) {
        this.velocity = velocity;
        if (velocity) {
            speed = 2;
            Main.animationSpeed = 1000000000 / 16;
        }
        else {
            speed = 1;
        }
    }






}
