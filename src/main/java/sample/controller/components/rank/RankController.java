package sample.controller.components.rank;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import sample.controller.components.bar.TitleSectionBarController;
import sample.controller.components.dashboard.DashboardController;
import sample.model.Player;
import sample.model.Team;
import sample.model.Team;
import sample.model.Team;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

@ViewController(value = "/view/components/rank/Rank.fxml",  title = "Application de Handball")
public class RankController extends TitleSectionBarController {

    public static final String CONTENT_PANE = "ContentPane";
    private static final String PREFIX = "( ";
    private static final String POSTFIX = " )";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private StackPane rootRank;
    @FXML
    private JFXButton buttonPopupInfoSection;
    @FXML
    private JFXDialog dialogRank;
    @FXML
    private JFXButton buttonAcceptDialogRank;
    @FXML
    private JFXButton buttonBack;
    @FXML
    private Label labelTitleSection;

    @FXML
    private JFXTreeTableView<Team> treeTableViewLidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column1LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, String> column2LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column3LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column4LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column5LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column6LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column7LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column8LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column9LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column10LidlStarligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column11LidlStarligue;
    @FXML
    private Label treeTableViewCountTeamLidlStarligue;
    @FXML
    private JFXTextField searchFieldLidlStarligue;
    @FXML
    private JFXComboBox comboBoxLidlStarligue;

    @FXML
    private JFXTreeTableView<Team> treeTableViewProligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column1Proligue;
    @FXML
    private JFXTreeTableColumn<Team, String> column2Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column3Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column4Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column5Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column6Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column7Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column8Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column9Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column10Proligue;
    @FXML
    private JFXTreeTableColumn<Team, Integer> column11Proligue;
    @FXML
    private Label treeTableViewCountTeamProligue;
    @FXML
    private JFXTextField searchFieldProligue;
    @FXML
    private JFXComboBox comboBoxProligue;


    private final String[] teamNamesLidleStarligue = {"Toulouse", "Paris", "Montpellier", "Nantes", "Nantes",
            "Nîmes", "Aix", "Saint-Raphaël", "Créteil",};

    private final String[] teamNamesProligue = {"Nancy", "Limoges", "Billère", "Nice", "Valence",};
    private final String[] teamNamesCoach = {"Jacques", "Tim", "Jean", "Thomas",};

    private final Random random = new SecureRandom();

    private FlowHandler centerFlowHandler;

    @PostConstruct
    public void init() throws Exception {
        DashboardController.initButtonInfos(rootRank, dialogRank, buttonPopupInfoSection, viewFlowContext, CONTENT_PANE, buttonAcceptDialogRank, buttonBack);
        labelTitleSection.setText("Classements");
        initButtonTitle();
        initComboBox();
        setupEditableTableViewTeams();
    }

    private void initComboBox() {
        comboBoxLidlStarligue.getItems().addAll(
                "2019",
                "2018",
                "2017",
                "2016",
                "2015"
        );
        comboBoxLidlStarligue.setPromptText("2019");
        comboBoxProligue.getItems().addAll(
                "2019",
                "2018",
                "2017",
                "2016",
                "2015"
        );
        comboBoxProligue.setPromptText("2019");
    }

    private Team createNewRandomTeamLidlStarligue() {
        return new Team(
                1,
                teamNamesLidleStarligue[random.nextInt(teamNamesLidleStarligue.length)],
                random.nextInt(50),
                random.nextInt(26),
                random.nextInt(26),
                random.nextInt(26),
                random.nextInt(26),
                random.nextInt(800),
                random.nextInt(800),
                random.nextInt(200),
                teamNamesCoach[random.nextInt(teamNamesCoach.length)]);
    }

    private Team createNewRandomTeamProligue() {
        return new Team(
                1,
                teamNamesProligue[random.nextInt(teamNamesProligue.length)],
                random.nextInt(50),
                random.nextInt(26),
                random.nextInt(26),
                random.nextInt(26),
                random.nextInt(26),
                random.nextInt(800),
                random.nextInt(800),
                random.nextInt(200),
                teamNamesCoach[random.nextInt(teamNamesCoach.length)]);
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<Team, T> column, Function<Team, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

    private void setupEditableTableViewTeams() {
        setupCellValueFactory(column1LidlStarligue, t -> t.rank.asObject());
        setupCellValueFactory(column2LidlStarligue, Team::teamNameProperty);
        setupCellValueFactory(column4LidlStarligue, t -> t.played.asObject());
        setupCellValueFactory(column5LidlStarligue, t -> t.won.asObject());
        setupCellValueFactory(column6LidlStarligue, t -> t.equality.asObject());
        setupCellValueFactory(column7LidlStarligue, t -> t.lost.asObject());
        setupCellValueFactory(column8LidlStarligue, t -> t.goalSet.asObject());
        setupCellValueFactory(column9LidlStarligue, t -> t.goalConceded.asObject());
        setupCellValueFactory(column10LidlStarligue, t -> t.goalAverage.asObject());
        setupCellValueFactory(column11LidlStarligue, t -> t.point.asObject());

        setupCellValueFactory(column1Proligue, t -> t.rank.asObject());
        setupCellValueFactory(column2Proligue, Team::teamNameProperty);
        setupCellValueFactory(column4Proligue, t -> t.played.asObject());
        setupCellValueFactory(column5Proligue, t -> t.won.asObject());
        setupCellValueFactory(column6Proligue, t -> t.equality.asObject());
        setupCellValueFactory(column7Proligue, t -> t.lost.asObject());
        setupCellValueFactory(column8Proligue, t -> t.goalSet.asObject());
        setupCellValueFactory(column9Proligue, t -> t.goalConceded.asObject());
        setupCellValueFactory(column10Proligue, t -> t.goalAverage.asObject());
        setupCellValueFactory(column11Proligue, t -> t.point.asObject());

        column1LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column1LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().rank.set(t.getNewValue());
        });
        column2LidlStarligue.setCellFactory((TreeTableColumn<Team, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        column2LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setTeamName(t.getNewValue());
        });
        column4LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column4LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().played.set(t.getNewValue());
        });
        column5LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column5LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().won.set(t.getNewValue());
        });
        column6LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column6LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().equality.set(t.getNewValue());
        });
        column7LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column7LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().lost.set(t.getNewValue());
        });
        column8LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column8LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goalSet.set(t.getNewValue());
        });
        column9LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column9LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goalConceded.set(t.getNewValue());
        });
        column10LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column10LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goalAverage.set(t.getNewValue());
        });
        column11LidlStarligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column11LidlStarligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPoint(t.getNewValue());
        });

        column1Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column1Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().rank.set(t.getNewValue());
        });
        column2Proligue.setCellFactory((TreeTableColumn<Team, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        column2Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setTeamName(t.getNewValue());
        });
        column4Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column4Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().played.set(t.getNewValue());
        });
        column5Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column5Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().won.set(t.getNewValue());
        });
        column6Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column6Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().equality.set(t.getNewValue());
        });
        column7Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column7Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().lost.set(t.getNewValue());
        });
        column8Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column8Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goalSet.set(t.getNewValue());
        });
        column9Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column9Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goalConceded.set(t.getNewValue());
        });
        column10Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column10Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().goalAverage.set(t.getNewValue());
        });
        column11Proligue.setCellFactory((TreeTableColumn<Team, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column11Proligue.setOnEditCommit((TreeTableColumn.CellEditEvent<Team, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPoint(t.getNewValue());
        });

        final ObservableList<Team> observableListTeamsLidlStarligue = generateDummyDataTeamLidlStarligue(14);
        final ObservableList<Team> observableListTeamsProligue = generateDummyDataTeamProligue(14);

        setupTreeTableViewTeams(observableListTeamsLidlStarligue, treeTableViewLidlStarligue, treeTableViewCountTeamLidlStarligue);

        setupTreeTableViewTeams(observableListTeamsProligue, treeTableViewProligue, treeTableViewCountTeamProligue);
    }

    private void setupTreeTableViewTeams(ObservableList<Team> observableListTeamsProligue, JFXTreeTableView<Team> treeTableViewProligue, Label treeTableViewCountTeamProligue) {
        treeTableViewProligue.setRoot(new RecursiveTreeItem<>(observableListTeamsProligue, RecursiveTreeObject::getChildren));
        treeTableViewProligue.setShowRoot(false);
        treeTableViewProligue.setEditable(true);
        treeTableViewCountTeamProligue.textProperty()
                .bind(Bindings.createStringBinding(() -> PREFIX + treeTableViewProligue.getCurrentItemsCount() + POSTFIX,
                        treeTableViewProligue.currentItemsCountProperty()));

        searchFieldLidlStarligue.textProperty()
                .addListener(setupSearchField(treeTableViewLidlStarligue));
    }

    private ObservableList<Team> generateDummyDataTeamLidlStarligue(final int numberOfEntries) {
        final ObservableList<Team> dummyData = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfEntries; i++) {
            dummyData.add(createNewRandomTeamLidlStarligue());
        }
        return dummyData;
    }

    private ObservableList<Team> generateDummyDataTeamProligue(final int numberOfEntries) {
        final ObservableList<Team> dummyData = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfEntries; i++) {
            dummyData.add(createNewRandomTeamProligue());
        }
        return dummyData;
    }

    private ChangeListener<String> setupSearchField(final JFXTreeTableView<Team> tableView) {
        return getStringChangeListener(tableView);
    }

    static ChangeListener<String> getStringChangeListener(JFXTreeTableView<Team> tableView) {
        return (o, oldVal, newVal) ->
                tableView.setPredicate(teamProp -> {
                    final Team team = teamProp.getValue();
                    return Integer.toString(team.rank.get()).contains(newVal)
                            || team.getTeamName().contains(newVal)
                            || Integer.toString(team.played.get()).contains(newVal)
                            || Integer.toString(team.won.get()).contains(newVal)
                            || Integer.toString(team.equality.get()).contains(newVal)
                            || Integer.toString(team.lost.get()).contains(newVal)
                            || Integer.toString(team.goalSet.get()).contains(newVal)
                            || Integer.toString(team.goalConceded.get()).contains(newVal)
                            || Integer.toString(team.goalAverage.get()).contains(newVal)
                            || Integer.toString(team.point.get()).contains(newVal);
                });
    }

}
