package com.color.picker;

import javax.swing.*;
import java.awt.*;

public class PreviewPane extends JPanel {
  private final JPanel selectedColor;
  private final JPanel currentColor;

  public PreviewPane() {
    selectedColor = new JPanel();
    selectedColor.setPreferredSize(new Dimension(75, 75));
    selectedColor.add(new JLabel("       "));
    currentColor = new JPanel();
    currentColor.setPreferredSize(new Dimension(75, 75));
    currentColor.add(new JLabel("         "));
    setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridy = 1;
    add(selectedColor, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridy = 1;
    add(currentColor, gridBagConstraints);
  }

  public void changeSelectedColor(Color color) {
    selectedColor.setBackground(color);
  }

  public void changeCurrentColor(Color color) {
    currentColor.setBackground(color);
  }
}
