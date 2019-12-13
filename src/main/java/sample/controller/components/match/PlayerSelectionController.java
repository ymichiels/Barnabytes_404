package sample.controller.components.match;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.datafx.controller.ViewController;
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
import sample.model.Player;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Function;

import static sample.controller.components.match.MatchController.getStringChangeListener;

@ViewController("/view/components/match/PlayerSelection.fxml")
public class PlayerSelectionController {
    private static final String PREFIX = "( ";
    private static final String POSTFIX = " )";

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private JFXTreeTableView<Player> treeTableViewTeam1;
    @FXML
    private JFXTreeTableColumn<Player, String> column1Team1;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column2Team1;
    @FXML
    private JFXTreeTableColumn<Player, String> column3Team1;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column4Team1;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column5Team1;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column6Team1;
    @FXML
    private Label treeTableViewCount1;
    @FXML
    private JFXButton treeTableViewAdd1;
    @FXML
    private JFXButton treeTableViewRemove1;
    @FXML
    private JFXTextField searchField1;

    @FXML
    private JFXTreeTableView<Player> treeTableViewTeam2;
    @FXML
    private JFXTreeTableColumn<Player, String> column1Team2;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column2Team2;
    @FXML
    private JFXTreeTableColumn<Player, String> column3Team2;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column4Team2;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column5Team2;
    @FXML
    private JFXTreeTableColumn<Player, Integer> column6Team2;
    @FXML
    private Label treeTableViewCount2;
    @FXML
    private JFXButton treeTableViewAdd2;
    @FXML
    private JFXButton treeTableViewRemove2;
    @FXML
    private JFXTextField searchField2;

    private final String[] names = {"Jean", "Moulin", "Benoit", "Morley", "Scott", "Kruger", "Lain",
            "Kennedy", "Gawron", "Han", "Hall", "Aydogdu", "Grace",
            "Spiers", "Perera", "Smith", "Connoly",
            "Sokolowski", "Chaow", "James", "June",};
    private final String[] posts = {"GB", "AiD", "AiG", "ArD", "ArG", "DmC", "Piv",};
    private final Random random = new SecureRandom();

    @PostConstruct
    public void init() {
        final ObservableList<Player> observableListPlayersTeam1 = generateDummyData(14);
        final ObservableList<Player> observableListPlayersTeam2 = generateDummyData(14);

        setupEditableTableViewPlayers();
    }

    private Player createNewRandomPlayer() {
        return new Player(
                1,
                posts[random.nextInt(posts.length)],
                random.nextInt(14),
                names[random.nextInt(names.length)]);
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<Player, T> column, Function<Player, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Player, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

    private void setupEditableTableViewPlayers() {

        setupCellValueFactory(column1Team1, Player::postProperty);
        setupCellValueFactory(column2Team1, p -> p.number.asObject());
        setupCellValueFactory(column3Team1, Player::lastNameProperty);
        setupCellValueFactory(column4Team1, p -> p.numberYellowCard.asObject());
        setupCellValueFactory(column5Team1, p -> p.numberRedCard.asObject());
        setupCellValueFactory(column6Team1, p -> p.numberGoal.asObject());

        setupCellValueFactory(column1Team2, Player::postProperty);
        setupCellValueFactory(column2Team2, p -> p.number.asObject());
        setupCellValueFactory(column3Team2, Player::lastNameProperty);
        setupCellValueFactory(column4Team2, p -> p.numberYellowCard.asObject());
        setupCellValueFactory(column5Team2, p -> p.numberRedCard.asObject());
        setupCellValueFactory(column6Team2, p -> p.numberGoal.asObject());

        // Construit le tableau pour l'equipe 1
        column1Team1.setCellFactory((TreeTableColumn<Player, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        column1Team1.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPost(t.getNewValue());
        });
        column2Team1.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column2Team1.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().number.set(t.getNewValue());
        });
        column3Team1.setCellFactory((TreeTableColumn<Player, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        column3Team1.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setLastName(t.getNewValue());
        });
        column4Team1.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column4Team1.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().numberYellowCard.set(t.getNewValue());
        });
        column5Team1.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column5Team1.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().numberRedCard.set(t.getNewValue());
        });
        column6Team1.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column6Team1.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().numberGoal.set(t.getNewValue());
        });

        // Construit le tableau pour l'equipe 2
        column1Team2.setCellFactory((TreeTableColumn<Player, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        column1Team2.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setPost(t.getNewValue());
        });
        column2Team2.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column2Team2.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().number.set(t.getNewValue());
        });
        column3Team2.setCellFactory((TreeTableColumn<Player, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        column3Team2.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, String> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().setLastName(t.getNewValue());
        });
        column4Team2.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column4Team2.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().numberYellowCard.set(t.getNewValue());
        });
        column5Team2.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column5Team2.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().numberRedCard.set(t.getNewValue());
        });
        column6Team2.setCellFactory((TreeTableColumn<Player, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        column6Team2.setOnEditCommit((TreeTableColumn.CellEditEvent<Player, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().numberGoal.set(t.getNewValue());
        });

        final ObservableList<Player> observableListPlayersTeam1 = generateDummyData(14);
        final ObservableList<Player> observableListPlayersTeam2 = generateDummyData(14);

        treeTableViewTeam1.setRoot(new RecursiveTreeItem<>(observableListPlayersTeam1, RecursiveTreeObject::getChildren));
        treeTableViewTeam1.setShowRoot(false);
        treeTableViewTeam1.setEditable(true);
        treeTableViewCount1.textProperty()
                .bind(Bindings.createStringBinding(() -> PREFIX + treeTableViewTeam1.getCurrentItemsCount() + POSTFIX,
                        treeTableViewTeam1.currentItemsCountProperty()));

        treeTableViewAdd1.setOnMouseClicked((e) -> {
            observableListPlayersTeam1.add(createNewRandomPlayer());
            final IntegerProperty currCountProp1 = treeTableViewTeam1.currentItemsCountProperty();
            currCountProp1.set(currCountProp1.get() + 1);
        });
        treeTableViewRemove1.setOnMouseClicked((e) -> {
            observableListPlayersTeam1.remove(treeTableViewTeam1.getSelectionModel().selectedItemProperty().get().getValue());
            final IntegerProperty currCountProp1 = treeTableViewTeam1.currentItemsCountProperty();
            currCountProp1.set(currCountProp1.get() - 1);
        });

        searchField1.textProperty()
                .addListener(setupSearchField(treeTableViewTeam1));

        treeTableViewTeam2.setRoot(new RecursiveTreeItem<>(observableListPlayersTeam2, RecursiveTreeObject::getChildren));
        treeTableViewTeam2.setShowRoot(false);
        treeTableViewTeam2.setEditable(true);
        treeTableViewCount2.textProperty()
                .bind(Bindings.createStringBinding(() -> PREFIX + treeTableViewTeam2.getCurrentItemsCount() + POSTFIX,
                        treeTableViewTeam2.currentItemsCountProperty()));

        treeTableViewAdd2.setOnMouseClicked((e) -> {
            observableListPlayersTeam2.add(createNewRandomPlayer());
            final IntegerProperty currCountProp2 = treeTableViewTeam2.currentItemsCountProperty();
            currCountProp2.set(currCountProp2.get() + 1);
        });
        treeTableViewRemove2.setOnMouseClicked((e) -> {
            observableListPlayersTeam2.remove(treeTableViewTeam2.getSelectionModel().selectedItemProperty().get().getValue());
            final IntegerProperty currCountProp2 = treeTableViewTeam2.currentItemsCountProperty();
            currCountProp2.set(currCountProp2.get() - 1);
        });

        searchField2.textProperty()
                .addListener(setupSearchField(treeTableViewTeam2));
    }

    private ObservableList<Player> generateDummyData(final int numberOfEntries) {
        final ObservableList<Player> dummyData = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfEntries; i++) {
            dummyData.add(createNewRandomPlayer());
        }
        return dummyData;
    }

    private ChangeListener<String> setupSearchField(final JFXTreeTableView<Player> tableView) {
        return getStringChangeListener(tableView);
    }
}
