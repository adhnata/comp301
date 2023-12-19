package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoard implements FXComponent {
  private final ClassicMvcController controller;
  private final Model model;

  public GameBoard(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    GridPane gridPaneImpl = new GridPane();
    gridPaneImpl.getStyleClass().add("board");
    gridPaneImpl.setHgap(2);
    gridPaneImpl.setVgap(2);
    for (int i = 0; i < model.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < model.getActivePuzzle().getWidth(); j++) {
        gridPaneImpl.add(makeTile(i, j), j, i);
      }
    }
    return gridPaneImpl;
  }

  private Button makeTile(int r, int c) {

    CellType cellTypeImpl = model.getActivePuzzle().getCellType(r, c);
    Button buttonForTile = new Button("  ");
    if (cellTypeImpl == CellType.CORRIDOR) {
      if (model.isLamp(r, c)) {
        Image goatImage = new Image("The Box Submission-Adhi.JPG");

        ImageView goatImageView = new ImageView(goatImage);
        goatImageView.setFitHeight(14);
        goatImageView.setFitWidth(14);

        buttonForTile = new Button("", goatImageView);
        if (!model.isLampIllegal(r, c)) {
          buttonForTile.getStyleClass().add("lamp");

        } else {
          buttonForTile.getStyleClass().add("invalid");
        }
      } else if (model.isLit(r, c)) {
        buttonForTile.getStyleClass().add("lit");
      } else {
        buttonForTile.getStyleClass().add("corridor");
      }
    } else if (cellTypeImpl == CellType.WALL) {
      buttonForTile.getStyleClass().add("wall");
    } else if (cellTypeImpl == CellType.CLUE) {
      buttonForTile = new Button(Integer.toString(model.getActivePuzzle().getClue(r, c)));
      buttonForTile.getStyleClass().add("wall");
    }
    buttonForTile.setOnAction((ActionEvent e) -> controller.clickCell(r, c));
    return buttonForTile;
  }
}
