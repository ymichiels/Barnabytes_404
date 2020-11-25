/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller.components.match;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;

import javax.annotation.PostConstruct;

@ViewController(value = "/view/components/match/DateSelection.fxml", title = "Sélection de la date")
public class DateSelectionController {

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;

    @PostConstruct
    public void init() {
    }
}

