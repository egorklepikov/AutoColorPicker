package com.color.picker;


import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
  private final JColorChooser colorPicker;
  private final PreviewPane previewPane;
  private Robot robot;

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
    try {
      GlobalScreen.registerNativeHook();
    } catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
      System.err.println(ex.getMessage());
      System.exit(1);
    }
    GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    add(colorPicker);
    setVisible(true);
  }

  public static void main(String[] args) {
    new MainFrame();
  }

  private class MouseMotionListener extends Thread {
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

      }
    }
  }

  private class GlobalKeyListener implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
      if (e.getKeyCode() == NativeKeyEvent.VC_TAB) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        previewPane.changeSelectedColor(robot.getPixelColor((int) point.getX(), (int) point.getY()));
      }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }
  }
}
