package sample.util;

import com.jfoenix.svg.SVGGlyph;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.model.Action;
import sample.model.Match;

public class ListActionCell extends ListCell<Action> {

    GridPane gridCell;
    Text timer;
    Text action;
    Text player;
    Text team;
    Text count;
    TextFlow label;
    Match match;
    SVGGlyph icon;

    public ListActionCell() {
        super();
        gridCell = new GridPane();
        timer = new Text();
        action = new Text();
        player = new Text();
        label = new TextFlow();
        icon = new SVGGlyph();
    }

    @Override
    protected void updateItem(Action item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            setGraphic(null);
        } else {
            ObservableList<Action> list = getListView().getItems();
            timer.setText(item.getTimer());
            switch (item.getAction()) {
                case "but":
                    icon = new SVGGlyph(1, "Goal", "M770 204.667q84 96 84 222 0 4-1 8t-1 6l-42 32-60-20-62-186 34-58zM608 298.667l58 172-154 108-154-108 58-172h192zM618 102.667l30 64-26 46h-218l-28-46 30-64q50-18 106-18t106 18zM302 208.667l34 58-62 186-60 20-42-32q0-2-1-6t-1-8q0-52 25-117t59-105zM412 752.667q-50-14-105-56t-83-86l18-58 58-18 170 118v60zM554 712.667v-60l170-118 58 18 18 58q-28 44-83 86t-105 56zM512 852.667q176 0 301-125t125-301-125-301-301-125-301 125-125 301 125 301 301 125z", Color.BLACK);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "tir":
                    icon = new SVGGlyph(2, "Strike", "M32 14h-3.154c-0.864-5.57-5.276-9.982-10.846-10.846v-3.154h-4v3.154c-5.57 0.864-9.982 5.276-10.846 10.846h-3.154v4h3.154c0.864 5.57 5.276 9.982 10.846 10.846v3.154h4v-3.154c5.57-0.864 9.982-5.276 10.846-10.846h3.154v-4zM24.776 14h-3.118c-0.603-1.705-1.953-3.056-3.658-3.658v-3.118c3.36 0.765 6.010 3.416 6.776 6.776zM16 18c-1.105 0-2-0.895-2-2s0.895-2 2-2c1.105 0 2 0.895 2 2s-0.895 2-2 2zM14 7.224v3.118c-1.705 0.603-3.056 1.953-3.658 3.658h-3.118c0.765-3.36 3.416-6.010 6.776-6.776zM7.224 18h3.118c0.603 1.705 1.953 3.056 3.658 3.658v3.118c-3.36-0.765-6.010-3.416-6.776-6.776zM18 24.776v-3.118c1.705-0.603 3.056-1.953 3.658-3.658h3.118c-0.765 3.36-3.416 6.010-6.776 6.776z", Color.ORANGE);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "faute":
                    icon = new SVGGlyph(3, "Foul", "M12 21.984q-0.844 0-1.43-0.563t-0.586-1.406h4.031q0 0.797-0.609 1.383t-1.406 0.586zM12.984 12v-3.984h-1.969v3.984h1.969zM12.984 15.984v-1.969h-1.969v1.969h1.969zM18 15.984l2.016 2.016v0.984h-16.031v-0.984l2.016-2.016v-4.969q0-2.344 1.195-4.078t3.305-2.25v-0.703q0-0.609 0.422-1.055t1.078-0.445 1.078 0.445 0.422 1.055v0.703q2.109 0.516 3.305 2.25t1.195 4.078v4.969z", Color.rgb(229, 28, 35));
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "avertissement":
                    icon = new SVGGlyph(4, "Warning", "M12.984 14.016v-4.031h-1.969v4.031h1.969zM12.984 18v-2.016h-1.969v2.016h1.969zM0.984 21l11.016-18.984 11.016 18.984h-22.031z", Color.ORANGE);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " pour ");
                    break;
                case "sortie":
                    icon = new SVGGlyph(5, "Out", "M20.016 12l-8.016 8.016-8.016-8.016 1.453-1.406 5.578 5.578v-12.188h1.969v12.188l5.625-5.578z", Color.rgb(229, 28, 35));
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "entrée":
                    icon = new SVGGlyph(6, "Enter", "M3.984 12l8.016-8.016 8.016 8.016-1.453 1.406-5.578-5.578v12.188h-1.969v-12.188l-5.625 5.578z", Color.GREEN);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "passe décisive":
                    icon = new SVGGlyph(8, "Pass", "M9 16.172l10.594-10.594 1.406 1.406-12 12-5.578-5.578 1.406-1.406z", Color.BLACK);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "temps mort":
                    icon = new SVGGlyph(9, "DeadTime", "M12 20.016q2.906 0 4.945-2.063t2.039-4.969-2.039-4.945-4.945-2.039-4.945 2.039-2.039 4.945 2.039 4.969 4.945 2.063zM19.031 7.406q0.797 1.031 1.383 2.648t0.586 2.93q0 3.703-2.625 6.352t-6.375 2.648-6.375-2.648-2.625-6.352 2.625-6.352 6.375-2.648q1.266 0 2.93 0.609t2.695 1.406l1.406-1.453q0.75 0.609 1.406 1.406zM11.016 14.016v-6h1.969v6h-1.969zM15 0.984v2.016h-6v-2.016h6z", Color.BLACK);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
                case "carton jaune":
                    icon = new SVGGlyph(9, "YellowCard", "M1024,831.998C1024,831.998,1024,192.002,1024,192.002C1024,139.102,1005.222,93.892,967.665,56.335C930.108,18.778,884.898,0,831.998,0C831.998,0,192.002,0,192.002,0C139.102,0,93.892,18.778,56.335,56.335C18.778,93.892,0,139.102,0,192.002C0,192.002,0,831.998,0,831.998C0,884.898,18.778,930.108,56.335,967.665C93.892,1005.222,139.102,1024,192.002,1024C192.002,1024,831.998,1024,831.998,1024C884.898,1024,930.108,1005.222,967.665,967.665C1005.222,930.108,1024,884.898,1024,831.998C1024,831.998,1024,831.998,1024,831.998", Color.rgb(255, 235, 59));
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " pour ");
                    break;
                case "carton rouge":
                    icon = new SVGGlyph(10, "RedCard", "M1024,831.998C1024,831.998,1024,192.002,1024,192.002C1024,139.102,1005.222,93.892,967.665,56.335C930.108,18.778,884.898,0,831.998,0C831.998,0,192.002,0,192.002,0C139.102,0,93.892,18.778,56.335,56.335C18.778,93.892,0,139.102,0,192.002C0,192.002,0,831.998,0,831.998C0,884.898,18.778,930.108,56.335,967.665C93.892,1005.222,139.102,1024,192.002,1024C192.002,1024,831.998,1024,831.998,1024C884.898,1024,930.108,1005.222,967.665,967.665C1005.222,930.108,1024,884.898,1024,831.998C1024,831.998,1024,831.998,1024,831.998", Color.rgb(229, 28, 35));
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " pour ");
                    break;
                case "carton bleu":
                    icon = new SVGGlyph(11, "BlueCard", "M1024,831.998C1024,831.998,1024,192.002,1024,192.002C1024,139.102,1005.222,93.892,967.665,56.335C930.108,18.778,884.898,0,831.998,0C831.998,0,192.002,0,192.002,0C139.102,0,93.892,18.778,56.335,56.335C18.778,93.892,0,139.102,0,192.002C0,192.002,0,831.998,0,831.998C0,884.898,18.778,930.108,56.335,967.665C93.892,1005.222,139.102,1024,192.002,1024C192.002,1024,831.998,1024,831.998,1024C884.898,1024,930.108,1005.222,967.665,967.665C1005.222,930.108,1024,884.898,1024,831.998C1024,831.998,1024,831.998,1024,831.998", Color.rgb(96, 184, 226));
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " pour ");
                    break;
                case "exclusion":
                    icon = new SVGGlyph(12, "Exclusion", "M3.984 5.016v13.969h8.016v2.016h-8.016q-0.797 0-1.383-0.609t-0.586-1.406v-13.969q0-0.797 0.586-1.406t1.383-0.609h8.016v2.016h-8.016zM17.016 6.984l4.969 5.016-4.969 5.016-1.406-1.453 2.578-2.578h-10.172v-1.969h10.172l-2.578-2.625z", Color.BLACK);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de 2 minutes pour ");
                    break;
                case "arrêt":
                    icon = new SVGGlyph(13, "Save", "M23.016 5.484v14.531q0 1.641-1.195 2.813t-2.836 1.172h-7.266q-1.688 0-2.859-1.172l-7.875-8.016q1.266-1.219 1.313-1.219 0.328-0.281 0.797-0.281 0.328 0 0.609 0.141l4.313 2.438v-11.906q0-0.609 0.445-1.055t1.055-0.445 1.055 0.445 0.445 1.055v7.031h0.984v-9.516q0-0.656 0.422-1.078t1.078-0.422 1.078 0.422 0.422 1.078v9.516h0.984v-8.531q0-0.609 0.445-1.055t1.055-0.445 1.055 0.445 0.445 1.055v8.531h1.031v-5.531q0-0.609 0.445-1.055t1.055-0.445 1.055 0.445 0.445 1.055z", Color.BLACK);
                    icon.setSize(16, 16);
                    action.setText(" : " + item.getAction() + " de ");
                    break;
            }
            player.setText(item.getPlayer());
            label.getChildren().clear();
            label.getChildren().addAll(icon, timer, action, player);

            if (list.indexOf(item) != 0 && list.get((list.indexOf(item) - 1)).getTeamName().equals(item.getTeamName())) {
                gridCell.getChildren().clear();
                gridCell.add(icon, 0, 0);
                gridCell.add(label, 0, 0);
            } else {
                team = new Text(item.getTeamName());
                team.getStyleClass().add("Nom de l'équipe");
                if (list.indexOf(item) != 0) {
                    GridPane.setMargin(team, new Insets(20, 0, 20, 0));
                }
                gridCell.getChildren().clear();
                gridCell.add(team, 0, 0);
                gridCell.add(icon, 0, 1);
                gridCell.add(label, 0, 1);
            }
            setGraphic(gridCell);
        }
    }

}
