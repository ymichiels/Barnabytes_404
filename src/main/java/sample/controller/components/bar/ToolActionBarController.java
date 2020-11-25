package sample.controller.components.bar;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.action.BackAction;
import javafx.fxml.FXML;

public class ToolActionBarController {

    @FXML
    @ViewNode
    @BackAction
    @ActionTrigger("back")
    private JFXButton buttonBack;

    @FXML
    @ActionTrigger("next")
    private JFXButton buttonNext;

    @FXML
    @ActionTrigger("finish")
    private JFXButton buttonFinish;

    public JFXButton getButtonBack() {
        return buttonBack;
    }

    public JFXButton getButtonNext() {
        return buttonNext;
    }

    public JFXButton getButtonFinish() {
        return buttonFinish;
    }

}
