package com.comp301.a06image;

import java.awt.*;

public class CircleDecorator implements Image {
  private final Image image;
  private final int cx;
  private final int cy;
  private final int radius;
  private final Color color;

  public CircleDecorator(Image image, int cx, int cy, int radius, Color color) {
    if (radius < 0 || image == null) {
      throw new IllegalArgumentException();
    }
    this.image = image;
    this.cx = cx;
    this.cy = cy;
    this.radius = radius;
    this.color = color;
  }

  @Override
  public Color getPixelColor(int x, int y) {
    double distance = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));

    if (distance < radius) {
      return color;
    } else {
      return image.getPixelColor(x, y);
    }
  }

  @Override
  public int getWidth() {
    return image.getWidth();
  }

  @Override
  public int getHeight() {
    return image.getHeight();
  }

  @Override
  public int getNumLayers() {
    return image.getNumLayers() + 1;
  }
}
