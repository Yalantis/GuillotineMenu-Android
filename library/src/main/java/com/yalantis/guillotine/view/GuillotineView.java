package com.yalantis.guillotine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.yalantis.guillotine.controller.SpringController;
import com.yalantis.guillotine.interfaces.GuillotineCallback;
import com.yalantis.guillotine.library.R;

public class GuillotineView extends FrameLayout {

  private View guillotineToolbar;
  private View content;

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
    initializeSpringController();
    initializeAttributes(attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    if(!isInEditMode()) {
      mapGUI(attributes);
      attributes.recycle();
    }
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    ViewGroup.LayoutParams layoutParams = guillotineToolbar.getLayoutParams();
    int position = layoutParams.height / 2;

    ViewCompat.setPivotX(guillotineToolbar, position);
    ViewCompat.setPivotY(guillotineToolbar, position);

    guillotineToolbar.setPivotX(position);
    guillotineToolbar.setPivotY(position);

    //ViewCompat.setPivotX(guillotineToolbar, 100);
    //ViewCompat.setY(guillotineToolbar, -MeasureSpec.getSize(widthMeasureSpec) + height);
    layoutParams.width = MeasureSpec.getSize(heightMeasureSpec);
    //layoutParams.height = MeasureSpec.getSize(widthMeasureSpec);
    guillotineToolbar.setLayoutParams(layoutParams);

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
    springController.setTension(attributes.getInteger(
        R.styleable.guillotine_layout_guillotine_tension, SpringController.DEFAULT_TENSION));
    springController.setFriction(attributes.getInteger(
        R.styleable.guillotine_layout_guillotine_friction, SpringController.DEFAULT_TENSION));
    this.attributes = attributes;
  }

  private void mapGUI(TypedArray attributes) {
    if (getChildCount() == 1) {
      int dragViewId = attributes.getResourceId(R.styleable.guillotine_layout_guillotine_view_id, 0);
      guillotineToolbar = findViewById(dragViewId);
    } else {
      throw new IllegalStateException("DraggerView must contains only two direct child");
    }
  }

  private void initializeSpringController() {
    springController = new SpringController(guillotineCallback);
  }

  public void setContent(View content) {
    this.content = content;
  }

  public void open() {
    springController.open();
  }

  public void close() {
    springController.close();
  }

  public boolean isOpened() {
    return springController.isOpened();
  }

  private GuillotineCallback guillotineCallback = new GuillotineCallback() {
    @Override public View getGuillotineView() {
      return guillotineToolbar;
    }
    @Override public View getHomeIcon() {
      return null;
    }
    @Override public View getContent() {
      return content;
    }
  };

}
