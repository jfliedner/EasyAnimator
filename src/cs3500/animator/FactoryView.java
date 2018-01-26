package cs3500.animator;

import java.util.ArrayList;

import cs3500.animator.model.Shape;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;

/**
 * Factory class for the Views.
 */
public class FactoryView {

  /**
   * Factory method for creating a view based on a String.
   * @param view        The desired view as a string.
   * @return            The respective view.
   */
  public static IView factory(String view) {
    switch (view) {
      case "text":
        return new TextView();
      case "visual":
        return new VisualView(new ArrayList<Shape>());
      case "interactive":
        return new HybridView(new ArrayList<Shape>());
      case "svg":
        return new SVGView();
      default: throw new IllegalArgumentException("Invalid string");
    }
  }
}
