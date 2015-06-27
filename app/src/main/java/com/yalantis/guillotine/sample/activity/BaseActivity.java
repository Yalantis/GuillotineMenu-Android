package com.yalantis.guillotine.sample.activity;

import android.view.View;
import butterknife.OnClick;
import com.yalantis.guillotine.sample.R;
import com.yalantis.guillotine.view.GuillotineView;

public class BaseActivity extends AbstractActivity {

  @OnClick(R.id.guillotine_view) void OnGuillotineViewClick(View view) {
    if(view instanceof GuillotineView) {
      ((GuillotineView) view).open();
    }
  }

  @Override protected int getContentViewId() {
    return R.layout.activity_base;
  }

}
