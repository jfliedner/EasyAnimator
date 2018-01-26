package cs3500.animator.controller;

import javax.swing.Timer;

import cs3500.animator.model.IAnimator;
import cs3500.animator.model.Shape;
import cs3500.animator.view.HybridView;
import cs3500.animator.view.panels.ScrubbingPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * An interactive controller which allows the user to use various actions, allowed for HybridView.
 */
public class InteractiveController implements IController, ActionListener, IInteractiveController {

  private IAnimator model;
  private HybridView view;
  private int time;
  private Timer timer;
  private ArrayList<Shape> shapesToAnimate;
  private boolean loop = false;
  private KeyboardListener listen;
  private boolean reset = false;
  private ArrayList<String> log;

  /**
   * Constructor the the interactive controller.
   * @param model       The model to use
   * @param view        The HybridView to use
   */
  public InteractiveController(IAnimator model, HybridView view) {
    this.model = model;
    this.view = view;
    this.view.addSlider(new ScrubbingPanel(this));
    this.time = 0;
    this.shapesToAnimate = new ArrayList<Shape>();
    timer = new Timer(50, this);
    listen = new KeyboardListener(this.timer, this);
    log = new ArrayList<String>();
  }

  @Override
  public boolean toLoop() {
    boolean b = this.loop;
    return b;
  }

  @Override
  public void setLoop(boolean toLoop) {
    this.loop = toLoop;
  }

  @Override
  public void incTempo() {
    if (timer.getDelay() >= 50) {
      timer.setDelay(timer.getDelay() - 25);
    }
  }

  @Override
  public int getDelay() {
    return timer.getDelay();
  }

  @Override
  public void decTempo() {
    timer.setDelay(timer.getDelay() + 25);
  }

  @Override
  public void reset() {
    timer.stop();
    if (!loop) {
      model.reset();
    }
    this.view.setShapes(model.getShapes());
    shapesToAnimate = model.getShapes();
    this.view.refresh();
    this.time = 0;
    timer.start();
  }

  @Override
  public void start() {
    timer.stop();
    timer.setDelay(50);
    if (!reset) {
      model.reset();
    }
    this.view.setShapes(model.getShapes());
    shapesToAnimate = model.getShapes();
    this.view.refresh();
    this.time = 0;
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (shapesToAnimate.isEmpty() && loop) {
      this.reset();
    }
    time += 1;
    shapesToAnimate.clear();
    for (int i = 0; i < model.getShapes().size(); i += 1) {
      Shape s = model.getShapes().get(i);
      if (s.getTimeAppear() <= time && s.getTimeDisappear() >= time) {
        shapesToAnimate.add(s);
      }
    }
    view.setShapes(shapesToAnimate); // passing them to the view
    for (Shape s: shapesToAnimate) {
      s.executeAnimations(time); // executing their animations
    }
    view.refresh();
  }

  @Override
  public void goController() {
    this.view.addKeyListener(listen);
    this.view.setFocusable(true);
    this.view.makeVisible();
  }

  @Override
  public void draw() {
    view.refresh();
  }

  @Override
  public void addToLog(String command) {
    view.addToLog(command);
    this.log.add(command);
  }

  @Override
  public ArrayList<String> getShapeNames() {
    ArrayList<String> names = new ArrayList<String>();
    for (Shape s : model.getOriginals()) {
      names.add(s.getName());
    }
    return names;
  }

  @Override
  public void resetShapes(ArrayList<String> selectedShapes) {
    reset = true;
    ArrayList<Shape> selected = new ArrayList<Shape>();
    for (int index = 0; index < selectedShapes.size(); index += 1) {
      for (int idx = 0; idx < model.getOriginals().size(); idx += 1) {
        Shape s = model.getOriginals().get(idx);
        if (s.getName().equals(selectedShapes.get(index))) {
          selected.add(s.makeCopy());
        }
      }
    }
    model.setShapes(selected);
    view.setShapes(selected);
  }

  @Override
  public void createSVG(String filename) {
    byte[] data = view.getOutput().getBytes();
    Path p = Paths.get("./" + filename + ".svg");
    try (OutputStream out = new BufferedOutputStream(
            Files.newOutputStream(p, CREATE, APPEND))) {
      out.write(data, 0, data.length);
    } catch (IOException e) {
      // IO exception!
    }
  }

  @Override
  public ArrayList<String> getLog() {
    ArrayList<String> result = new ArrayList<String>();
    for (int index = 0; index < log.size(); index += 1) {
      result.add(log.get(index));
    }
    return result;
  }

  @Override
  public void setBackground(Color color) {
    model.setBackground(color);
    view.setShapes(model.getShapes());
  }

  @Override
  public void setDelay(int speed) {
    switch (speed) {
      case 0:
        this.timer.setDelay(100);
        break;
      case 1:
        this.timer.setDelay(90);
        break;
      case 2:
        this.timer.setDelay(80);
        break;
      case 3:
        this.timer.setDelay(70);
        break;
      case 4:
        this.timer.setDelay(60);
        break;
      case 5:
        this.timer.setDelay(50);
        break;
      case 6:
        this.timer.setDelay(40);
        break;
      case 7:
        this.timer.setDelay(30);
        break;
      case 8:
        this.timer.setDelay(20);
        break;
      case 9:
        this.timer.setDelay(10);
        break;
      case 10:
        this.timer.setDelay(1);
        break;
      default:
        this.timer.setDelay(50);
    }
  }

}
