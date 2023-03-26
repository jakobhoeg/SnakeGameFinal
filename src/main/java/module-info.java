module com.example.snakegamefinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakegamefinal to javafx.fxml;
    exports com.example.snakegamefinal;
}