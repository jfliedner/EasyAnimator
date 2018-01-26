package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IAnimator;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;

/**
 * Main class for EasyAnimator, contains the main method.
 */
public final class EasyAnimator {

  /**
   * Enums for possible flags from command line.
   */
  private enum FLAGS { FILE, VIEW, OUTPUT, SPEED }

  /**
   * Main method.
   * @param args      Command line arguments.
   */
  public static void main(String[] args) {
    String fileName = "";
    IView view = new TextView();
    int speed = 1;
    int tick = 1;
    FileOutputStream f;
    AnimationFileReader fileReader = new AnimationFileReader();
    IAnimator model = new AnimatorModel();

    // Parsing through command line arguments
    int i = 0;
    while (i < args.length) { // not empty
      FLAGS flag = textToFlag(args[i]); // flag
      switch (flag) { // what type of flag?
        case FILE: // the specified animation file
          if (i + 1 < args.length) {
            String animationFile = args[i + 1];
            if (isValidSet(FLAGS.FILE, animationFile)) {
              fileName = animationFile;
              i += 2;
              continue;
            }
            else {
              view.showErrorMessage("Not a valid animation file"); // pop-up if invalid
              System.exit(1);
            }
          }
          else {
            view.showErrorMessage("Not a valid animation file"); // pop-up if no file specified
            System.exit(1);
          }
          break;
        case VIEW: // the specified view
          if (i + 1 < args.length) {
            String viewType = args[i + 1];
            if (isValidSet(FLAGS.VIEW, viewType)) {
              view = FactoryView.factory(viewType);
              i += 2;
            }
            else {
              view.showErrorMessage("Not a valid view type"); // pop-up if invalid
              System.exit(1);
            }
          }
          else {
            view.showErrorMessage("No view specified"); // pop-up if no view specified
            System.exit(1);
          }
          break;
        case OUTPUT: // the specified output
          if (i + 1 < args.length) {
            String outTo = args[i + 1];
            if (!isValidSetFlag(outTo)) {
              if (isValidOut(outTo)) {
                if (outTo.contains(".txt") || outTo.contains(".svg")) {
                  try { // using FileOutputStream, setOut, and PrintStream if writing to a file
                    f = new FileOutputStream(outTo);
                  }
                  catch (FileNotFoundException e) {
                    return;
                  }
                  System.setOut(new PrintStream(f));
                  i += 2;
                }
                else { // if not, don't need to do anything
                  i += 2;
                }
              }
              else {
                view.showErrorMessage("Not a valid out"); // pop-up if invalid out
                System.exit(1);
              }
            }
            else { // nothing specified for the output
              // default of System.out
              i += 1;
            }
          }
          break;
        case SPEED: // the specified speed/tempo
          if (i + 1 < args.length) {
            String speedString = args[i + 1];
            if (!isValidSetFlag(speedString)) {
              try {
                speed = Integer.parseInt(speedString); // checking that it's a number
                i += 2;
              }
              catch (NumberFormatException e) {
                view.showErrorMessage("Not a valid speed"); // pop-up if not a number
                System.exit(1);
              }
            }
            else {
              speed = 1; // nothing specified so default
              i += 1;
            }
          }
          break;
        default: view.showErrorMessage("Error"); // invalid flag
          System.exit(1);
      }
    }

    // sets the model based on the input file
    try {
      model = fileReader.readFile(fileName, new AnimatorModel.Builder());
    }
    catch (FileNotFoundException e) {
      // empty catch, controller will throw exception if fileName is invalid
    }

    // adds the shapes to the view
    view.setShapes(model.getShapes());

    // timer handling
    if (view.getViewType().equals("Visual")) {
      AnimationController control = new AnimationController(model, view);
      control.goController();
    } else if (view.getViewType().equals("Text")) {
      view = new TextView(System.out, model.getShapes(), speed);
      view.makeVisible();
    } else if (view.getViewType().equals("Hybrid")) {
      InteractiveController control = new InteractiveController(model, (HybridView) view);
      control.goController();
    } else {
      view = new SVGView(System.out, model.getShapes(), speed);
      view.makeVisible();
    }
  }

  /**
   * Checks if the string is a valid flag.
   * @param check     String to check.
   * @return          If it's a valid flag.
   */
  private static boolean isValidSetFlag(String check) {
    return check.equals("-if") || check.equals("-iv")
            || check.equals("-o") || check.equals("-speed");
  }

  /**
   * Converts the string of a flag into its enum version.
   * @param convert       String to convert.
   * @return              Its respective enum.
   */
  private static FLAGS textToFlag(String convert) {
    switch (convert) {
      case "-if":
        return FLAGS.FILE;
      case "-iv":
        return FLAGS.VIEW;
      case "-o":
        return FLAGS.OUTPUT;
      case "-speed":
        return  FLAGS.SPEED;
      default: throw new IllegalArgumentException("Invalid");
    }
  }

  /**
   * Checks if the set of a flag and a string are valid.
   * @param flag            The flag
   * @param check           The string to check against
   * @return                If the two are a valid set
   */
  private static boolean isValidSet(FLAGS flag, String check) {
    switch (flag) {
      case FILE:
        return check.contains(".txt");
      case VIEW:
        return check.equals("svg") || check.equals("visual") || check.equals("text")
                || check.equals("interactive");
      case SPEED:
        return true;
      case OUTPUT:
        return true;
      default: throw new IllegalArgumentException("Invalid");
    }
  }

  /**
   * Checks if the string is a valid out.
   * @param check         The string to check.
   * @return              If the string is a valid out.
   */
  private static boolean isValidOut(String check) {
    return check.contains(".txt") || check.contains(".svg") || check.equals("out");
  }
}
