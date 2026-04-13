module com.example.nighttest_javafx_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nighttest_javafx_1 to javafx.fxml;
    exports com.example.nighttest_javafx_1;
}