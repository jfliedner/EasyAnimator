package cs3500.animator.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.util.ArrayList;

/**
 * A panel for our visual log, which catalogs actions from the user.
 */
public class LogPanel extends JPanel {

  private ArrayList<String> log = new ArrayList<String>();
  private JEditorPane textPane = new JEditorPane();

  /**
   * Constructor for the panel.
   */
  public LogPanel() {
    textPane.setEditable(false);
    JScrollPane paneScrollPane = new JScrollPane(textPane);
    paneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    paneScrollPane.setPreferredSize(new Dimension(1500, 1000));
    textPane.add(paneScrollPane.getVerticalScrollBar());
    paneScrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Action Log"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    this.setBackground(Color.BLACK);
  }

  /**
   * Add a command to the log.
   * @param command   The command to add
   */
  public void addToLog(String command) {
    if (log.size() > 6) {
      log.remove(0);
    }
    log.add(command);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.GREEN);
    g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    for (int index = 0; index < log.size(); index += 1) {
      g2d.drawString(log.get(index), 20, 30 + 30 * index);
    }
  }
}
