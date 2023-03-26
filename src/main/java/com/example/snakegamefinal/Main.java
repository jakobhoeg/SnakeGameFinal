package com.example.snakegamefinal;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

    static int blockSize = 20;
    static Paint backgroundColor = Paint.valueOf("#273F43");
    static Paint snakeColor = Paint.valueOf("#A2A58A");
    int width = 25, height = 20;
    int startLength = 3;
    long lastUpdate = System.nanoTime();
    boolean changedDirection = false;
    GameField field;
    static VBox root;
    int rotateAngle = 90;
    static double animationSpeed = 1000000000 / 7;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new VBox();
        root.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
        root.setPadding(new Insets(20));

        field = new GameField(width, height);
        field.addSnake(new Snake(startLength, field, 1));

        Label scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(scoreLabel.getFont().font(26));
        scoreLabel.setTextFill(Color.web("#FFFFFF"));

        root.getChildren().addAll(field, scoreLabel);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (field.snake.getDirection() != SnakeBlock.DOWN)
                        //field.snake.setDirection(SnakeBlock.UP);
                        setDirection(field.snake, SnakeBlock.UP);
                    break;
                case RIGHT:
                    if (field.snake.getDirection() != SnakeBlock.LEFT)
                        //field.snake.setDirection(SnakeBlock.RIGHT);
                        setDirection(field.snake, SnakeBlock.RIGHT);
                    break;
                case DOWN:
                    if (field.snake.getDirection() != SnakeBlock.UP)
                        //field.snake.setDirection(SnakeBlock.DOWN);
                        setDirection(field.snake, SnakeBlock.DOWN);
                    break;
                case LEFT:
                    if (field.snake.getDirection() != SnakeBlock.RIGHT)
                        //field.snake.setDirection(SnakeBlock.LEFT);
                        setDirection(field.snake, SnakeBlock.LEFT);
                    break;
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.setResizable(false);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= animationSpeed) {
                    field.update();
                    lastUpdate = now;
                    scoreLabel.setText("Score: " + field.score);
                    changedDirection = false;

                    // Rotate canvas if score is 200
                    if (field.score >= 200)
                    {
                        root.setRotate(rotateAngle);
                    }

                    if (field.isDead())
                    {
                        stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game Over");
                        alert.setContentText("Your score was " + field.score);
                        Platform.runLater(alert::showAndWait);

                        alert.setOnHidden(event -> {
                            root.getChildren().clear();
                            field = new GameField(width, height);
                            field.addSnake(new Snake(startLength, field, 1));
                            scoreLabel.setText("Score: 0");
                            field.score = 0;
                            root.getChildren().addAll(field, scoreLabel);
                            root.setRotate(0);
                            start();
                        });

                    }
                }
            }
        };
        timer.start();
    }

    public void setDirection(Snake snake, int direction) {
        if (!changedDirection)
        {
            snake.setDirection(direction);
            changedDirection = true;
        }
    }
}
