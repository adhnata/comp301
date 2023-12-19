package com.comp301.a09akari.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public class Text implements FXComponent {
  private final String text;

  public Text(String text) {
    this.text = text;
  }

  @Override
  public Parent render() {
    return new Label(text);
  }
}
