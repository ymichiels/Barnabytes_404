package sample.util;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TabPane extends Application {

    private static final String TAB_0 = "Toutes les actions du match";
    private static final String TAB_01 = "Actions importantes";
    private static final String msg = TAB_0;
    private final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tabs");

        JFXTabPane tabPane = new JFXTabPane();

        Tab tab = new Tab();
        tab.setText(msg);
        tab.setContent(new Label(TAB_0));
        tab.setContent(new ScrollPane());
        tab.setContent(new ListActionCell());
        tabPane.getTabs().add(tab);
        tabPane.setPrefSize(300, 200);
        Tab tab1 = new Tab();
        tab1.setText(TAB_01);
        tab1.setContent(new Label(TAB_01));

        tabPane.getTabs().add(tab1);

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(1);

        tabPane.setMaxWidth(500);
        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-padding:20");

        Group root = new Group();
        Scene scene = new Scene(root, 700, 250);
        root.getChildren().addAll(hbox);
        scene.getStylesheets().add(TabPane.class.getResource("/css/style.css").toExternalForm());

        primaryStage.setTitle("JFX Tabs Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String nextSessionId() {
        return new BigInteger(50, random).toString(16);
    }
}
