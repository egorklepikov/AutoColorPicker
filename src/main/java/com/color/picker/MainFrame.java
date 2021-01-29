package com.color.picker;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  private final JColorChooser colorPicker;
  private final PreviewPane previewPane;

  public MainFrame() {
    setSize(600, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
    previewPane = new PreviewPane();
    colorPicker = new JColorChooser();
    colorPicker.setPreviewPanel(previewPane);
    colorPicker.setBorder(null);
    colorPicker.setSize(600, 450);
    colorPicker.setVisible(true);
    new MouseMotionListener().start();
    add(colorPicker);
    setVisible(true);
  }

  public static void main(String[] args) {
    new MainFrame();
  }

  private class MouseMotionListener extends Thread {
    private Robot robot;

    public MouseMotionListener() {
      try {
        robot = new Robot();
      } catch (AWTException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        Point point = MouseInfo.getPointerInfo().getLocation();
        Color pixelColor = robot.getPixelColor((int) point.getX(), (int) point.getY());
        colorPicker.setColor(pixelColor);
        previewPane.changeCurrentColor(pixelColor);
        previewPane.changeSelectedColor(pixelColor);
      }
    }
  }
}
