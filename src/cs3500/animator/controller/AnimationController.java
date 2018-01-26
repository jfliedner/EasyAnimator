package cs3500.animator.controller;

import javax.swing.Timer;

import cs3500.animator.model.IAnimator;
import cs3500.animator.view.IView;

/**
 * The controller for the MODEL-VIEW-CONTROLLER.
 * Implements IController.
 */
public class AnimationController implements IController {
  private IAnimator model;
  private IView view;

  /**
   * Constructor for the controller which takes in a model and a view, sets the time to 0.
   * @param model         The model for the controller
   * @param view          The view for the controller
   */
  public AnimationController(IAnimator model, IView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void goController() {
    PlayAnimation play = new PlayAnimation(model, view);
    Timer timer = new Timer(100, play);
    view.makeVisible();
    timer.start();
  }

}
