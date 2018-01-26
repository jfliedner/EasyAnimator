package cs3500.animator.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Represent a dialog for user to choose colors.
 */
public class ColorChooserDialog extends JPanel implements ChangeListener {
  private JColorChooser chooser;
  private Color color;

  /**
   * Constructor for color-choosing dialog.
   */
  public ColorChooserDialog() {
    super(new BorderLayout());
    color = Color.WHITE;
    chooser = new JColorChooser(color);
    chooser.getSelectionModel().addChangeListener(this);
    chooser.setBorder(BorderFactory.createTitledBorder("Choose Background Color"));
    add(chooser, BorderLayout.CENTER);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    color = chooser.getColor();
  }

}
