package com.yalantis.guillotine.controller;

import android.support.v4.view.ViewCompat;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.yalantis.guillotine.interfaces.GuillotineCallback;

public class SpringController {

  private static final int DEFAULT_TENSION = 100;
  private static final int DEFAULT_FRICTION = 30;

  private double tension = DEFAULT_TENSION;
  private double friction = DEFAULT_FRICTION;

  private static volatile Spring springRotate;

  private GuillotineCallback guillotineCallback;

  public SpringController(GuillotineCallback guillotineCallback) {
    this.guillotineCallback = guillotineCallback;
  }

  private Spring springRotate() {
    if(springRotate == null) {
      synchronized (Spring.class) {
        if(springRotate == null) {
          springRotate = SpringSystem
              .create()
              .createSpring()
              .setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        }
      }
    }
    return springRotate;
  }

  public void onDetachedFromWindow() {
    springRotate().removeAllListeners();
  }

  public void onAttachedToWindow() {
    springRotate().removeAllListeners().addListener(slideButtonSpringListener);
  }

  public void open() {
    springRotate().setEndValue(1);
  }

  public void close() {
    springRotate().setEndValue(0);
  }

  public double getTension() {
    return tension;
  }

  public void setTension(double tension) {
    this.tension = tension;
  }

  public double getFriction() {
    return friction;
  }

  public void setFriction(double friction) {
    this.friction = friction;
  }

  private SimpleSpringListener slideButtonSpringListener = new SimpleSpringListener() {
    @Override public void onSpringUpdate(Spring spring) {
      super.onSpringUpdate(spring);
      float rotateSpring = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0,
          1, 0, 90);
      ViewCompat.setRotation(guillotineCallback.getGuillotineView(), rotateSpring);
    }
  };


}
