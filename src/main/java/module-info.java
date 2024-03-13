module com.example.lemmikkipeli {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lemmikkipeli to javafx.fxml;
    exports com.example.lemmikkipeli;
}