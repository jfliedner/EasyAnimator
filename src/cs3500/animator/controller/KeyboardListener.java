package cs3500.animator.controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import cs3500.animator.view.dialogs.ColorChooserDialog;
import cs3500.animator.view.dialogs.ExportSVGDialog;
import cs3500.animator.view.dialogs.SelectedShapesDialog;

/**
 * A custom KeyboardListener.
 */
public class KeyboardListener implements KeyListener {
  private Timer timer;
  private InteractiveController control;

  /**
   * Constructor for the KeyboardListener.
   * @param timer         The Timer to use
   * @param control       The controller to use
   */
  public KeyboardListener(Timer timer, InteractiveController control) {
    this.timer = timer;
    this.control = control;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // empty body
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // empty body
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_S: // start
        control.addToLog("Animation has started.");
        control.draw();
        control.start();
        break;
      case KeyEvent.VK_P: // pause
        control.addToLog("Animation is paused.");
        control.draw();
        if (timer.isRunning()) {
          timer.stop();
        }
        break;
      case KeyEvent.VK_C: // resume
        if (!timer.isRunning()) {
          control.addToLog("Animation is resumed.");
          control.draw();
          timer.start();
        }
        break;
      case KeyEvent.VK_R: // restart
        control.addToLog("Animation has restarted.");
        control.draw();
        control.start();
        break;
      case KeyEvent.VK_L: // enable looping
        control.addToLog("Looping has been enabled.");
        control.draw();
        control.setLoop(true);
        break;
      case KeyEvent.VK_K: // disable looping
        control.addToLog("Looping has been disabled.");
        control.draw();
        control.setLoop(false);
        break;
      case KeyEvent.VK_UP: // speed up
        control.addToLog("Animation has been sped up.");
        control.draw();
        control.incTempo();
        break;
      case KeyEvent.VK_DOWN: // slow down
        control.addToLog("Animation has been slowed down.");
        control.draw();
        control.decTempo();
        break;
      case KeyEvent.VK_E: // export to SVG
        control.addToLog("Export to SVG.");
        control.draw();
        timer.stop();
        ExportSVGDialog svgDialog = new ExportSVGDialog();
        control.createSVG(svgDialog.getResult());
        timer.start();
        break;
      case KeyEvent.VK_A: // choose subset of shapes
        control.addToLog("Choose subset of shapes to display.");
        control.draw();
        timer.stop();
        SelectedShapesDialog select = new SelectedShapesDialog(control.getShapeNames());
        control.resetShapes(select.getSelected());
        control.draw();
        String selectedShapes = "";
        for (String s: select.getSelected()) {
          selectedShapes = selectedShapes + " " + s;
        }
        control.addToLog("These shapes were selected: " + selectedShapes);
        control.addToLog("To change this selection,"
                + " recall Select Shapes by pressing 'a'");
        break;
      case KeyEvent.VK_B: // choose color for background
        timer.stop();
        ColorChooserDialog choose = new ColorChooserDialog();
        Color newColor = JColorChooser.showDialog(choose,
                "Choose Background Color", Color.WHITE);
        if (newColor != null) {
          control.setBackground(newColor);
          control.addToLog("Chose new background color.");
        }
        timer.start();
        break;
      default:
        break;
    }
  }
}
