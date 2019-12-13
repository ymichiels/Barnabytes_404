/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller.components.match;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;

import static sample.controller.components.dashboard.DashboardController.initButtonInfos;

@ViewController(value = "/view/components/match/LeagueSelection.fxml", title = "Sélection de la league")
public class LeagueSelectionController {

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    @ViewNode
    private JFXRadioButton radioButtonStarligue;
    @FXML
    @ViewNode
    private JFXRadioButton radioButtonProligue;

    private ToggleGroup group;

    @PostConstruct
    public void init() throws FlowException {
        //initButtonInfos(rootNewMatch, dialogNewMatch, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogNewMatch);
        group = new ToggleGroup();
        group.getToggles().addAll(radioButtonStarligue, radioButtonProligue);
        //radioButtonStarligue.setSelected(true);
    }

}
