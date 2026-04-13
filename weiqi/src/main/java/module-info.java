module com.example.weiqi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.weiqi to javafx.fxml;
    exports com.example.weiqi;
}