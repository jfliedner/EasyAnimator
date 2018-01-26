package cs3500.animator.model;

import java.awt.Color;

/**
 * An animation that changes color using RGB.
 */
public class ChangeColorAnimation extends AAnimation implements Animation {
  private float rFrom;
  private float gFrom;
  private float bFrom;

  private float rTo;
  private float gTo;
  private float bTo;

  /**
   * Constructor.
   * @param toMutate          The shape to mutate.
   * @param rFrom             The red from
   * @param gFrom             The green from
   * @param bFrom             The blue from
   * @param rTo               The red to
   * @param gTo               The green to
   * @param bTo               The blue to
   * @param startTime         The start time
   * @param endTime           The end time
   */
  public ChangeColorAnimation(Shape toMutate, float rFrom, float gFrom, float bFrom,
                              float rTo, float gTo, float bTo, long startTime, long endTime) {

    super(toMutate, startTime, endTime);

    if (rFrom < 0.0 || rFrom > 255.0) {
      throw new IllegalArgumentException("Invalid red start field");
    }

    if (gFrom < 0.0 || gFrom > 255.0) {
      throw new IllegalArgumentException("Invalid green start field");
    }

    if (bFrom < 0.0 || bFrom > 255.0) {
      throw new IllegalArgumentException("Invalid blue start field");
    }

    if (rTo < 0.0 || rTo > 255.0) {
      throw new IllegalArgumentException("Invalid red to field");
    }

    if (gTo < 0.0 || gTo > 255.0) {
      throw new IllegalArgumentException("Invalid green to field");
    }

    if (bTo < 0.0 || bTo > 255.0) {
      throw new IllegalArgumentException("Invalid blue to field");
    }

    this.rFrom = rFrom;
    this.gFrom = gFrom;
    this.bFrom = bFrom;

    this.rTo = rTo;
    this.gTo = gTo;
    this.bTo = bTo;
  }

  /**
   * Gets the from Red value.
   * @return      Returns rFrom
   */
  public float getRFrom() {
    return rFrom;
  }

  /**
   * Gets the from Green value.
   * @return      Returns gFrom
   */
  public float getGFrom() {
    return gFrom;
  }

  /**
   * Gets the from Blue value.
   * @return      Returns bFrom
   */
  public float getBFrom() {
    return bFrom;
  }

  /**
   * Gets the to Red value.
   * @return      Returns rTo
   */
  public float getRTo() {
    return rTo;
  }

  /**
   * Gets the to Green value.
   * @return      Returns gTo
   */
  public float getGTo() {
    return gTo;
  }

  /**
   * Gets the to Blue value.
   * @return      Returns bTo
   */
  public float getBTo() {
    return bTo;
  }

  @Override
  public void changeShape(AShape toModify, long time) {
    float changeR = (float)getValueToChangeShape(rFrom, rTo, time, startTime, endTime);
    float changeG = (float)getValueToChangeShape(gFrom, gTo, time, startTime, endTime);
    float changeB = (float)getValueToChangeShape(bFrom, bTo, time, startTime, endTime);
    toModify.setColor(new Color(changeR, changeG, changeB));
  }

  @Override
  public String animationType() {
    return "Color Change";
  }
}
