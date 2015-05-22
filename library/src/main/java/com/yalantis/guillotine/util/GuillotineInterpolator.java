package com.yalantis.guillotine.util;

import android.animation.TimeInterpolator;

/**
 * Created by Dmytro Denysenko on 5/7/15.
 */
public class GuillotineInterpolator implements TimeInterpolator {

    public static final float ROTATION_TIME = 0.45f;
    public static final float PAUSE_TIME = 0.15f;
    public static final float FIRST_BOUNCE_TIME = 0.2f;
    public static final float SECOND_BOUNCE_TIME = 0.2f;


    public GuillotineInterpolator() {
    }

    public float getInterpolation(float t) {
        if (t < ROTATION_TIME) return rotation(t);
        else if (t < ROTATION_TIME + PAUSE_TIME) return 1.0f;
        else if (t < ROTATION_TIME + PAUSE_TIME + FIRST_BOUNCE_TIME) return firstBounce(t);
        else return secondBounce(t);
    }

    private float rotation(float t) {
        return 4.0f * t * t;
    }

    private float firstBounce(float t) {
        return 5.0f * t * t - 7.0f * t + 3.4f;
    }

    private float secondBounce(float t) {
        return 5.0f * t * t - 9.0f * t + 5.0f;
    }
}
