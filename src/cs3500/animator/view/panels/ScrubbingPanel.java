package cs3500.animator.view.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.controller.InteractiveController;

/**
 * Represent a dialog of a slider to adjust animation speed.
 */
public class ScrubbingPanel extends JPanel implements ActionListener, ChangeListener {

  int speed;
  InteractiveController control;

  public ScrubbingPanel(InteractiveController control) {
    super(new BorderLayout());
    this.control = control;
    speed = 5;
    JLabel sliderLabel = new JLabel("Speed", JLabel.CENTER);
    sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // the slider
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
    slider.setBorder(BorderFactory.createTitledBorder("Adjust speed of animation."));
    slider.addChangeListener(this);
    slider.setMajorTickSpacing(5);
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    add(sliderLabel);
    add(slider);
    setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    speed = source.getValue();
    control.setDelay(speed);
  }
}
