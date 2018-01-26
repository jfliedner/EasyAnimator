package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.TweenModelBuilder;

/**
 * An AnimatorModel, which implements IAnimator. Operates on Shapes and Animations of the
 * abstract type aAnimation and aShape.
 */
public class AnimatorModel implements IAnimator {

  private ArrayList<Shape> shapes = new ArrayList<Shape>();
  private long curTime = 0;
  private ArrayList<Shape> originals = new ArrayList<Shape>();

  /**
   * Default constructor.
   */
  public AnimatorModel() {
    shapes = new ArrayList<Shape>();
  }

  /**
   * Constructor.
   * @param shapes        The shapes to animate.
   */
  public AnimatorModel(ArrayList<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Builder class for the model, implements TweenModelBuilder of type IAnimator.
   * Allows the TweenModelBuilder to call the model's methods without knowledge
   * of implementation details.
   */
  public static final class Builder implements TweenModelBuilder<IAnimator> {
    private ArrayList<Shape> shapes = new ArrayList<>();


    @Override
    public TweenModelBuilder<IAnimator> addOval(String name, float cx, float cy, float xRadius,
                                                float yRadius, float red, float green, float blue,
                                                int startOfLife, int endOfLife) {
      this.shapes.add(new Oval(xRadius, yRadius, new Color(red, green, blue), name,
              startOfLife, endOfLife, cx, cy, 0));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimator> addRectangle(String name, float lx, float ly, float width,
                                                     float height, float red, float green,
                                                     float blue, int startOfLife, int endOfLife) {
      this.shapes.add(new Rectangle(width, height, new Color(red, green, blue), name,
              startOfLife, endOfLife, lx, ly, 0));
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimator> addMove(String name, float moveFromX, float moveFromY,
                                                float moveToX, float moveToY, int startTime,
                                                int endTime) {
      for (Shape s: this.shapes) {
        if (s.getName().equals(name)) {
          s.addAnAnimation(new MoveAnimation(s, moveFromX, moveFromY, moveToX, moveToY,
                  startTime, endTime));
          break;
        }
      }
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimator> addColorChange(String name, float oldR, float oldG,
                                                       float oldB, float newR, float newG,
                                                       float newB, int startTime, int endTime) {
      for (Shape s: this.shapes) {
        if (s.getName().equals(name)) {
          s.addAnAnimation(new ChangeColorAnimation(s, oldR, oldG, oldB,
                  newR, newG, newB, startTime, endTime));
          break;
        }
      }
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimator> addScaleToChange(String name, float fromSx,
                                                         float fromSy, float toSx, float toSy,
                                                         int startTime, int endTime) {
      for (Shape s: this.shapes) {
        if (s.getName().equals(name)) {
          s.addAnAnimation(new ScaleAnimation(s, fromSx, fromSy, toSx, toSy,
                  startTime, endTime));
          break;
        }
      }
      return this;
    }

    @Override
    public IAnimator build() {
      return new AnimatorModel(this);
    }
  }

  /**
   * Constructor that takes in a Builder as a parameter and operates on its fields instead.
   * @param b         The Builder.
   */
  public AnimatorModel(Builder b) {
    this.shapes = b.shapes;
    for (Shape s : this.shapes) {
      this.originals.add(s.makeCopy());
    }
  }

  @Override
  public void incTempo(boolean temp) {
    if (temp) {
      this.curTime += 50;
    } else if (this.curTime > 50) {
      this.curTime -= 50;
    }
  }

  @Override
  public void setShapes(ArrayList<Shape> selected) {
    this.shapes = selected;
  }

  @Override
  public void setBackground(Color color) {
    boolean hasbg = false;
    if (this.shapes.get(0).getName().equals("background")) {
      hasbg = true;
    }
    if (hasbg) {
      Shape old = this.shapes.get(0);
      Rectangle background = new Rectangle(1000, 1000, color, "background",
              0, old.getTimeDisappear(), old.getPosnX(), old.getPosnY(), 0);
      this.shapes.set(0, background);
    } else {
      Rectangle background = new Rectangle(1000, 1000, color, "background",
              0, this.shapes.get(this.shapes.size() - 1).getTimeDisappear(),
              0, 0, 0);
      this.shapes.add(0, background);
    }
  }


  @Override
  public void animateShapes() {
    for (Shape shape : shapes) {
      shape.executeAnimations(curTime);
    }
  }


  @Override
  public void addShape(Shape toAdd) {
    shapes.add(toAdd);
  }

  @Override
  public void addTime(long toAdd) {
    curTime += toAdd;
  }

  @Override
  public ArrayList<Shape> getShapes() {
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    shapes.addAll(this.shapes);
    return shapes;
  }

  @Override
  public ArrayList<Shape> getOriginals() {
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    shapes.addAll(this.originals);
    return shapes;
  }

  @Override
  public void reset() {
    this.shapes = new ArrayList<Shape>();
    for (Shape s : this.originals) {
      this.shapes.add(s.makeCopy());
    }
  }


}
