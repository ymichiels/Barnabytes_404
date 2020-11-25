package sample.controller.components.match;

import com.jfoenix.controls.JFXComboBox;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.scene.layout.BorderPane;

import javax.annotation.PostConstruct;

@ViewController("GraphController.fxml")
public class GraphController {

    @ViewNode
    private BorderPane mainBorderPane;
    @ViewNode
    private JFXComboBox dateComboBox;
    @ViewNode
    private JFXComboBox graphComboBox;

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;

    private Flow matchCenterFlow;
    private FlowHandler matchCenterFlowHandler;

    @PostConstruct
    public void init() {
        matchCenterFlow = new Flow(GraphController.class);
        matchCenterFlowHandler = matchCenterFlow.createHandler(viewFlowContext);
        setContext();
    }

    private void setContext() {
        viewFlowContext.register("dateTypeAxis");
        viewFlowContext.register("dateTypeSince");
    }

}
