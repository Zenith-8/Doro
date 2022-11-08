module com.zenith.doro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.zenith.doro to javafx.fxml;
    exports com.zenith.doro;
}