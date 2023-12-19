package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private final ClassicMvcController controller;

  public ControlView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane bOne = new HBox();
    bOne.setPadding(new Insets(23, 0, 0, 23));
    Button bTwo = new Button("Next");
    bTwo.setOnAction(
        (ActionEvent e) -> {
          controller.clickNextPuzzle();
          controller.clickResetPuzzle();
        });
    Button bThree = new Button("Previous");
    bThree.setOnAction(
        (ActionEvent e) -> {
          controller.clickPrevPuzzle();
          controller.clickResetPuzzle();
        });
    Button resetButton = new Button("Reset");
    resetButton.setOnAction(
        (ActionEvent e) -> {
          controller.clickResetPuzzle();
        });
    Button randomButton = new Button("Random");
    randomButton.setOnAction(
        (ActionEvent e) -> {
          controller.clickRandPuzzle();
          controller.clickResetPuzzle();
        });
    bOne.getChildren().add(bTwo);
    bOne.getChildren().add(bThree);
    bOne.getChildren().add(randomButton);
    bOne.getChildren().add(resetButton);
    for (Node p : bOne.getChildren()) {
      p.getStyleClass().add("navbutton");
    }
    bOne.getStyleClass().add("buttonpanel");
    return bOne;
  }
}
