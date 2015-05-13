package com.yalantis.guillotine.util;

import android.animation.TimeInterpolator;

/**
 * Created by Dmytro Denysenko on 5/7/15.
 */
public class GuillotineInterpolator implements TimeInterpolator {

    public GuillotineInterpolator() {
    }

    public float getInterpolation(float t) {
        if (t < 0.45f) return firstBounce(t);
        else if (t < 0.6f) return 1.0f;
        else if (t < 0.8f) return secondBounce(t);
        else return thirdBounce(t);
    }

    private float firstBounce(float t) {
        return 4.0f * t * t;
    }

    private float secondBounce(float t) {
        return 5f * t * t - 7f * t + 3.4f;
    }

    private float thirdBounce(float t) {
        return 5.0f * t * t - 9.0f * t + 5.0f;
    }
}
