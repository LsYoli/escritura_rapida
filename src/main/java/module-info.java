module com.example.funcion {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.funcion to javafx.fxml;
    exports com.example.funcion;
}