package sample.controller.components.setting;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.controller.components.bar.TitleSectionBarController;
import sample.controller.components.dashboard.DashboardController;
import sample.util.Create;

import javax.annotation.PostConstruct;

@ViewController(value = "/view/components/setting/Setting.fxml",  title = "Application de Handball")
public class SettingController extends TitleSectionBarController {

    public static final String CONTENT_PANE = "ContentPane";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private StackPane rootSetting;
    @FXML
    private JFXButton buttonPopupInfoSection;
    @FXML
    private JFXButton buttonBack;
    @FXML
    private JFXDialog dialogSetting;
    @FXML
    private JFXButton buttonAcceptDialogSetting;
    @FXML
    private JFXButton buttonAddNewPlayer;
    @FXML
    private Label labelTitleSection;

    private FlowHandler centerFlowHandler;

    @PostConstruct
    public void init() throws Exception {
        DashboardController.initButtonInfos(rootSetting, dialogSetting, buttonPopupInfoSection, viewFlowContext, CONTENT_PANE, buttonAcceptDialogSetting, buttonBack);
        labelTitleSection.setText("Réglages");
        initButtonTitle();
    }

    @FXML
    private void addNewPlayer () {
        try {
            Flow addNewMatchFlow = new Flow(NewPlayerController.class);
            FlowHandler addNewMatchFlowHandler = addNewMatchFlow.createHandler(viewFlowContext);
            Stage primaryStage = (Stage) viewFlowContext.getRegisteredObject("NewStagePlayer");
            Stage childStage = Create.createChildStage(primaryStage, "Nouveau Joueur", addNewMatchFlowHandler.start(), 1200, 800, true);
            childStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
