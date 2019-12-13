/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller.components.match;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.controller.components.bar.TitleSectionBarController;
import sample.controller.components.dashboard.DashboardController;
import sample.model.Match;
import sample.util.Create;
import sample.util.Navigate;

import javax.annotation.PostConstruct;

@ViewController(value = "/view/components/match/ResumeMatchs.fxml",  title = "Application de Handball")
public class ResumeMatchsController extends TitleSectionBarController {

    public static final String CONTENT_PANE = "ContentPane";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @ViewNode
    private JFXButton buttonBack;
    @FXML
    //@ViewNode
    private StackPane rootResumeMatch;
    @ViewNode
    private JFXScrollPane scrollPane;
    @ViewNode
    private JFXButton buttonAddNewMatch;
    @ViewNode
    private JFXButton buttonRefresh;
    @ViewNode
    @ActionTrigger("graph")
    private JFXButton buttonGraph;
    @ViewNode
    private Label labelTitleSection;
    @FXML
    private JFXButton buttonPopupInfoSection;
    @FXML
    private JFXDialog dialogResumeMatch;
    @FXML
    private JFXButton buttonAcceptDialogResumeMatch;

    @ActionHandler
    private FlowActionHandler actionHandler;

    private FlowHandler flowHandler;
    private Flow flow;
    private JFXMasonryPane masonryPane;
    private double scrollValue = 0.0;
    private Match match;

    @PostConstruct
    public void init() throws Exception {
        DashboardController.initButtonInfos(rootResumeMatch, dialogResumeMatch, buttonPopupInfoSection, viewFlowContext, CONTENT_PANE, buttonAcceptDialogResumeMatch, buttonBack);
        labelTitleSection.setText("Matches");
        initButtonTitle();
        masonryPane = new JFXMasonryPane();
        masonryPane.getChildren().addListener((ListChangeListener<Node>) c -> {
            masonryPane.layout();
            scrollPane.layout();
        });
        scrollPane.setContent(masonryPane);

        viewFlowContext.register("newMatchMasonryPane", masonryPane);
        viewFlowContext.register("newMatchScrollPane", scrollPane);

        loadNewMatch();
    }

    private void loadNewMatch() {
//        if (getEatenService.isRunning()) return;
//        getEatenService.reset();
//
//        getEatenService.setUsername(Chosen.getEater().getUsername());
//        getEatenService.setNumber(100);
//        getEatenService.setOnSucceeded(t -> {
//            Result<EatenRecord> result = (Result<EatenRecord>)t.getSource().getValue();
//            if (result == null) return;
//            for (EatenRecord eatenRecord : result) {
//                Eaten eaten = Eaten.createEaten(eatenRecord);
//                Node cardNode = Creator.createCard(eaten);
//                masonryPane.getChildren().add(cardNode);
//
//                cardNode.setOnMouseClicked(event -> {
//                    //if not same username as card not allowed to edit
//                    if (!eaten.getUsername().equals(Chosen.getEater().getUsername())) return;
//                    if(!(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)) return;
//
//                    try {
//                        Flow editEatenFlow = new Flow(EditEatenController.class);
//                        FlowHandler editEatenFlowHandler = editEatenFlow.createHandler(viewFlowContext);
//                        Stage primaryStage = (Stage) viewFlowContext.getRegisteredObject("stage");
//                        viewFlowContext.register("editEaten",eaten);
//
//                        //wrap
//                        BorderPane borderPane = new BorderPane();
//                        borderPane.setCenter(editEatenFlowHandler.start()); //TODO check if appropriate
//                        HBox hBox = new HBox();
//                        hBox.getChildren().add(new Label("FOOD ID: " + eaten.getFoodId()));
//                        hBox.setAlignment(Pos.CENTER);
//                        borderPane.setTop(hBox);
//
//                        Stage childStage = Creator.createChildStage(primaryStage, "Edit eaten", borderPane , 600,400, false);
//                        childStage.show();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//                Platform.runLater(() -> {
//                    scrollPane.requestLayout();
//                });
//                scrollPane.setVvalue(scrollValue);
//            }
//        });
//        getEatenService.start();
    }

    @FXML
    private void addNewMatch () {
        try {
            Flow addNewMatchFlow = new Flow(NewMatchController.class);
            FlowHandler addNewMatchFlowHandler = addNewMatchFlow.createHandler(viewFlowContext);
            Stage primaryStage = (Stage) viewFlowContext.getRegisteredObject("NewMatch");
            Stage childStage = Create.createChildStage(primaryStage, "Nouveau Match", addNewMatchFlowHandler.start(), 1200, 800, true);
            childStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startNewMatch () {
        try {
            Flow addNewMatchFlow = new Flow(MatchController.class);
            FlowHandler addNewMatchFlowHandler = addNewMatchFlow.createHandler(viewFlowContext);
            Stage primaryStage = (Stage) viewFlowContext.getRegisteredObject("StartNewMatch");
            Stage childStage = Create.createChildStage(primaryStage, "Nouveau Match", addNewMatchFlowHandler.start(), 1200, 800, true);
            childStage.setMaximized(true);
            childStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refresh () {
        masonryPane.getChildren().clear();
    }

    @FXML
    private void graph() throws FlowException, VetoException {
        Navigate.navigateTo(GraphController.class);
    }

}
