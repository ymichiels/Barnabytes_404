package sample.controller.components.dashboard;

import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import sample.controller.main.MainController;

import javax.annotation.PostConstruct;


@ViewController(value = "/view/components/dashboard/Dashboard.fxml", title = "Application de Handball")
public final class DashboardController extends MainController {

    public static final String CONTENT_PANE = "ContentPane";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private StackPane hamburgerSideMenuContainer;
    @FXML
    private JFXHamburger hamburgerSideMenu;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private JFXButton buttonInfo;
    @FXML
    private JFXDialog dialog;
    @FXML
    private StackPane root;
    @FXML
    private JFXDialog dialog2;
    @FXML
    private JFXButton buttonPopupValidate;
    @FXML
    private JFXDialog dialogLeave;
    @FXML
    private JFXButton buttonPopupValidateYes;
    @FXML
    private JFXButton buttonPopupValidateNo;


    @ViewNode
    //@LinkAction(ResumeMatchsController.class)
    //@ActionTrigger("navigateToMatch")
    private JFXButton buttonMatch;
    @ViewNode
    //@LinkAction(RankController.class)
    //@ActionTrigger("navigateToRank")
    private JFXButton buttonRank;
    @ViewNode
    //@LinkAction(StatisticController.class)
    //@ActionTrigger("navigateToStatistic")
    private JFXButton buttonStatistic;
    @ViewNode
    //@LinkAction(SettingController.class)
    //@ActionTrigger("navigateToSetting")
    private JFXButton buttonSetting;
    @ViewNode
    //@ActionTrigger("navigateToLogin")
    private JFXButton buttonLogout;
    @FXML
    private JFXButton buttonAccept;
    @FXML
    private JFXDrawer drawer;

    @ActionHandler
    private FlowActionHandler actionHandler;

    private FlowHandler sideMenuFlowHandler;
    private FlowHandler contentFlowHandler;
    private JFXListView<JFXButton> listViewButtons;
    private MainController main;

    @PostConstruct
    public void init() {
        root.getChildren().removeAll(dialog, dialog2);

        buttonMatch.setOnAction(action -> {
            dialog2.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog2.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonRank.setOnAction(action -> {
            dialog2.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog2.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonStatistic.setOnAction(action -> {
            dialog2.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog2.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonSetting.setOnAction(action -> {
            dialog2.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog2.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonPopupValidate.setOnAction(action -> dialog2.close());

        buttonInfo.setOnAction(action -> {
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonAccept.setOnAction(action -> dialog.close());

        buttonLogout.setOnAction(action -> {
            dialogLeave.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialogLeave.show((StackPane) viewFlowContext.getRegisteredObject(CONTENT_PANE));
        });
        buttonPopupValidateYes.setOnAction(action -> Platform.exit());
        buttonPopupValidateNo.setOnAction(action -> dialogLeave.close());
    }

    public static void initButtonInfos(StackPane root, JFXDialog dialog, JFXButton buttonPopupInfoSection, ViewFlowContext viewFlowContext, String contentPane, JFXButton buttonAcceptDialog, JFXButton buttonBack) {
        root.getChildren().remove(dialog);
        buttonPopupInfoSection.setOnAction(action -> {
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog.show((StackPane) viewFlowContext.getRegisteredObject(contentPane));
        });
        buttonAcceptDialog.setOnAction(action -> dialog.close());
    }

    public static void initButtonInfos(StackPane root, JFXDialog dialog, JFXButton buttonPopupInfoSection, ViewFlowContext viewFlowContext, String contentPane, JFXButton buttonAcceptDialog) {
        root.getChildren().remove(dialog);
        buttonPopupInfoSection.setOnAction(action -> {
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            dialog.show((StackPane) viewFlowContext.getRegisteredObject(contentPane));
        });
        buttonAcceptDialog.setOnAction(action -> dialog.close());
    }

//    @ActionMethod
//    private void navToMatch() {
//        actionHandler.Navigate.navigateTo(ResumeMatchsController.class);
//    }
//
//    @FXML
//    private void navToSetting() {
//        Navigate.navigateTo(SettingController.class);
//    }
//
//    @FXML
//    private void navToRank() {
//        Navigate.navigateTo(RankController.class);
//    }
//
//    @ActionMethod("navigateToStatistic")
//    private void navigateToStatistic() throws VetoException, FlowException {
//        actionHandler.navigate(ResumeMatchsController.class);
//    }
//
//    private void setWelcomeText() {
//        if (Global.getConnectedUser().getGender().equals("Male")){
//            labelWelcome.setText("Bienvenu, " + getConnectedUser().getFirstName() + " !");
//        }else {
//            labelWelcome.setText("Bienvenue, " + getConnectedUser().getFirstName() + " !");
//        }
//    }

}