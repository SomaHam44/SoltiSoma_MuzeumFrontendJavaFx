module hu.petrik.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens hu.petrik.muzeumfrontendjavafx to javafx.fxml;
    exports hu.petrik.muzeumfrontendjavafx;
    exports hu.petrik.muzeumfrontendjavafx.controllers;
    opens hu.petrik.muzeumfrontendjavafx.controllers to javafx.fxml;
}