package cs3500.animator.view.dialogs;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 * Pop-up dialog for allowing the user to export animation to SVG file.
 */
public class ExportSVGDialog extends JPanel {

  private String result = "";

  /**
   * Constructor the pop-up for ExportSVGDialog.
   */
  public ExportSVGDialog() {
    JFrame frame = new JFrame();
    frame.setPreferredSize(new Dimension(300, 200));
    result = (String)JOptionPane.showInputDialog(
            frame, "Enter in the desired name of the SVG file, not including .svg: \n",
            "Export to SVG",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            "Type here");
  }

  /**
   * The result from the user's input in the dialog.
   * @return      The result
   */
  public String getResult() {
    return result;
  }
}
