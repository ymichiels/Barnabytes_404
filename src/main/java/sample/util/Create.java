package sample.util;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Main;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Create {

    public static Label createLabel(String name) {
        Label label = new Label(name);
        label.setId(name);

        return label;
    }

    public static Stage createChildStage(Stage primaryStage, String title, Node content, int width, int height, boolean resizable) throws Exception {
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(Main.class.getResourceAsStream("/svg/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();
        Stage childStage = new Stage();
        childStage.setWidth(width);
        childStage.setHeight(height);
        //childStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - childStage.getWidth() / 2);
        //childStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - childStage.getHeight() / 2);
        childStage.setResizable(resizable);
        childStage.initModality(Modality.WINDOW_MODAL);
        childStage.initOwner(primaryStage);
        JFXDecorator decorator = new JFXDecorator(childStage, content,true,true,true);
        decorator.setOnCloseButtonAction(() ->{
            childStage.close();
        });
        SVGGlyph glyphSportHandball = SVGGlyphLoader.getIcoMoonGlyph("icomoon.svg.sports_handball");
        decorator.setGraphic(glyphSportHandball);
        Scene scene = new Scene(decorator);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(Main.class.getResource("/css/style.css").toExternalForm());
        childStage.setScene(scene);
        childStage.setTitle(title);
        childStage.getIcons().add(new Image("/img/handball_logo_main_app.png"));
        return childStage;
    }

    public static Callback<DatePicker, DateCell> getFutureDayCellsDisabled() {
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        DayOfWeek day = DayOfWeek.from(item);
                        if (day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY) {
                            this.setTextFill(Color.web("#4CAF50"));
                        }

                        if (item.isAfter(LocalDate.now())) {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
