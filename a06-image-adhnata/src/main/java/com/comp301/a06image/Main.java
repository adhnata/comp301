package com.comp301.a06image;

import javafx.application.Application;

import java.awt.*;
import java.io.IOException;

public class Main {
  /**
   * Creates and returns an image object for testing. The image that this method produces will be
   * automatically displayed on the screen when main() is run below. Use this method to test
   * different decorators.
   */
  public static Image makeImage() throws IOException {
    Image baseImage = new PictureImage("img/kmp.jpg");
    Image redBorderImage = new BorderDecorator(baseImage, 5, new Color(255, 0, 0));
    Image blueBorderImage = new BorderDecorator(redBorderImage, 50, new Color(0, 0, 255));
    Image yellowCircleImage =
        new CircleDecorator(blueBorderImage, 50, 50, 40, new Color(255, 255, 0));
    Image orangeSquareImage =
        new SquareDecorator(yellowCircleImage, 100, 100, 40, new Color(200, 80, 10));
    Image finalImage = new ZoomDecorator(orangeSquareImage, 2);
    return finalImage;
  }

  /**
   * Use this method for testing your code. When main() runs, the image you created and decorated in
   * the makeImage() method above will be generated and displayed on the screen. You don't need to
   * make any changes to this main() method.
   */
  public static void main(String[] args) {
    Application.launch(ImageDisplay.class, args);
  }
}
