package cs3500.animator.view;

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
 * Represents an abstract SVG, to be used by any view with SVG capabilities.
 */
abstract class AbstractSVG extends JFrame {

  protected ArrayList<Shape> shapes;
  protected int tempo;

  /**
   * Default constructor.
   */
  public AbstractSVG() {
    this.tempo = 1;
    this.shapes = new ArrayList<>();
  }

  /**
   * Constructor for AbstractSVG.
   * @param shapes      The shapes for the SVG output
   * @param tempo       The tempo for the animation
   */
  public AbstractSVG(ArrayList<Shape> shapes, int tempo) {
    this.shapes = shapes;
    this.tempo = tempo;
  }

  /**
   * Converts animations within list into a text description in SVG format.
   * @param animations      The animations to describe
   * @return                The SVG text description of the animations
   */
  private String animationsToSVG(ArrayList<Animation> animations) {
    String toReturn = "";
    for (Animation a : animations) {
      switch (a.animationType()) {
        case "Color Change":
          toReturn = toReturn + changeColorToSVG((ChangeColorAnimation) a);
          break;
        case "Move":
          toReturn = toReturn + moveToSVG((MoveAnimation) a);
          break;
        case "Rotate":
          toReturn = toReturn + rotateToSVG((RotateAnimation) a);
          break;
        case "Scale":
          toReturn = toReturn + scaleToSVG((ScaleAnimation) a);
          break;
        default:
          this.showErrorMessage("Animation type incompatible!");
      }
    }
    return toReturn;
  }

  /**
   * Converts the given oval into a text description in SVG format.
   * @param oval    the oval to describe
   * @return        The SVG text description of the given Oval
   */
  private String ovalToSVG(Oval oval) {
    int r = oval.getColor().getRed();
    int g = oval.getColor().getGreen();
    int b = oval.getColor().getBlue();
    String toReturn = "";
    toReturn = toReturn + "<ellipse id=\"" + oval.getName() + "\" cx=\"" + oval.getPosnX()
            + "\" cy=\"" + oval.getPosnY() + "\" rx=\"" + oval.getWidth() + "\""
            + " ry=\"" + oval.getHeight() + "\" fill=\"rgb(" + r + "," + g + "," + b
            + ")\" visibility=\"visible\"";
    if (!(oval.getAnimationsToExecute().isEmpty())) {
      toReturn = toReturn + " >\n";
      ArrayList<Animation> animations = oval.getAnimationsToExecute();
      Collections.sort(animations);
      toReturn = toReturn + this.animationsToSVG(animations);
      toReturn = toReturn + "</ellipse>\n";
    }
    else {
      // no animations
      toReturn = toReturn + " />\n";
    }
    return toReturn;
  }

  /**
   * Converts the given rectangle into a text description in SVG format.
   * @param rect    the rectangle to describe
   * @return        The SVG text description of the given Rectangle
   */
  private String rectToSVG(Rectangle rect) {
    int r = rect.getColor().getRed();
    int g = rect.getColor().getGreen();
    int b = rect.getColor().getBlue();
    String toReturn = "";
    toReturn = toReturn + "<rect id=\"" + rect.getName() + "\" x=\"" + rect.getPosnX()
            + "\" y=\"" + rect.getPosnY() + "\" width=\"" + rect.getWidth() + "\""
            + " height=\"" + rect.getHeight() + "\" fill=\"rgb(" + r + "," + g + "," + b
            + ")\" visibility=\"visible\"";
    if (!(rect.getAnimationsToExecute().isEmpty())) {
      toReturn = toReturn + " >\n";
      ArrayList<Animation> animations = rect.getAnimationsToExecute();
      Collections.sort(animations);
      toReturn = toReturn + this.animationsToSVG(animations);
      toReturn = toReturn + "</rect>\n";
    } else {
      // no animations
      toReturn = toReturn + " />\n";
    }
    return toReturn;
  }

  /**
   * Converts the given ChangeColorAnimation into a text description in SVG format.
   * @param animation   the animation to describe
   * @return            the SVG text description of the given ChangeColorAnimation
   */
  private String changeColorToSVG(ChangeColorAnimation animation) {
    double duration = (animation.getEndTime() - animation.getStartTime()) / this.tempo;
    String toReturn = "    " + "<animate attributeName=\"fill\" attributeType=\"CSS\" "
            + "from=\"rgb(" + animation.getRFrom() + "," + animation.getGFrom() + ","
            + animation.getBFrom() + ")\" " + "to=\"rgb(" + animation.getRTo() + ","
            + animation.getGTo() + "," + animation.getBTo() + ")\""
            + " begin=\"" + (animation.getStartTime() / this.tempo) + "s\" dur=\""
            + duration + "s\""
            + " fill=\"freeze\" />\n";
    return toReturn;
  }

  /**
   * Converts the given MoveAnimation into a text description in SVG format.
   * @param animation   the animation to describe
   * @return            the SVG text description of the given MoveAnimation
   */
  private String moveToSVG(MoveAnimation animation) {
    String toReturn = "";
    double duration = (animation.getEndTime() - animation.getStartTime()) / this.tempo;
    // only add horizontal move description of there is a horizontal move
    if (animation.getPosnYFrom() != animation.getPosnYTo()) {
      toReturn = toReturn + "    " + "<animate attributeName=\"x\" attributeType=\"XML\" "
              + "begin=\"" + (animation.getStartTime() / this.tempo) + "s\" dur=\""
              + duration + "s\""
              + " fill=\"freeze\" from=\"" + animation.getPosnXFrom() + "\" to=\""
              + animation.getPosnXTo() + "\" />\n";
    }
    if (animation.getPosnXFrom() != animation.getPosnXTo()) {
      toReturn = toReturn + "    " + "<animate attributeName=\"y\" attributeType=\"XML\" "
              + "begin=\"" + (animation.getStartTime() / this.tempo) + "s\" dur=\""
              + duration + "s\""
              + " fill=\"freeze\" from=\"" + animation.getPosnYFrom() + "\" to=\""
              + animation.getPosnYTo() + "\" />\n";
    }
    return toReturn;
  }

  /**
   * Converts the given RotateAnimation into a text description in SVG format.
   * @param animation   the animation to describe
   * @return            the SVG text description of the given RotateAnimation
   */
  private String rotateToSVG(RotateAnimation animation) {
    double duration = (animation.getEndTime() - animation.getStartTime()) / this.tempo;
    String toReturn = "    "
            + "<animateTransform attributeName=\"transform\" attributeType=\"XML\""
            + " type=\"rotate\" from=\"" + animation.getAngleFrom() + "\" to = \""
            + animation.getAngleTo() + "\""  + " begin=\"" + (animation.getStartTime() / this.tempo)
            + "s\" dur=\"" + duration + "s\" fill=\"freeze\" />\n";
    return toReturn;
  }

  /**
   * Converts the given ScaleAnimation into a text description in SVG format.
   * @param animation   the animation to describe
   * @return            the SVG text description of the given ScaleAnimation
   */
  private String scaleToSVG(ScaleAnimation animation) {
    String toReturn = "";
    double duration = (animation.getStartTime() - animation.getEndTime()) / this.tempo;
    if (animation.getChangeHeight() != animation.getFromHeight()) {
      toReturn = toReturn + "    " + "<animate attributeName=\"x\" attributeType=\"XML\" "
              + "begin=\"" + (animation.getStartTime() / this.tempo) + "s\" dur=\""
              + duration + "s\""
              + " fill=\"freeze\" from=\"" + animation.getFromWidth() + "\" to=\""
              + animation.getChangeWidth() + "\" />\n";
    }
    if (animation.getChangeWidth() != animation.getFromWidth()) {
      toReturn = toReturn + "    " + "<animate attributeName=\"y\" attributeType=\"XML\" "
              + "begin=\"" + (animation.getStartTime() / this.tempo) + "s\" dur=\""
              + duration + "s\""
              + " fill=\"freeze\" from=\"" + animation.getFromHeight() + "\" to=\""
              + animation.getChangeHeight() + "\" />\n";
    }
    return toReturn;
  }

  /**
   * Outputs the SVG format description as a String.
   *
   * @return    the text description of the animation in SVG format as a String.
   */
  public String getOutput() {
    String result = "";
    result = result + "<svg width=\"1000\" height=\"500\" version=\"1.1\" \n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">\n";
    Collections.sort(shapes);
    for (Shape a: shapes) {
      if (a.getShapeType().equals("oval")) {
        result = result + ovalToSVG((Oval) a);
        result = result + "\n";
      }
      else {
        result = result + rectToSVG((Rectangle) a);
        result = result + "\n";
      }
    }
    result = result + "</svg>";
    return result;
  }

  /**
   * Shows an error message.
   * @param error     Message to include in error
   */
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,
            "Error",JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Gets the tempo of the animation.
   * @return    The tempo
   */
  public int getTempo() {
    int temp = this.tempo;
    return temp;
  }
}
