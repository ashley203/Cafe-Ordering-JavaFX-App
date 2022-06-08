module com.example.rutgerscafe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rutgerscafe to javafx.fxml;
    exports com.example.rutgerscafe;
}