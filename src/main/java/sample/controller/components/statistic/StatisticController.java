package sample.controller.components.statistic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import sample.controller.components.bar.TitleSectionBarController;
import sample.controller.components.dashboard.DashboardController;

import javax.annotation.PostConstruct;

@ViewController(value = "/view/components/statistic/Statistic.fxml", title = "Application de Handball")
public class StatisticController extends TitleSectionBarController {

    public static final String CONTENT_PANE = "ContentPane";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private StackPane rootStatistic;
    @FXML
    private JFXButton buttonPopupInfoSection;
    @FXML
    private JFXButton buttonBack;
    @FXML
    private JFXDialog dialogStatistic;
    @FXML
    private JFXButton buttonAcceptDialogStatistic;
    @FXML
    private Label labelTitleSection;

    private FlowHandler centerFlowHandler;

    @PostConstruct
    public void init() throws Exception {
        DashboardController.initButtonInfos(rootStatistic, dialogStatistic, buttonPopupInfoSection, viewFlowContext, CONTENT_PANE, buttonAcceptDialogStatistic, buttonBack);
        labelTitleSection.setText("Statistiques");
        initButtonTitle();
    }
}


