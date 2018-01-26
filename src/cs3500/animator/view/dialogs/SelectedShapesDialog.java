package cs3500.animator.view.dialogs;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Pop-up dialog for the user to select shapes.
 */
public class SelectedShapesDialog extends JPanel {
  private ArrayList<JCheckBox> boxes = new ArrayList<>();
  private int input = 1;

  /**
   * Constructor for SelectedShapesDialog.
   * @param shapes      The shapes to offer
   */
  public SelectedShapesDialog(ArrayList<String> shapes) {
    ArrayList<String> names;
    ArrayList<JCheckBox> boxes = new ArrayList<>();
    JFrame frame = new JFrame();
    JPanel listOfBoxes = new JPanel();
    names = shapes;
    for (String s : names) {
      JCheckBox box = new JCheckBox(s);
      boxes.add(box);
      listOfBoxes.add(box);
    }

    JScrollPane scrollPane = new JScrollPane(listOfBoxes);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(400, 100));
    Object[] obj = (Object[]) boxes.toArray(new Object[boxes.size()]);
    input = JOptionPane.showConfirmDialog(frame, scrollPane,
            "Check the boxes for the shapes you'd like to select",
            JOptionPane.OK_CANCEL_OPTION);
  }

  /**
   * Gets the shapes that have been selected.
   * @return      The selected shapes
   */
  public ArrayList<String> getSelected() {
    ArrayList<String> selected = new ArrayList<String>();
    if (input == 2) {
      for  (int i = 0; i < boxes.size(); i++) {
        selected.add(boxes.get(i).getText());
      }
    } else {
      for (int i = 0; i < boxes.size(); i++) {
        if (boxes.get(i).isSelected()) {
          selected.add(boxes.get(i).getText());
        }
      }
    }
    return selected;
  }
}
