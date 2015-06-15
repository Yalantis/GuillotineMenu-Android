package com.yalantis.guillotine.util;

import android.animation.TimeInterpolator;

/**
 * Created by Dmytro Denysenko on 5/15/15.
 */
public class ActionBarInterpolator implements TimeInterpolator {

    private static final float FIRST_BOUNCE_PART = 0.375f;
    private static final float SECOND_BOUNCE_PART = 0.625f;

    @Override
    public float getInterpolation(float t) {
        if (t < FIRST_BOUNCE_PART) {
            return (-1111f) * t * t + 1933f * t - 840f;
        } else if (t < SECOND_BOUNCE_PART) {
            return (833.3f) * t * t - 1533f * t + 705f;
        } else {
            return (-370.4f) * t * t + 718.5f * t - 348.1f;
        }
    }
}
