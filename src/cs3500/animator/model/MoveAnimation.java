package cs3500.animator.model;

/**
 * Represents an animation in which a shape moves from Point A, B to Point X, Y.
 */
public class MoveAnimation extends AAnimation implements Animation {

  private double posnXFrom;
  private double posnYFrom;

  private double posnXTo;
  private double posnYTo;

  /**
   * Constructor.
   * @param toMutate                The shape to mutate.
   * @param posnXFrom               The position x you're moving from.
   * @param posnYFrom               The position y you're moving from.
   * @param posnXTo                 The position x you're moving to.
   * @param posnYTo                 The position y you're moving to.
   * @param start                   Start time.
   * @param end                     End time.
   */
  public MoveAnimation(Shape toMutate, double posnXFrom, double posnYFrom, double posnXTo,
                       double posnYTo, long start, long end) {
    super(toMutate, start, end);

    this.posnXFrom = posnXFrom;
    this.posnYFrom = posnYFrom;
    this.posnXTo = posnXTo;
    this.posnYTo = posnYTo;
  }

  @Override
  public void changeShape(AShape toModify, long time) {
    toModify.setPosnX(this.getValueToChangeShape(posnXFrom, posnXTo, time, startTime, endTime));
    toModify.setPosnY(this.getValueToChangeShape(posnYFrom, posnYTo, time, startTime, endTime));
  }

  @Override
  public String animationType() {
    return "Move";
  }

  /**
   * Gets the from PosnX.
   * @return      Returns posnXFrom
   */
  public double getPosnXFrom() {
    return posnXFrom;
  }

  /**
   * Gets the to PosnX.
   * @return      Returns posnXTo
   */
  public double getPosnXTo() {
    return posnXTo;
  }

  /**
   * Gets the from PosnY.
   * @return     Returns posnYFrom
   */
  public double getPosnYFrom() {
    return posnYFrom;
  }

  /**
   * Gets the to PosnY.
   * @return    Returns posnYTo
   */
  public double getPosnYTo() {
    return posnYTo;
  }
}
