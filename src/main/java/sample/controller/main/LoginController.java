package sample.controller.main;

import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.LinkAction;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import sample.controller.components.dashboard.DashboardController;

import javax.annotation.PostConstruct;

@ViewController(value = "/view/Login.fxml", title = "Application de Handball")
public class LoginController extends MainController {

    public static final String CONTENT_PANE = "ContentPane";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    @ViewNode
    private StackPane root;
    @FXML
    private StackPane hamburgerSideMenuContainer;
    @FXML
    private JFXHamburger hamburgerSideMenu;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private JFXDialog dialogLogin;
    @FXML
    private JFXButton buttonAcceptDialogLogin;
    @FXML
    private JFXButton buttonPopupInfo;
    @FXML
    private Label labelAppVersion;
    @FXML
    private Label labelErrorMessage;
    @FXML
    private JFXTextField textFieldUsername;
    @FXML
    private JFXPasswordField textFieldPassword;
    @FXML
    @ViewNode
    @LinkAction(DashboardController.class)
    public JFXButton buttonLogIn;

    private String username;
    private String password;
    private Boolean logged = true;

    @PostConstruct
    public void init() {
        FlowHandler contentFlowHandler = (FlowHandler) viewFlowContext.getRegisteredObject("ContentFlowHandler");

        root.getChildren().remove(dialogLogin);
        buttonPopupInfo.setOnAction(action -> {
            dialogLogin.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogLogin.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonAcceptDialogLogin.setOnAction(action -> dialogLogin.close());

        Flow contentFlow = (Flow) viewFlowContext.getRegisteredObject("ContentFlow");
    }


    public JFXButton getButtonLogIn() {
        return buttonLogIn;
    }

    public String getStringButtonLogIn() {
        return buttonLogIn.getText();
    }

}
