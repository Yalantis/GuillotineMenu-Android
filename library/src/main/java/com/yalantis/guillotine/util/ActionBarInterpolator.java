package com.yalantis.guillotine.util;

import android.animation.TimeInterpolator;

/**
 * Created by Dmytro Denysenko on 5/15/15.
 */
public class ActionBarInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float t) {
        if (t < 0.5) {
            return -16.0f * t * t + 8.0f * t;
        } else {
            return -16.0f * t * t + 24.0f * t - 8;
        }
    }
}
