package com.example.snakegamefinal;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Timer;

public class GameField extends Pane {
    private int w,h;

    ArrayList<SnakeBlock> snakeBlocks = new ArrayList<>();
    int score = 0;
    Food f;
    Snake snake;

    int timer = 0;
    String type = "";
    String color = "";

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public GameField(int width, int height) {
        w = width;
        h = height;
        setMinSize(w * Main.blockSize, h * Main.blockSize);
        setBackground(new Background(new BackgroundFill(Main.backgroundColor, null, null)));
        setBorder(new Border(new BorderStroke(Color.web("#000000"), BorderStrokeStyle.SOLID,
                null, new BorderWidths(1))));
        addFood();
        timer = 0;
    }

    public void addSnake(Snake s) {
        snake = s;
        for (SnakeBlock block : snake.snakeBlocks) {
            addSnakeBlock(block);
        }
    }

    private void addSnakeBlock(SnakeBlock block) {
        getChildren().add(block);
        block.setFill(Main.snakeColor);
        snakeBlocks.add(block);
    }

    public void addFood() {
        int randomX = (int) (Math.random() * w);
        int randomY = (int) (Math.random() * h);
        int randomType = (int) (Math.random() * 3);
        switch (randomType) {
            case 0:
                type = "NORMAL";
                color = "#A33E33";
                break;
            case 1:
                type = "VELOCITY";
                color = "#E5CD4E";
                break;
            case 2:
                type = "BIGHEAD";
                color = "#85C2CD";
                break;
        }
        Food food = new Food(randomX, randomY, type, color);
        getChildren().add(food);
        getChildren().remove(f);
        this.f = food;
    }


    public void update() {
        // Timer to dissappear food and add new one
        timer++;
        if (timer > 30 && !isDead() && !isEaten(f))
        {
            addFood();
            timer = 0;
        }

        // Update method to move the snake
        for (SnakeBlock block : snakeBlocks) {
            block.update();
        }

        // Update method to check if the snake has eaten the food
        if (isEaten(f)) {
            if (f.type.equals("VELOCITY")) {
                snake.head.setVelocity(true);
            } else if (f.type.equals("BIGHEAD")) {
                snake.head.setBigHead(true);
            }
            score+= 10;
            timer = 0;
            addFood();
            addNewBlock();
        }

        // Big head timer - return to normal after 4 seconds
        if (snake.head.bigHead) {
            Timer timer = new Timer();
            timer.schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    snake.head.setBigHead(false);
                }
            }, 4000);
        }

        if (snake.head.velocity) {
            Timer timer = new Timer();
            timer.schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    snake.head.setVelocity(false);
                    Main.animationSpeed = 1000000000 / 7;
                }
            }, 4000);
        }

    }

    public void addNewBlock() {
        SnakeBlock newBlock = new SnakeBlock(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
        snake.tail = newBlock;
        addSnakeBlock(newBlock);
    }

    public boolean isEaten(Food f) {
        return f.posX == snake.head.posX && f.posY == snake.head.posY;
    }

    public boolean isDead() {
        for (SnakeBlock block : snakeBlocks) {
            if (block != snake.head && block.posX == snake.head.posX && block.posY == snake.head.posY) {
                return true;
            }
        }
        return false;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}
