package com.example.snakegamefinal;

public enum FoodColors {
    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF"),
    YELLOW("#FFFF00"),
    PURPLE("#FF00FF"),
    ORANGE("#FFA500"),
    BLACK("#000000"),
    WHITE("#FFFFFF");

    private String color;

    FoodColors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
