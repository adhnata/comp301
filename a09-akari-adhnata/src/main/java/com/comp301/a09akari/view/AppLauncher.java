package com.comp301.a09akari.view;

import javafx.application.Application;
import javafx.stage.Stage;
import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;

public class AppLauncher extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
    Model model = new ModelImpl(library);
    String[] startText = {
      "Here we go", "Ready", "So realski", "Opium", "Bird is the word", "Big broski", "That's tuffy"
    };
    String text = startText[(int) (Math.random() * startText.length)];
    stage.setTitle("Adhi Akari: " + text);
    ClassicMvcController controller = new ControllerImpl(model);
    Viewer view = new Viewer(controller, model, stage);
    model.addObserver(view);
    view.update(model);
    stage.show();
  }
}
