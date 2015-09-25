package com.yalantis.guillotine.sample.activity;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.yalantis.guillotine.sample.R;
import com.yalantis.guillotine.view.GuillotineView;

public class BaseActivity extends AbstractActivity {

  @InjectView(R.id.guillotine_view) GuillotineView guillotineView;
  @InjectView(R.id.content) TextView content;

  @OnClick(R.id.menu_icon) void onMenuIconClick() {
    if(guillotineView.isOpened()) {
      guillotineView.close();
    } else {
      guillotineView.open();
    }
  }

  @Override protected int getContentViewId() {
    return R.layout.activity_base;
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    guillotineView.setContent(content);
  }

  @Override public void onBackPressed() {
    if(guillotineView.isOpened()) {
      guillotineView.close();
    } else {
      guillotineView.open();
    }
  }
}
