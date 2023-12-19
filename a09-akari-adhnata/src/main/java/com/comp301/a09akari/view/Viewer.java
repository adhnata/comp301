package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Viewer implements FXComponent, ModelObserver {
  private final ClassicMvcController controller;
  private final Model model;
  private final FXComponent gamePanel;
  private final FXComponent topPanel;
  private final Stage stage;

  public Viewer(ClassicMvcController controller, Model model, Stage stage) {
    this.controller = controller;
    this.model = model;
    this.gamePanel = new GameView(controller, model);
    this.topPanel = new TopView(model);
    this.stage = stage;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.getStyleClass().add("background");
    layout.getChildren().add(topPanel.render());
    layout.getChildren().add(gamePanel.render());
    return layout;
  }

  @Override
  public void update(Model model) {
    int x = 300, y = 300;
    Scene scene = new Scene(render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    stage.setMinHeight(x);
    stage.setMinWidth(y);
  }
}
