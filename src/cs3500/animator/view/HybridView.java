package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import cs3500.animator.model.Shape;
import cs3500.animator.view.panels.AnimationPanel;
import cs3500.animator.view.panels.LegendPanel;
import cs3500.animator.view.panels.LogPanel;
import cs3500.animator.view.panels.ScrubbingPanel;

/**
 * A HybridView, which allows user interactions and includes visual cues, along with exporting
 * as an SVG file.
 */
public class HybridView extends AbstractSVG implements IView {

  private AnimationPanel animationPanel;
  private LogPanel logPanel;

  /**
   * Constructor for the hybrid view.
   * @param shapes      The shapes to include in the view
   */
  public HybridView(ArrayList<Shape> shapes) {
    super(shapes, 1);
    Collections.sort(shapes);
    this.setTitle("Easy Animator!");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.white);
    animationPanel = new AnimationPanel(this.shapes);
    animationPanel.setPreferredSize(new Dimension(1000, 1000));
    LegendPanel legendPanel = new LegendPanel();
    legendPanel.setPreferredSize(new Dimension(500, 500));
    logPanel = new LogPanel();
    logPanel.setPreferredSize(new Dimension(500, 500));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    layout.setVerticalGroup(
            layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(animationPanel)
                            .addComponent(legendPanel))
                    .addComponent(logPanel));

    layout.setHorizontalGroup(
            layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(animationPanel)
                                    .addComponent(legendPanel))
                            .addComponent(logPanel)));


    this.setLayout(layout);
    this.pack();
  }

  @Override
  public String getOutput() {
    return super.getOutput();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setShapes(ArrayList<Shape> shapes) {
    this.shapes = shapes;
    this.animationPanel.setShapes(shapes);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,
            "Error",JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public String getViewType() {
    return "Hybrid";
  }

  @Override
  public int getTempo() {
    int temp = this.tempo;
    return temp;
  }

  /**
   * Adds the command to the log panel's log.
   * @param command   The command to add
   */
  public void addToLog(String command) {
    logPanel.addToLog(command);
  }

  /**
   * Overlay a slider on animation panel.
   */
  public void addSlider(ScrubbingPanel panel) {
    this.animationPanel.add(panel);
  }

}
