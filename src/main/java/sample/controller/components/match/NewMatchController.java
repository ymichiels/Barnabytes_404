package sample.controller.components.match;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.svg.SVGGlyph;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import javafx.util.Duration;
import sample.controller.components.bar.TitleSectionBarMinController;

import javax.annotation.PostConstruct;

import static sample.controller.components.dashboard.DashboardController.initButtonInfos;

@ViewController(value = "/view/components/match/NewMatch.fxml", title = "Nouveau match")
public class NewMatchController {

    public static final String CONTENT_PANE = "ContentNewMatch";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private StackPane rootNewMatch;
    @FXML
    @ActionTrigger("back")
    private JFXButton buttonBack;
    @FXML
    @ActionTrigger("finish")
    private JFXButton buttonFinish;
    @FXML
    @ActionTrigger("next")
    private JFXButton buttonNext;
    @FXML
    private JFXButton buttonPopupInfoSection2;
    @FXML
    @ViewNode
    private JFXDialog dialogNewMatchLeague;
    @FXML
    @ViewNode
    private JFXButton buttonAcceptDialogNewMatchLeague;
    @FXML
    @ViewNode
    private JFXDialog dialogNewMatchDate;
    @FXML
    @ViewNode
    private JFXButton buttonAcceptDialogNewMatchDate;
    @FXML
    @ViewNode
    private JFXDialog dialogNewMatchTeam;
    @FXML
    @ViewNode
    private JFXButton buttonAcceptDialogNewMatchTeam;
    @FXML
    @ViewNode
    private JFXDialog dialogNewMatchPlayer;
    @FXML
    @ViewNode
    private JFXButton buttonAcceptDialogNewMatchPlayer;
    @FXML
    private StackPane centerPane;
    @FXML
    @ViewNode
    private Label labelTitleSection;

    private FlowHandler flowHandler;

    @PostConstruct
    public void init() throws FlowException {
        rootNewMatch.getChildren().removeAll(dialogNewMatchLeague, dialogNewMatchDate, dialogNewMatchTeam, dialogNewMatchPlayer);
        initButtonInfos(rootNewMatch, dialogNewMatchLeague, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchLeague);
        viewFlowContext.register(CONTENT_PANE, rootNewMatch);
        labelTitleSection.setText("Sélection de la league");
        SVGGlyph help = new SVGGlyph(0,
                "FULLSCREEN",
                "M15.047 11.25q0.938-0.938 0.938-2.25 0-1.641-1.172-2.813t-2.813-1.172-2.813 1.172-1.172 2.813h1.969q0-0.797 0.609-1.406t1.406-0.609 1.406 0.609 0.609 1.406-0.609 1.406l-1.219 1.266q-1.172 1.266-1.172 2.813v0.516h1.969q0-1.547 1.172-2.813zM12.984 18.984v-1.969h-1.969v1.969h1.969zM12 2.016q4.125 0 7.055 2.93t2.93 7.055-2.93 7.055-7.055 2.93-7.055-2.93-2.93-7.055 2.93-7.055 7.055-2.93z",
                Color.WHITE);
        help.setSize(24, 24);
        buttonPopupInfoSection2.setGraphic(help);
        buttonPopupInfoSection2.setRipplerFill(Color.WHITE);

        Flow flow = new Flow(LeagueSelectionController.class).
                withLink(LeagueSelectionController.class, "next", DateSelectionController.class).
                withLink(DateSelectionController.class, "next", TeamSelectionController.class).
                withLink(TeamSelectionController.class, "next", PlayerSelectionController.class);
        flowHandler = flow.createHandler();
        centerPane.getChildren().add(flowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.SWIPE_LEFT)));

        buttonBack.setDisable(true);
        buttonFinish.setDisable(true);
    }

    @ActionMethod("back")
    public void onBack() throws VetoException, FlowException {
        flowHandler.navigateBack();
        if(flowHandler.getCurrentViewControllerClass().equals(LeagueSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchLeague, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchLeague);
            buttonBack.setDisable(true);
            labelTitleSection.setText("Sélection de la league");
        }
        else if(flowHandler.getCurrentViewControllerClass().equals(DateSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchDate, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchDate);
            labelTitleSection.setText("Sélection de la date");
        }
        else if(flowHandler.getCurrentViewControllerClass().equals(TeamSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchTeam, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchTeam);
            labelTitleSection.setText("Sélection des équipes");
        }
        else if(flowHandler.getCurrentViewControllerClass().equals(PlayerSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchPlayer, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchPlayer);
            labelTitleSection.setText("Sélection des joueurs");
        }
        else {
            buttonBack.setDisable(false);
        }
        buttonFinish.setDisable(true);
        buttonNext.setDisable(false);
    }

    @ActionMethod("next")
    public void onNext() throws VetoException, FlowException {
        flowHandler.handle("next");
        //Match match = new Match();
        if(flowHandler.getCurrentViewControllerClass().equals(DateSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchDate, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchDate);
            labelTitleSection.setText("Sélection de la date");
        }
        else if(flowHandler.getCurrentViewControllerClass().equals(TeamSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchTeam, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchTeam);
            labelTitleSection.setText("Sélection des équipes");
        }
        else if(flowHandler.getCurrentViewControllerClass().equals(PlayerSelectionController.class)) {
            initButtonInfos(rootNewMatch, dialogNewMatchPlayer, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatchPlayer);
            buttonNext.setDisable(true);
            buttonFinish.setDisable(false);
            labelTitleSection.setText("Sélection des joueurs");
        } else {
            buttonNext.setDisable(false);
        }
        buttonBack.setDisable(false);
    }

    @ActionMethod("finish")
    public void onFinish() throws VetoException, FlowException {
        hideWindow(buttonFinish);
        buttonFinish.setDisable(true);
        buttonNext.setDisable(true);
        buttonBack.setDisable(false);
    }

    private void hideWindow(JFXButton button) {
    Window window = buttonFinish.getScene().getWindow();
    window.hide();
    }
}
