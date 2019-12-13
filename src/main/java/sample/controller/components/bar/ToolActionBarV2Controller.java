package sample.controller.components.bar;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.action.BackAction;
import javafx.fxml.FXML;

@ViewController(value = "/view/components/bar/ToolActionBarV2.fxml", title = "Application de Handball")
public class ToolActionBarV2Controller {

    @FXML
    @ViewNode
    @ActionTrigger("cancel")
    private JFXButton buttonCancel;
    @FXML
    @ViewNode
    @ActionTrigger("validate")
    private JFXButton buttonValidate;

    public JFXButton getButtonCancel() {
        return buttonCancel;
    }

    public JFXButton getButtonValidate() {
        return buttonValidate;
    }
    
}
