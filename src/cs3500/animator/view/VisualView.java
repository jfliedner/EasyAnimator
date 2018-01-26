package cs3500.animator.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import cs3500.animator.model.Shape;
import cs3500.animator.view.panels.AnimationPanel;

/**
 * The view implementation which produces a visual view of the animation using Swing.
 * Extends JFrame and implements IView.
 */
public class VisualView extends JFrame implements IView {
  private AnimationPanel animationPanel;
  private ArrayList<Shape> shapes;
  private int tempo;

  /**
   * Constructor that takes in ArrayList of Shapes.
   * @param shapes      Shapes to animate.
   */
  public VisualView(ArrayList<Shape> shapes) {
    super();
    Collections.sort(shapes);
    this.shapes = shapes;
    this.tempo = 1;
    this.setTitle("Easy Animator!");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setBackground(Color.white);
    this.setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    animationPanel = new AnimationPanel(this.shapes);
    animationPanel.setPreferredSize(new Dimension(1000, 1000));
    this.add(animationPanel, BorderLayout.CENTER);

    this.pack();
  }

  /**
   * Gets the tempo field.
   * @return      Returns the tempo
   */
  public int getTempo() {
    int temp = this.tempo;
    return temp;
  }


  @Override
  public String getOutput() {
    throw new UnsupportedOperationException("No text output in visual view.");
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
    return "Visual";
  }
}
