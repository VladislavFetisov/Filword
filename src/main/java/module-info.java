module Fillwords {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    exports FillWordApp.Controllers;
    exports FillWordApp.View;
    opens FillWordApp.View to javafx.graphics;
    opens FillWordApp.Controllers to javafx.controls;
}