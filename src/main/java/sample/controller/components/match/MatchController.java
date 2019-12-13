/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller.components.match;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.kordamp.ikonli.javafx.FontIcon;
import sample.controller.components.bar.TitleSectionBarMinController;
import sample.model.*;
import sample.util.Clock;
import sample.util.ListActionCell;
import javax.annotation.PostConstruct;

import static sample.controller.components.dashboard.DashboardController.initButtonInfos;

@ViewController(value = "/view/components/match/Match.fxml",  title = "Application de Handball")
public class MatchController extends TitleSectionBarMinController {

    private static final String PREFIX = "( ";
    private static final String POSTFIX = " )";
    public static final String CONTENT_PANE = "ContentMatch";
    private static final String[] ACTIONS = {"But", "Faute", "Tir", "Entrée", "Sortie", "Passe décisive", "Avertissement",
            "Exclusion", "Temps mort", "Carton jaune", "Carton rouge", "Carton bleu", "Arrêt du gardien",};
    private final String[] NAMES = {"Jean", "Moulin", "Benoit", "Morley", "Scott", "Kruger", "Lain",
            "Kennedy", "Gawron", "Han", "Hall", "Aydogdu", "Grace",
            "Spiers", "Perera", "Smith", "Connoly",
            "Sokolowski", "Chaow", "James", "June",};
    private final String[] POSTS = {"GB", "AiD", "AiG", "ArD", "ArG", "DmC", "Piv",};

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;
    @FXML
    private StackPane rootMatch;
    @FXML
    private JFXButton buttonLaunch;
    @FXML
    private FontIcon iconStart;
    @FXML
    private JFXButton buttonGoal;
    @FXML
    private JFXButton buttonTir;
    @FXML
    private JFXButton buttonPass;
    @FXML
    private JFXButton buttonFoul;
    @FXML
    private JFXButton buttonNewEnter;
    @FXML
    private JFXButton buttonNewOut;
    @FXML
    private JFXButton buttonWhistle;
    @FXML
    private Label labelTimer;
    @FXML
    private Label labelScoreTeam1;
    @FXML
    private Label labelScoreTeam2;
    @FXML
    private JFXTextField textFieldRefereeName;
    @FXML
    private JFXTextField textFieldTeamName1;
    @FXML
    private JFXTextField textFieldTeamName2;
    @FXML
    private JFXTextField textFieldCoachNameTeam1;
    @FXML
    private JFXTextField textFieldCoachNameTeam2;
    @ViewNode
    private Label labelTitleSection;
    @FXML
    private JFXButton buttonPopupInfoSection2;
    @FXML
    private JFXDialog dialogLaunchMatch;
    @FXML
    private JFXButton buttonAcceptDialogLaunchMatch;
    @FXML
    private JFXListView<Action> listViewAllAction;
    @FXML
    private JFXListView<Action> listViewImportantAction;
    @FXML
    private JFXListView<String> listViewActions;

    //TableView1
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

    //TableView2
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
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab tabPaneAllAction;
    @FXML
    private Tab tabPaneImportantAction;
    @FXML
    private Label labelStrikeTeam1;
    @FXML
    private Label labelStrikeTeam2;
    @FXML
    private Label labelFoulTeam1;
    @FXML
    private Label labelFoulTeam2;
    @FXML
    private Label labelYellowCardTeam1;
    @FXML
    private Label labelYellowCardTeam2;
    @FXML
    private Label labelRedCardTeam1;
    @FXML
    private Label labelRedCardTeam2;
    @FXML
    private Label labelBlueCardTeam1;
    @FXML
    private Label labelBlueCardTeam2;
    @FXML
    private Label labelExclusionTeam1;
    @FXML
    private Label labelExclusionTeam2;
    @FXML
    private Label labelDeadTimeTeam1;
    @FXML
    private Label labelDeadTimeTeam2;
    @FXML
    private Label labelSaveTeam1;
    @FXML
    private Label labelSaveTeam2;

    private final Random random = new SecureRandom();
    private ListView playersList;
    private Clock timer;
    private Match match;
    ArrayList <Action> arrayListActions = new ArrayList<>();
    ArrayList <Player> arrayListPlayers = new ArrayList<>();
    private Team team1;
    private Team team2;
    private Player player;
    private Referee Referee;
    private String RefereeName;
    private String TeamName1;
    private String TeamName2;
    private String coachTeam1;
    private String coachTeam2;
    public boolean focusOnTeam1 = false;
    public boolean focusOnTeam2 = false;
    public int nbFocusTeam;
    private ObservableList<String> observableListActionMatchTexte;
    private ObservableList<Action> observableListAllActionMatch;
    private ObservableList<String> observableListPlayers;
    private ObservableList<String> observableListPostsPlayers;
    private ObservableList<Player> observableListPlayersNotExcluded;
    private ObservableList<Player> observableListPlayersTeam1;
    private ObservableList<Player> observableListPlayersTeam2;
    private ObservableList<Player> observableListPlayersSubstituteTeam1;
    private ObservableList<Player> observableListPlayersSubstituteTeam2;

    int launchCount = 0;

    private Label labelDeadTimeCount;

    @PostConstruct
    public void init() throws Exception {
        //initTabPaneComponents();
        viewFlowContext.register(CONTENT_PANE, rootMatch);
        labelTitleSection.setText("Lancement d'un match");
        initButtonInfo();
        initButtonInfos(rootMatch, dialogLaunchMatch, buttonPopupInfoSection2, viewFlowContext, CONTENT_PANE, buttonAcceptDialogLaunchMatch);
        initScores();
        initStatistic();


        timer = new Clock(buttonLaunch, labelTimer);
        
        observableListActionMatchTexte = FXCollections.observableArrayList(ACTIONS);
        observableListPlayers = FXCollections.observableArrayList(NAMES);
        observableListPostsPlayers = FXCollections.observableArrayList(POSTS);
        observableListAllActionMatch = FXCollections.observableArrayList();

        //final ObservableList<Player> observableListPlayersTeam1 = generateDummyData(14);
        //final ObservableList<Player> observableListPlayersTeam2 = generateDummyData(14);
        //observableListPlayersNotExcluded = FXCollections.observableArrayList();
        listViewAllAction.setCellFactory(new Callback<ListView<Action>, ListCell<Action>>() {
            @Override
            public ListCell<Action> call(ListView<Action> param) {
                return new ListActionCell();
            }
        });
        setupNonSelectableListView();
        setupEditableTableViewPlayers();
        listViewActions.setItems(observableListActionMatchTexte);

        //listViewActions.getSelectionModel().selectedItemProperty().addListener(new ActionsTextChangeListener());
    }

    @FXML
    public void launchMatch() {
        launchCount = 1;
        iconStart.setIconLiteral("mdi-pause");
        setupNonEditableObjects();
        team1 = new Team(1, textFieldTeamName1.getPromptText(), textFieldCoachNameTeam1.getPromptText());
        team2 = new Team(2, textFieldTeamName2.getPromptText(), textFieldCoachNameTeam2.getPromptText());
        Player playersTeam1 = (Player) observableListPlayersTeam1;
        Player playersTeam2 = (Player) observableListPlayersTeam2;
        Referee = new Referee(textFieldRefereeName.getPromptText());
        if (!(textFieldTeamName1.getText().isEmpty()) && (!(textFieldTeamName2.getText().isEmpty())) && (!(textFieldRefereeName.getText().isEmpty())) && (!(textFieldCoachNameTeam1.getText().isEmpty())) && (!(textFieldCoachNameTeam2.getText().isEmpty())))
        {
            match = new Match(
                    launchCount,
                    textFieldRefereeName.getText(),
                    1,
                    textFieldTeamName1.getText(),
                    textFieldCoachNameTeam1.getText(),
                    0,
                    2,
                    textFieldTeamName2.getText(),
                    textFieldCoachNameTeam2.getText(),
                    0,
                    //(ArrayList<String>) observableListPlayers,
                    true,
                    arrayListActions);
            if (buttonLaunch.getText().equals("Lancer") || buttonLaunch.getText().equals("Relancer")) {
                timer.startStop();
                buttonLaunch.setText("Mettre en pause");
                treeTableViewTeam1.setOnMouseClicked(new setupClickOnTreeTableView());
                treeTableViewTeam2.setOnMouseClicked(new setupClickOnTreeTableView());
            } else if (buttonLaunch.getText().equals("Mettre en pause")) {
                timer.startStop();
                buttonLaunch.setText("Relancer");
            }
            else if (buttonLaunch.getText().equals("Arrêter le match")) {
                if (!timer.isStopped()) {
                    timer.startStop();
                }
            }

        }
        else if ((textFieldCoachNameTeam1.getText().isEmpty()) && (textFieldTeamName2.getText().isEmpty()) && (textFieldRefereeName.getText().isEmpty()) && (textFieldCoachNameTeam1.getText().isEmpty()) && (textFieldCoachNameTeam2.getText().isEmpty()))
        {
            match = new Match(
                    launchCount,
                    textFieldRefereeName.getPromptText(),
                    1,
                    textFieldTeamName1.getPromptText(),
                    textFieldCoachNameTeam1.getPromptText(),
                    0,
                    2,
                    textFieldTeamName2.getPromptText(),
                    textFieldCoachNameTeam2.getPromptText(),
                    0,
                    //(ArrayList<String>) observableListPlayers,
                    true,
                    arrayListActions);
            //match.newStart(textFieldRefereeName.getPromptText(), labelTimer.getText());
            if (buttonLaunch.getText().equals("Lancer") || buttonLaunch.getText().equals("Relancer")) {
                timer.startStop();
                buttonLaunch.setText("Mettre en pause");
                treeTableViewTeam1.setOnMouseClicked(new setupClickOnTreeTableView());
                treeTableViewTeam2.setOnMouseClicked(new setupClickOnTreeTableView());
            } else if (buttonLaunch.getText().equals("Mettre en pause")) {
                iconStart.setIconLiteral("mdi-play");
                timer.startStop();
                buttonLaunch.setText("Relancer");
            }
            else if (buttonLaunch.getText().equals("Arrêter le match")) {
                if (!timer.isStopped()) {
                    timer.startStop();
                }
            }
            launchCount = launchCount++;
        }
    }

    private Player createNewRandomPlayerTeam1() {
        return new Player(
                1,
                POSTS[random.nextInt(POSTS.length)],
                random.nextInt(14),
                NAMES[random.nextInt(NAMES.length)]);
    }

    private Player createNewRandomPlayerTeam2() {
        return new Player(
                2,
                POSTS[random.nextInt(POSTS.length)],
                random.nextInt(14),
                NAMES[random.nextInt(NAMES.length)]);
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

        final ObservableList<Player> observableListPlayersTeam1 = generateDummyDataTeam1(14);
        final ObservableList<Player> observableListPlayersTeam2 = generateDummyDataTeam2(14);

        treeTableViewTeam1.setRoot(new RecursiveTreeItem<>(observableListPlayersTeam1, RecursiveTreeObject::getChildren));
        treeTableViewTeam1.setShowRoot(false);
        treeTableViewTeam1.setEditable(true);
        treeTableViewCount1.textProperty()
                .bind(Bindings.createStringBinding(() -> PREFIX + treeTableViewTeam1.getCurrentItemsCount() + POSTFIX,
                        treeTableViewTeam1.currentItemsCountProperty()));

        treeTableViewAdd1.setOnMouseClicked((e) -> {
            observableListPlayersTeam1.add(createNewRandomPlayerTeam1());
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
            observableListPlayersTeam2.add(createNewRandomPlayerTeam2());
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

    private ObservableList<Player> generateDummyDataTeam1(final int numberOfEntries) {
        final ObservableList<Player> dummyData = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfEntries; i++) {
            dummyData.add(createNewRandomPlayerTeam1());
        }
        return dummyData;
    }

    private ObservableList<Player> generateDummyDataTeam2(final int numberOfEntries) {
        final ObservableList<Player> dummyData = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfEntries; i++) {
            dummyData.add(createNewRandomPlayerTeam2());
        }
        return dummyData;
    }

    private ChangeListener<String> setupSearchField(final JFXTreeTableView<Player> tableView) {
        return getStringChangeListener(tableView);
    }

    static ChangeListener<String> getStringChangeListener(JFXTreeTableView<Player> tableView) {
        return (o, oldVal, newVal) ->
                tableView.setPredicate(playerProp -> {
                    final Player player = playerProp.getValue();
                    return Integer.toString(player.number.get()).contains(newVal)
                            || player.getLastName().contains(newVal)
                            || Integer.toString(player.numberYellowCard.get()).contains(newVal)
                            || Integer.toString(player.numberRedCard.get()).contains(newVal)
                            || Integer.toString(player.numberGoal.get()).contains(newVal);
                });
    }

    private void setupNonEditableObjects() {
        textFieldRefereeName.setEditable(false);
        textFieldTeamName1.setEditable(false);
        textFieldTeamName2.setEditable(false);
        textFieldCoachNameTeam1.setEditable(false);
        textFieldCoachNameTeam2.setEditable(false);
        treeTableViewTeam1.setEditable(false);
        treeTableViewTeam2.setEditable(false);
    }

    private void setupNonSelectableListView() {
        listViewActions.setEditable(false);
    }

    private void initScores() {
        labelScoreTeam1.setText("0");
        labelScoreTeam2.setText("0");
    }

    private void initStatistic() {
        labelDeadTimeTeam1.setText("0");
        labelDeadTimeTeam2.setText("0");
        labelExclusionTeam1.setText("0");
        labelExclusionTeam2.setText("0");
        labelStrikeTeam1.setText("0");
        labelStrikeTeam2.setText("0");
        labelFoulTeam1.setText("0");
        labelFoulTeam2.setText("0");
        labelSaveTeam1.setText("0");
        labelSaveTeam2.setText("0");
        labelYellowCardTeam1.setText("0");
        labelYellowCardTeam2.setText("0");
        labelRedCardTeam1.setText("0");
        labelRedCardTeam2.setText("0");
        labelBlueCardTeam1.setText("0");
        labelBlueCardTeam2.setText("0");
    }

    public void refreshScore() {
        labelScoreTeam1.setText(Integer.toString(match.getScoreTeam1()));
        labelScoreTeam2.setText(Integer.toString(match.getScoreTeam2()));
        if (match.getScoreTeam1() > match.getScoreTeam2()) {
            //to do lancer une fenetre avec écrit qui a gagné
        } else {
            //to do lancer une fenetre avec écrit égalité
        }
    }

    public void resetSelectedAction() {
        listViewActions.getSelectionModel().clearSelection();
        observableListPlayers = FXCollections.observableArrayList();
        observableListPostsPlayers = FXCollections.observableArrayList();
        treeTableViewTeam1.getSelectionModel().clearSelection();
        treeTableViewTeam2.getSelectionModel().clearSelection();
    }

    class setupClickOnTreeTableView implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            TreeItem<Player> playerCurrentSelected;
            if (event.getClickCount() == 2 && !timer.isStopped()) {
                switch (listViewActions.getSelectionModel().getSelectedItem()) {
                    case "But":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("but équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newGoal(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelStrikeTeam1.setText(Integer.toString(match.getStrikeCountTeam1()));
                            refreshScore();
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("but équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newGoal(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelStrikeTeam2.setText(Integer.toString(match.getStrikeCountTeam2()));
                            refreshScore();
                        }
                        break;
                    case "Faute":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("faute équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newFoul(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelFoulTeam1.setText(Integer.toString(match.getFoulCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("faute équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newFoul(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelFoulTeam2.setText(Integer.toString(match.getFoulCountTeam2()));
                        }
                        break;
                    case "Tir":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("tir équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newStrike(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelStrikeTeam1.setText(Integer.toString(match.getStrikeCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("tir équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newStrike(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelStrikeTeam2.setText(Integer.toString(match.getStrikeCountTeam2()));
                        }
                        break;
                    case "Entrée":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("entrée équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newEnter(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("entrée équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newEnter(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                        }
                        break;
                    case "Sortie":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("sortie équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newOut(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("sortie équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newOut(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                        }
                        break;
                    case "Passe décisive":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("passe décisive équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newPass(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("passe décisive équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newPass(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                        }
                        break;
                    case "Exclusion":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("exclusion équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newExclusion(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelExclusionTeam1.setText(Integer.toString(match.getExclusionCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("exclusion équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newExclusion(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelExclusionTeam2.setText(Integer.toString(match.getExclusionCountTeam2()));
                        }
                        break;
                    case "Avertissement":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("disqualification équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newWarning(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("disqualification équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newWarning(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                        }
                        break;
                    case "Carton jaune":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("carton jaune équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newYellowCard(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelYellowCardTeam1.setText(Integer.toString(match.getYellowCardCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("carton jaune équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newYellowCard(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelYellowCardTeam2.setText(Integer.toString(match.getYellowCardCountTeam2()));
                        }
                        break;
                    case "Carton rouge":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("carton rouge équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newRedCard(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelRedCardTeam1.setText(Integer.toString(match.getRedCardCountTeam1()));

                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("carton rouge équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newRedCard(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelRedCardTeam2.setText(Integer.toString(match.getRedCardCountTeam2()));
                        }
                        break;
                    case "Carton bleu":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("carton bleu équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newBlueCard(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelBlueCardTeam1.setText(Integer.toString(match.getBlueCardCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()){
                            System.out.println("carton bleu équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newBlueCard(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelBlueCardTeam2.setText(Integer.toString(match.getBlueCardCountTeam2()));
                        }
                        break;
                    case "Arrêt du gardien":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("arrêt guardien équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newSave(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelSaveTeam1.setText(Integer.toString(match.getSaveCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()) {
                            System.out.println("arrêt guardien équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newSave(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelSaveTeam2.setText(Integer.toString(match.getSaveCountTeam2()));
                        }
                        break;
                    case "Temps mort":
                        if(!treeTableViewTeam1.getSelectionModel().isEmpty()){
                            System.out.println("temps mort équipe 1");
                            playerCurrentSelected = treeTableViewTeam1.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newDeadTime(treeTableViewTeam1.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam1.getSelectionModel().clearSelection();
                            labelDeadTimeTeam1.setText(Integer.toString(match.getDeadTimeCountTeam1()));
                        }
                        else if (!treeTableViewTeam2.getSelectionModel().isEmpty()) {
                            System.out.println("temps mort équipe 2");
                            playerCurrentSelected = treeTableViewTeam2.getSelectionModel().getSelectedItem();
                            System.out.println(playerCurrentSelected.getValue().lastName);
                            match.newDeadTime(treeTableViewTeam2.getSelectionModel().getSelectedItem(), labelTimer.getText());
                            treeTableViewTeam2.getSelectionModel().clearSelection();
                            labelDeadTimeTeam2.setText(Integer.toString(match.getDeadTimeCountTeam2()));
                        }
                        if (!timer.isStopped()) {
                            timer.startStop();
                            buttonLaunch.setText("Relancer");
                            iconStart.setIconLiteral("mdi-loopsync");
                        }
                        break;
                    default:
                        break;
                }
                observableListAllActionMatch = FXCollections.observableArrayList(match.getActions());
                listViewAllAction.setItems(null);
                listViewAllAction.setItems(observableListAllActionMatch);
                listViewAllAction.scrollTo(observableListAllActionMatch.get(observableListAllActionMatch.size() - 1));
                resetSelectedAction();
                event.consume();
            }
        }
    }
}