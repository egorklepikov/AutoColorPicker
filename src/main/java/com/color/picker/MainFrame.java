package com.color.picker;

import com.bric.colorpicker.ColorPicker;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  private final ColorPicker colorPicker;

  public MainFrame() {
    setSize(600, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
    colorPicker = new ColorPicker(true, true);
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
        colorPicker.setColor(robot.getPixelColor((int) point.getX(), (int) point.getY()));
      }
    }
  }
}

/**
 * public class ColorPickerExample {
 *
 *     public static void main(String[] args) throws InvocationTargetException, InterruptedException {
 *         EventQueue.invokeAndWait(() -> {
 *             JFrame frame = new JFrame("ColorPicker Example");
 *             frame.setSize(600, 400);
 *             frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 *
 *             ColorPicker colorPicker = new ColorPicker(true, true);
 *             colorPicker.setColor(Color.BLUE);
 *             colorPicker.addColorListener(colorModel -> System.out.println(colorModel.getColor()));
 *             frame.add(colorPicker);
 *
 *             frame.setVisible(true);
 *         });
 *     }
 *
 * }
 */
