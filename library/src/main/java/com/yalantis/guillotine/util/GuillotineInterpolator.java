package com.yalantis.guillotine.util;

import android.animation.TimeInterpolator;

/**
 * Created by Dmytro Denysenko on 5/7/15.
 */
public class GuillotineInterpolator implements TimeInterpolator {

    public static final float ROTATION_TIME = 0.84f;
    public static final float FIRST_BOUNCE_TIME = 0.08f;
    public static final float SECOND_BOUNCE_TIME = 0.08f;


    public GuillotineInterpolator() {
    }

    public float getInterpolation(float t) {
        if (t < ROTATION_TIME) return rotation(t);
        else if (t < ROTATION_TIME + FIRST_BOUNCE_TIME) return 1.0f;
        else if (t < ROTATION_TIME + FIRST_BOUNCE_TIME + SECOND_BOUNCE_TIME) return firstBounce(t);
        else return secondBounce(t);
    }

    private float rotation(float t) {
        return 1.417f * t * t;
    }

    private float firstBounce(float t) {
        return 625f * t * t - 1100f * t + 484f;
    }

    private float secondBounce(float t) {
        return 625f * t * t - 1200f * t + 576f;
    }
}
