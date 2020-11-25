package sample.controller.main;

import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.controller.datafx.ExtendedAnimatedFlowContainer;
import sample.controller.sidemenu.SideMenuController;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;


@ViewController(value = "/view/Main.fxml", title = "Application de Handball")
public class MainController {

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    @ViewNode
    private StackPane root;
    @FXML
    @ViewNode
    private StackPane hamburgerSideMenuContainer;
    @FXML
    @ViewNode
    private JFXHamburger hamburgerSideMenu;
    @FXML
    @ViewNode
    private StackPane optionsBurger;
    @FXML
    @ViewNode
    private JFXRippler optionsRippler;
    @FXML
    @ViewNode
    private JFXDrawer drawer;

    private FlowHandler sideMenuFlowHandler;
    private FlowHandler centerFlowHandler;
    private Flow centerFlow;

    private JFXPopup toolbarPopup;
    private LoginController loginController;

    private ObservableList<JFXButton> observableListbuttons;


    @PostConstruct
    public void init() throws FlowException {
        observableListbuttons = FXCollections.observableArrayList();

        //init the title hamburger icon
        final JFXTooltip burgerTooltip = new JFXTooltip("Ouvrir le menu latéral");

        drawer.setOnDrawerOpening(e -> {
            final Transition animation = hamburgerSideMenu.getAnimation();
            burgerTooltip.setText("Fermer le menu latéral");
            animation.setRate(1);
            animation.play();
        });
        drawer.setOnDrawerClosing(e -> {
            final Transition animation = hamburgerSideMenu.getAnimation();
            burgerTooltip.setText("Ouvrir le menu latéral");
            animation.setRate(-1);
            animation.play();
        });
        hamburgerSideMenuContainer.setOnMouseClicked(e -> {
            if (drawer.isClosed() || drawer.isClosing()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/components/popup/MainPopup.fxml"));
        loader.setController(new InputController());
        try {
            toolbarPopup = new JFXPopup(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        optionsBurger.setOnMouseClicked(e ->
                toolbarPopup.show(optionsBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.RIGHT,
                        -12,
                        15));
        JFXTooltip.setVisibleDuration(Duration.millis(3000));
        JFXTooltip.install(hamburgerSideMenuContainer, burgerTooltip, Pos.BOTTOM_CENTER);

        // create the inner flow and content
        viewFlowContext = new ViewFlowContext();
        // set the default controller

        centerFlow = new Flow(LoginController.class);
        centerFlowHandler = centerFlow.createHandler(viewFlowContext);

        initContent();
        initSideBar();
    }

    public static final class InputController {
        @FXML
        private JFXListView<?> toolbarPopupList;

        // close application
        @FXML
        private void submit() {
            int selectionList = toolbarPopupList.getSelectionModel().getSelectedIndex();
            if (selectionList == 2) {
                Platform.exit();
            }
        }
    }

    class ChangeInnerFlow implements ChangeListener<JFXButton> {
        @Override
        public void changed(ObservableValue<? extends JFXButton> observable, JFXButton oldValue, JFXButton newValue) {
            if (newValue != null) {
                if (newValue.getClass().getName().equals(LoginController.class)) {
                    hamburgerSideMenu.setVisible(false);
                    hamburgerSideMenu.setDisable(true);
                    hamburgerSideMenuContainer.setVisible(false);
                } else {
                    hamburgerSideMenu.setVisible(true);
                    hamburgerSideMenu.setDisable(true);
                }
            }
        }
    }

    private void initContent() throws FlowException {
        viewFlowContext.register("ContentFlowHandler", centerFlowHandler);
        viewFlowContext.register("ContentFlow", centerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        try {
            drawer.setContent(centerFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        } catch (FlowException e) {
            e.printStackTrace();
        }
        viewFlowContext.register("ContentPane", drawer.getContent().get(0));
    }

    private void initSideBar() throws FlowException {
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        viewFlowContext.register("SideMenuFlow", sideMenuFlow);
        //viewFlowContext.register("CenterDrawer", drawer);
        final Duration containerAnimationDuration = Duration.millis(320);
        sideMenuFlowHandler = sideMenuFlow.createHandler(viewFlowContext);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
    }

    public JFXHamburger getHamburgerSideMenu() {
        return hamburgerSideMenu;
    }

}