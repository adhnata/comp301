package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class TopView implements FXComponent {
  private final Model model;
  private ClassicMvcController controller;

  public TopView(Model model) {
    this.model = model;
  }

  @Override
  public Parent render() {
    VBox titles = new VBox();
    titles.setAlignment(Pos.CENTER);
    Text t = new Text("Movie Mania");
    Text sub = new Text("Mark tiles as movie posters by clicking.");
    if (model.isSolved()) {
      sub = new Text("Success!");
    }
    Parent title = t.render();
    title.getStyleClass().add("title");
    Parent subtitle = sub.render();
    subtitle.getStyleClass().add("subtitle");
    titles.getChildren().add(title);
    titles.getChildren().add(subtitle);
    return titles;
  }
}
