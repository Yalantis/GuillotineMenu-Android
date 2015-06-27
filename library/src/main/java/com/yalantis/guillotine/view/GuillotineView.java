package com.yalantis.guillotine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Debug;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.yalantis.guillotine.controller.SpringController;
import com.yalantis.guillotine.interfaces.GuillotineCallback;
import com.yalantis.guillotine.library.R;

public class GuillotineView extends FrameLayout {

  private View guillotineToolbar;

  private TypedArray attributes;
  private SpringController springController;

  public GuillotineView(Context context) {
    this(context, null);
  }

  public GuillotineView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public GuillotineView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializeAttributes(attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    if(!isInEditMode()) {
      initializeSpringController();
      mapGUI(attributes);
      attributes.recycle();
    }
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if(springController != null) {
      springController.onAttachedToWindow();
    }
  }

  @Override protected void onDetachedFromWindow() {
    if(springController != null) {
      springController.onDetachedFromWindow();
    }
    super.onDetachedFromWindow();
  }

  private void initializeAttributes(AttributeSet attrs) {
    TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.guillotine_layout);
    //this.dragLimit = attributes.getFloat(R.styleable.dragger_layout_drag_limit,
    //    DEFAULT_DRAG_LIMIT);
    //this.dragPosition = DraggerPosition.getDragPosition(
    //    attributes.getInt(R.styleable.dragger_layout_drag_position, DEFAULT_DRAG_POSITION));
    //this.tension = attributes.getInteger(R.styleable.dragger_layout_tension, DEFAULT_TENSION);
    //this.friction = attributes.getInteger(R.styleable.dragger_layout_friction, DEFAULT_FRICTION);
    this.attributes = attributes;
  }

  private void mapGUI(TypedArray attributes) {
    if (getChildCount() == 1) {
      int dragViewId = attributes.getResourceId(
          R.styleable.guillotine_layout_guillotine_view_id, 0);
      guillotineToolbar = findViewById(dragViewId);
      guillotineToolbar.post(new Runnable() {
        @Override public void run() {
          ViewCompat.setPivotX(guillotineToolbar, guillotineToolbar.getHeight()/2);
        }
      });
    } else {
      throw new IllegalStateException("DraggerView must contains only two direct child");
    }
  }

  private void initializeSpringController() {
    springController = new SpringController(guillotineCallback);
  }

  public void open() {
    springController.open();
  }

  public void close() {
    springController.close();
  }

  private GuillotineCallback guillotineCallback = new GuillotineCallback() {

    @Override public View getGuillotineView() {
      return guillotineToolbar;
    }

    @Override public View getHomeIcon() {
      return null;
    }
  };

}
