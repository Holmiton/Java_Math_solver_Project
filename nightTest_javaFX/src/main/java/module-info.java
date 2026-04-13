module com.example.nighttest_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nighttest_javafx to javafx.fxml;
    exports com.example.nighttest_javafx;
}