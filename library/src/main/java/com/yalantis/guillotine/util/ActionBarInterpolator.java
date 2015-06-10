package com.yalantis.guillotine.util;

import android.animation.TimeInterpolator;

/**
 * Created by Dmytro Denysenko on 5/15/15.
 */
public class ActionBarInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float t) {
        if (t < 0.2727272) {
            return 26.89f * t * t - 7.333f * t;
        } else if (t < 0.63636363) {
            return (-30.25f) * t * t + 27.5f * t - 5.25f;
        } else {
            return (-24.20f) * t * t + 39.60f * t - 15.4f;
        }
    }
}
