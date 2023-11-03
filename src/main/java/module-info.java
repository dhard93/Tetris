module com.example.tetris
{
    requires javafx.controls;
    requires java.desktop;
    requires java.sql;
    requires javafx.media;
    opens com.example.tetris;
    exports com.example.tetris;
}