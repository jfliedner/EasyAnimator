package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import cs3500.animator.model.Shape;

/**
 * The view implementation which produces a textual description of the animation that is
 * compliant with SVG file format. Extends JFrame and implements IView.
 */
public class SVGView extends AbstractSVG implements IView {

  private Appendable svgOutput;

  /**
   * Constructor which takes in an Appendable, ArrayList, and an integer for the tempo.
   * @param ap            The Appendable
   * @param shapes        The ArrayList of Shapes
   * @param tempo         The tempo, represented as an int
   */
  public SVGView(Appendable ap, ArrayList<Shape> shapes, int tempo) {
    super(shapes, tempo);
    this.svgOutput = ap;
  }

  /**
   * Default constructor, which sets everything to its default values.
   */
  public SVGView() {
    super();
    svgOutput = System.out;
  }


  /**
   * Outputs the SVG view, which is the text description of the shapes and its animations
   * in SVG format.
   */
  private void output() {
    try {
      svgOutput = svgOutput.append(getOutput());
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
  public void refresh() {
    this.repaint();
  }

  @Override
  public String getViewType() {
    return "SVG";
  }

  @Override
  public int getTempo() {
    return super.getTempo();
  }
}

