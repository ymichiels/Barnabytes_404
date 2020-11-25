package sample.controller.components.global;

import com.jfoenix.controls.JFXButton;
import com.sun.glass.ui.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public final class Global {

    private static User connectedUser = new User();
    public static String appName = "Handball IHM";
    public static String navFrom = "";
    public static Double appVersion = 1.9;
    private static Stage stage;


    public static User getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(User connectedUser) {
        Global.connectedUser = connectedUser;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Global.stage = stage;
    }

    public static void showInfoMessage(String header, String content) {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle(appName);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(content);
        alertDialog.showAndWait();
    }

    public static Boolean showInfoMessageWithBtn(String header, String content, String btnYes, String btnNo) {
        // change button type text
        ButtonType yes = new ButtonType(btnYes, ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType(btnNo, ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION, content, yes, no);
        alertDialog.setTitle(appName);
        alertDialog.setHeaderText(header);

        Optional<ButtonType> result = alertDialog.showAndWait();

        return result.orElse(no) == yes;
    }

    public static void navigateModal(URL location, String windowName) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(location);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(appName + " " + windowName);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void navigateTo(URL location, String windowName) {

        try {
            Parent root = FXMLLoader.load(location);
            stage.setScene(new Scene(root));
            stage.setTitle(appName + " " + windowName);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUserProfile(Label username, ImageView buttonLogOut) {
        username.setText(connectedUser.getFirstName() + " " + connectedUser.getLastName());
        // set disconnect tooltip
        Tooltip.install(buttonLogOut, new Tooltip("Déconnexion"));
    }

    public static void logOut(URL location, JFXButton anyBtn) {
        // get all windows and close
        List<Window> windows = Window.getWindows();
        for (int i = windows.size() - 1; i >= 0; i--) {
            if (windows.get(i).getTitle().contains("Handball IHM")) {
                windows.get(i).close();
            }
        }

        // load login scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(location);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(appName + " - Connexion");
        stage.show();

        // navigate to new screen
        anyBtn.getScene().getWindow().hide();
    }


    public static void closeWindow(String windowName) {
        List<Window> windows = Window.getWindows();
        for (Window win : windows) {
            if (win.getTitle().contains(windowName)) {
                win.close();
            }
        }
    }
}
