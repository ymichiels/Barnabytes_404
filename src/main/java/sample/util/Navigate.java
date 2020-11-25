package sample.util;

import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import sample.model.User;

public final class Navigate {

    private static User connectedUser = new User();
    public static String appName = "Handball IHM";
    public static String navFrom = "";
    public static Double appVersion = 1.1;
    public String titlePage;
    private static ViewFlowContext context;

    //Getters and Setters
    public static void sleep() {
        setUser(null);
    }

    public User getUser() {
        return connectedUser;
    }

    public static void setUser(User user) {
        connectedUser = user;
    }

    public static User getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(User connectedUser) {
        Navigate.connectedUser = connectedUser;
    }

    public static ViewFlowContext getViewFlowContext() {
        return context;
    }

    public static void setViewFlowContext(ViewFlowContext contextPrimary) {
        context = contextPrimary;
    }

    public static void showErrorMessage(String header, String content) {
        Alert alertDialog = new Alert(Alert.AlertType.ERROR);
        alertDialog.setTitle(appName);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(content);
        alertDialog.showAndWait();
    }

    public static void showInfoMessage(String header, String content) {
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle(appName);
        alertDialog.setHeaderText(header);
        alertDialog.setContentText(content);
        alertDialog.showAndWait();
    }

    public static void navigateTo(Class controller) {
        String controllerName = controller.getSimpleName();
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
        try {
            contentFlowHandler.handle(controllerName);
        } catch (VetoException e) {
            e.printStackTrace();
        } catch (FlowException e) {
            e.printStackTrace();
        }
        System.out.println("Chargement de : " + controllerName + " est terminé.");
    }

    public static void navigateTo(Class controller, String contentName) {
        String controllerName = controller.getSimpleName();
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject(contentName);
        try {
            contentFlowHandler.handle(controllerName);
        } catch (VetoException e) {
            e.printStackTrace();
        } catch (FlowException e) {
            e.printStackTrace();
        }
        System.out.println("Chargement de : " + controllerName + " est terminé.");
    }

    public static void setUserProfile(Label username, ImageView buttonLogOut) {
        username.setText(connectedUser.getFirstName() + " " + connectedUser.getLastName());
        // set disconnect tooltip
        Tooltip.install(buttonLogOut, new Tooltip("Déconnexion"));
    }
}
