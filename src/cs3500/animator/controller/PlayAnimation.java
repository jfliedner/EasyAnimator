package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IView;

/**
 * A class that facilitates playing animations using ActionListener.
 */
public class PlayAnimation implements ActionListener {
  private IAnimator model;
  private IView view;
  private int time;

  /**
   * Constructor for PlayAnimation.
   * @param model         The model to use
   * @param view          The view to use
   */
  public PlayAnimation(IAnimator model, IView view) {
    this.model = model;
    this.view = view;
    this.time = 0;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (view.getViewType().equals("Visual")) {
      time += 1; // time has to be incremented
    }
    ArrayList<Shape> shapes = new ArrayList<Shape>(); // shapes to animate in the view
    for (int i = 0; i < model.getShapes().size(); i += 1) {
      Shape s = model.getShapes().get(i);
      if (s.getTimeAppear() <= time && s.getTimeDisappear() >= time) {
        shapes.add(s);
      }
    }
    view.setShapes(shapes); // passing them to the view
    for (Shape s: shapes) {
      s.executeAnimations(time); // executing their animations
    }
    view.refresh();
  }
}
