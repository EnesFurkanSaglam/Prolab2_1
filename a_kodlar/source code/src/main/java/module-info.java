module org.efs.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    opens org.efs.demo to javafx.fxml;
    exports org.efs.demo;
}