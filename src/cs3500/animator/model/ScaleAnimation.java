package cs3500.animator.model;

/**
 * An animation the scales the shape's width and height.
 */
public class ScaleAnimation extends AAnimation implements Animation {

  private double fromWidth;
  private double fromHeight;

  private double changeWidth;
  private double changeHeight;

  /**
   * Constructor.
   * @param toMutate            The shape to mutate
   * @param fromWidth           The width from
   * @param fromHeight          The height from
   * @param changeWidth         The width scaling to
   * @param changeHeight        The height scaling to
   * @param start               The start time
   * @param end                 The end time
   */
  public ScaleAnimation(Shape toMutate, double fromWidth, double fromHeight, double changeWidth,
                        double changeHeight, long start, long end) {
    super(toMutate, start, end);
    this.fromWidth = fromWidth;
    this.fromHeight = fromHeight;
    this.changeWidth = changeWidth;
    this.changeHeight = changeHeight;
  }

  @Override
  public void changeShape(AShape toModify, long time) {
    toModify.setWidth(this.getValueToChangeShape(fromWidth, changeWidth, time, startTime, endTime));
    toModify.setHeight(
            this.getValueToChangeShape(fromHeight, changeHeight, time, startTime, endTime));
  }

  @Override
  public String animationType() {
    return "Scale";
  }

  /**
   * Gets the from width.
   * @return        The fromWidth
   */
  public double getFromWidth() {
    return fromWidth;
  }

  /**
   * Gets the from height.
   * @return        The fromHeight
   */
  public double getFromHeight() {
    return fromHeight;
  }

  /**
   * Gets the width to change to.
   * @return        The changeWidth
   */
  public double getChangeWidth() {
    return changeWidth;
  }

  /**
   * Gets the height to change to.
   * @return        The changeHeight
   */
  public double getChangeHeight() {
    return changeHeight;
  }
}
