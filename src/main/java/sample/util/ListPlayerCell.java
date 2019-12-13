package sample.util;

import com.jfoenix.controls.JFXTreeTableRow;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import sample.model.Player;

public class ListPlayerCell extends JFXTreeTableRow<Player> {
	GridPane cellLayout;
	Label playerPost;
	Label playerIcon;
	Label playerName;
	
	public ListPlayerCell() {
		super();
		
		cellLayout = new GridPane();
		cellLayout.getStyleClass().add("cellLayout");

		playerPost = new Label();
		playerPost.getStyleClass().add("playerPost");
		GridPane.setHgrow(playerPost, Priority.ALWAYS);
		playerIcon = new Label();
		playerIcon.getStyleClass().add("playerIcon");
		GridPane.setHalignment(playerIcon, HPos.CENTER);

		playerName = new Label();
		playerName.setMaxWidth(135);
		playerName.getStyleClass().add("playerName");
		GridPane.setMargin(playerName, new Insets(3, 0, 0, 0));
		GridPane.setHgrow(playerName, Priority.ALWAYS);
		
		cellLayout.add(playerPost, 0, 0);
		cellLayout.add(playerIcon, 1, 0);
		cellLayout.add(playerName, 0, 1);
	}
	
	@Override
	protected void updateItem(Player item, boolean empty) {
		super.updateItem(item, empty);
		setText(null);
		if (empty) {
			setGraphic(null);
		} else {
			switch (item.getPost()) {
			case "joueur":
				playerPost.setText("Joueur");
				playerIcon.setText("");
				break;
			case "entraineur":
				playerPost.setText("Entraineur");
				playerIcon.setText("");
				break;
			case "arbitre":
				playerPost.setText("Arbitre");
				playerIcon.setText("");
				break;
			}
			playerName.setText(item.getFirstName()+" "+item.getLastName());
			setGraphic(cellLayout);
		}
	}
}
