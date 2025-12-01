module com.trabalhoprog1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.trabalhoprog1 to javafx.fxml;
    exports com.trabalhoprog1;
}
