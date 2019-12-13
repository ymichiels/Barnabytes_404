package sample.controller.sidemenu;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import sample.controller.components.dashboard.DashboardController;
import sample.controller.components.match.ResumeMatchsController;
import sample.controller.components.rank.RankController;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;
import java.util.Objects;
import javafx.scene.layout.StackPane;
import sample.controller.components.setting.SettingController;
import sample.controller.components.statistic.StatisticController;
import sample.controller.main.LoginController;

@ViewController(value = "/view/SideMenu.fxml", title = "Application de Handball")
public class SideMenuController {

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    @ActionTrigger("navigateToHome")
    private Label labelHome;
    @FXML
    @ActionTrigger("navigateToMatch")
    private Label labelMatch;
    @FXML
    @ActionTrigger("navigateToRank")
    private Label labelRank;
    @FXML
    @ViewNode
    @ActionTrigger("navigateToStatistic")
    private Label labelStatistic;
    @FXML
    @ActionTrigger("navigateToSetting")
    private Label labelSetting;
    @FXML
    @ViewNode
    private JFXListView<Label> sideList;
    @FXML
    private StackPane root;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        //Objects.requireNonNull(viewFlowContext, "viewFlowContext");
        FlowHandler contentFlowHandler = (FlowHandler) viewFlowContext.getRegisteredObject("ContentFlowHandler");
        sideList.propagateMouseEventsToParent();
        sideList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            new Thread(()->{
                Platform.runLater(()->{
                    if (newVal != null) {
                        try {
                            contentFlowHandler.handle(newVal.getId());
                        } catch (VetoException exc) {
                            exc.printStackTrace();
                        } catch (FlowException exc) {
                            exc.printStackTrace();
                        }
                    }
                });
            }).start();
        });
        Flow contentFlow = (Flow) viewFlowContext.getRegisteredObject("ContentFlow");
        bindNodeToController(labelHome, DashboardController.class, contentFlow, contentFlowHandler);
        bindNodeToController(labelMatch, ResumeMatchsController.class, contentFlow, contentFlowHandler);
        bindNodeToController(labelRank, RankController.class, contentFlow, contentFlowHandler);
        bindNodeToController(labelStatistic, StatisticController.class, contentFlow, contentFlowHandler);
        bindNodeToController(labelSetting, SettingController.class, contentFlow, contentFlowHandler);
    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }

}
