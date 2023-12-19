package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameView implements FXComponent {
  private final ClassicMvcController controller;
  private final Model model;
  private final GameBoard board;
  private final ControlView controls;

  public GameView(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
    this.board = new GameBoard(controller, model);
    this.controls = new ControlView(controller);
  }

  @Override
  public Parent render() {
    VBox main = new VBox();
    int padding = 15;
    main.setPadding(new Insets(padding, padding, padding, padding));
    main.getChildren().add(board.render());
    main.getChildren().add(controls.render());
    main.setAlignment(Pos.CENTER);
    return main;
  }
}
