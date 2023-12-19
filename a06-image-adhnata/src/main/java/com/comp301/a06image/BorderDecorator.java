package com.comp301.a06image;

import java.awt.*;

public class BorderDecorator implements Image {
  private final Image image;
  private final int thiccness;
  private final Color borderColor;

  public BorderDecorator(Image image, int thiccness, Color borderColor) {
    if (thiccness < 0 || image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    this.thiccness = thiccness;
    this.borderColor = borderColor;
  }

  @Override
  public int getWidth() {
    return image.getWidth() + 2 * thiccness;
  }

  @Override
  public int getHeight() {
    return image.getHeight() + 2 * thiccness;
  }

  @Override
  public int getNumLayers() {
    return image.getNumLayers() + 1;
  }

  @Override
  public Color getPixelColor(int x, int y) {
    int width = image.getWidth();
    int height = image.getHeight();
    if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
      throw new IllegalArgumentException();
    }
    if (x < thiccness || x >= width + thiccness || y < thiccness || y >= height + thiccness) {
      return borderColor;
    } else {
      return image.getPixelColor(x - thiccness, y - thiccness);
    }
  }
}
