package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import cs3500.animator.model.Animation;
import cs3500.animator.model.ChangeColorAnimation;
import cs3500.animator.model.MoveAnimation;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.RotateAnimation;
import cs3500.animator.model.ScaleAnimation;
import cs3500.animator.model.Shape;


/**
 * The view implementation which produces a textual description of the animation that follows the
 * format set out by the assignment instructions. Extends JFrame and implements IView.
 */
public class TextView extends JFrame implements IView {

  private Appendable textOutput;
  private ArrayList<Shape> shapes;
  private int tempo;

  /**
   * Constructor which takes in an Appendable, ArrayList, and an integer for the tempo.
   * @param ap            The Appendable
   * @param shapes        The ArrayList of Shapes
   * @param tempo         The tempo, represented as an int
   */
  public TextView(Appendable ap, ArrayList<Shape> shapes, int tempo) {
    this.textOutput = ap;
    this.shapes = shapes;
    this.tempo = tempo;
  }

  /**
   * Default constructor, which sets everything to its default values.
   */
  public TextView() {
    shapes = new ArrayList<>();
    textOutput = System.out;
    tempo = 1;
  }

  /**
   * Converts the given Oval object into a text description with its details.
   *
   * @param oval    the oval to describe
   * @return        String representation of this Oval's descriptions
   */
  private String ovalToText(Oval oval) {
    String toReturn = ("Name: " + oval.getName() + "\nType: oval\nCenter: ("
            + oval.getPosnX() + "," + oval.getPosnY() + "), X radius: " + oval.getWidth()
            + ", Y radius: " + oval.getHeight() + ", Color: (" + oval.getColor().getRed()
            + "," + oval.getColor().getGreen() + "," + oval.getColor().getBlue()
            + ")\nAppears at t=" + (oval.getTimeAppear() / this.tempo) + "s\nDisappears at t="
            + (oval.getTimeDisappear() / this.tempo) + "s\n");
    return toReturn;
  }

  /**
   * Converts the given Rectangle object into a text description with its details.
   *
   * @param rect    the rectangle to describe
   * @return        String representation of this Rectangle's descriptions
   */
  private String rectToText(Rectangle rect) {
    String toReturn = ("Name: " + rect.getName() + "\nType: rectangle\nLower-left corner: ("
            + rect.getPosnX() + "," + rect.getPosnY() + "), Width: " + rect.getWidth()
            + ", Height: " + rect.getHeight() + ", Color: (" + rect.getColor().getRed()
            + "," + rect.getColor().getGreen() + "," + rect.getColor().getBlue()
            + ")\nAppears at t=" + rect.getTimeAppear() / this.tempo + "s\nDisappears at t="
            + rect.getTimeDisappear() / this.tempo + "s\n");
    return toReturn;
  }

  /**
   * Converts the given ChangeColorAnimation animation into a text description.
   *
   * @param animation   the animation to describe and output
   * @return            String representation of the ChangeColorAnimation
   */
  private String changeColorToText(ChangeColorAnimation animation) {
    String toReturn = ("Shape " + animation.getShapeToAnimate().getName() + " changes color from ("
            + animation.getRFrom() + "," + animation.getGFrom() + "," + animation.getBFrom()
            + ") to (" + animation.getRTo() + "," + animation.getGTo() + "," + animation.getBTo()
            + ") from t=" + (animation.getStartTime() / this.tempo) + "s to t="
            + (animation.getEndTime() / this.tempo) + "s\n");
    return toReturn;
  }

  /**
   * Converts the given MoveAnimation into a text description of the animation.
   * @param animation   the animation to describe and output
   * @return            String representation of the MoveAnimation
   */
  private String moveToText(MoveAnimation animation) {
    String toReturn = ("Shape " + animation.getShapeToAnimate().getName() + " moves from ("
            + animation.getPosnXFrom() + "," + animation.getPosnYFrom() + ") to ("
            + animation.getPosnXTo() + "," + animation.getPosnYTo()
            + ") from t=" + (animation.getStartTime() / this.tempo) + "s to t="
            + (animation.getEndTime() / this.tempo) + "s\n");
    return toReturn;
  }

  /**
   * Converts the given RotateAnimation into a text description of the animation.
   * @param animation   the animation to describe and output
   * @return            the String representation of the RotateAnimation
   */
  private String rotateToText(RotateAnimation animation) {
    String toReturn = ("Shape " + animation.getShapeToAnimate().getName()
            + " rotates from angle " + animation.getAngleFrom() + " to angle "
            + animation.getAngleTo() + " from t=" + (animation.getStartTime() / this.tempo)
            + "s to t=" + (animation.getEndTime() / this.tempo) + "s\n");
    return toReturn;
  }

  /**
   * Converts the given ScaleAnimation into a text description of the animation.
   * @param animation   the animation to describe and output
   * @return            the String representation of the ScaleAnimation
   */
  private String scaleToText(ScaleAnimation animation) {
    String toReturn = ("Shape " + animation.getShapeToAnimate().getName() + " scales from Width: "
            + animation.getFromWidth() + ", Height: " + animation.getFromHeight() + " to Width: "
            + animation.getChangeWidth() + ", Height: "
            + animation.getChangeHeight() + " from t=" + (animation.getStartTime() / this.tempo)
            + "s to t=" + (animation.getEndTime() / this.tempo) + "s\n");
    return toReturn;
  }

  /**
   * Outputs the text description as a String.
   *
   * @return    the text description of the animation as a String.
   */
  public String getOutput() {
    ArrayList<Animation> animations = new ArrayList<>();
    Collections.sort(this.shapes);
    String result = "";
    for (Shape a : this.shapes) {
      if (a.getShapeType().equals("oval")) {
        result = result + this.ovalToText((Oval) a);
      } else {
        result = result + this.rectToText((Rectangle) a);
      }
      for (Animation b : a.getAnimationsToExecute()) {
        animations.add(b);
      }
    }
    Collections.sort(animations);
    for (Animation a : animations) {
      switch (a.animationType()) {
        case "Color Change":
          result = result + changeColorToText((ChangeColorAnimation) a);
          break;
        case "Move":
          result = result + moveToText((MoveAnimation) a);
          break;
        case "Rotate":
          result = result + rotateToText((RotateAnimation) a);
          break;
        case "Scale":
          result = result + scaleToText((ScaleAnimation) a);
          break;
        default:
          this.showErrorMessage("Animation type incompatible!");
      }
    }

    return result;
  }

  /**
   * Outputs the Text view, which is the text description of the shapes and its animations
   * in the format set out by the assignment instructions.
   */
  private void output() {
    try {
      this.textOutput = textOutput.append(this.getOutput());
    } catch (IOException e) {
      // empty, if appendable causes an IOException
    }
  }

  @Override
  public void makeVisible() {
    this.output();
  }

  @Override
  public void setShapes(ArrayList<Shape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,
            "Error",JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public String getViewType() {
    return "Text";
  }
}
