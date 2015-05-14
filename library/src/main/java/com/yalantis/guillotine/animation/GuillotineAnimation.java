package com.yalantis.guillotine.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;

import com.yalantis.guillotine.interfaces.GuillotineListener;
import com.yalantis.guillotine.util.GuillotineInterpolator;

/**
 * Created by Dmytro Denysenko on 5/6/15.
 */
public class GuillotineAnimation {
    private static final String ROTATION = "rotation";
    private static final float CLOSED_VALUE = -90f;
    private static final float OPENED_VALUE = 0f;
    private static final int DEFAULT_DURATION = 700;

    private final View mGuillotineView;
    private final int mDuration;
    private final ObjectAnimator mOpeningAnimation;
    private final ObjectAnimator mClosingAnimation;
    private final GuillotineListener mListener;
    private final boolean mIsRightToLeftLayout;
    private final TimeInterpolator mInterpolator;
    private final View mActionBarView;

    private boolean isOpening;
    private boolean isClosing;
    private long mDelay;

    private GuillotineAnimation(GuillotineBuilder builder) {
        this.mActionBarView = builder.actionBarView;
        this.mListener = builder.guillotineListener;
        this.mGuillotineView = builder.guillotineView;
        this.mDuration = builder.duration > 0 ? builder.duration : DEFAULT_DURATION;
        this.mDelay = builder.startDelay;
        this.mIsRightToLeftLayout = builder.isRightToLeftLayout;
        this.mInterpolator = builder.interpolator == null ? new GuillotineInterpolator() : builder.interpolator;
        setUpOpeningView(builder.openingView);
        setUpClosingView(builder.closingView);
        setUpActionBar(builder.openingView);
        this.mOpeningAnimation = buildOpeningAnimation();
        this.mClosingAnimation = buildClosingAnimation();
        //TODO handle right-to-left layouts
        //TODO handle landscape orientation
    }

    private void setUpActionBar(final View openingView) {
        mActionBarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mActionBarView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mActionBarView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                mActionBarView.setPivotX(calculatePivotX(openingView));
                mActionBarView.setPivotY(calculatePivotY(openingView));
            }
        });
    }

    private void close() {
        if (!isClosing) {
            mClosingAnimation.start();
        }

    }

    private void open() {
        if (!isOpening) {
            mOpeningAnimation.start();
        }
    }

    private void setUpOpeningView(final View openingView) {
        openingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });
    }

    private void setUpClosingView(final View closingView) {
        mGuillotineView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mGuillotineView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mGuillotineView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                mGuillotineView.setPivotX(calculatePivotX(closingView));
                mGuillotineView.setPivotY(calculatePivotY(closingView));
            }
        });

        closingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    private ObjectAnimator buildOpeningAnimation() {
        ObjectAnimator rotationAnimator = initAnimator(ObjectAnimator.ofFloat(mGuillotineView, ROTATION, CLOSED_VALUE, OPENED_VALUE));
        rotationAnimator.setInterpolator(mInterpolator);
        rotationAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mGuillotineView.setVisibility(View.VISIBLE);
                isOpening = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isOpening = false;
                if (mListener != null) {
                    mListener.onGuillotineOpened();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return rotationAnimator;
    }

    private ObjectAnimator buildClosingAnimation() {
        ObjectAnimator rotationAnimator = initAnimator(ObjectAnimator.ofFloat(mGuillotineView, ROTATION, OPENED_VALUE, CLOSED_VALUE));
        rotationAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isClosing = true;
                mGuillotineView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isClosing = false;
                mGuillotineView.setVisibility(View.GONE);
                startActionBarAnimation();

                if (mListener != null) {
                    mListener.onGuillotineClosed();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return rotationAnimator;
    }

    private void startActionBarAnimation() {
        ObjectAnimator.ofFloat(mActionBarView, ROTATION, OPENED_VALUE, 15f).start();
    }

    private ObjectAnimator initAnimator(ObjectAnimator animator) {
        animator.setDuration(mDuration);
        animator.setStartDelay(mDelay);
        return animator;
    }

    private float calculatePivotY(View burger) {
        return burger.getTop() + burger.getHeight() / 2;
    }

    private float calculatePivotX(View burger) {
        return burger.getLeft() + burger.getWidth() / 2;
    }

    public static class GuillotineBuilder {
        private final View guillotineView;
        private final View openingView;
        private final View closingView;
        private final View actionBarView;
        private GuillotineListener guillotineListener;
        private int duration;
        private long startDelay;
        private boolean isRightToLeftLayout;
        public TimeInterpolator interpolator;

        public GuillotineBuilder(@NonNull View guillotineView, @NonNull View openingView, @NonNull View closingView, View actionBarView) {
            this.guillotineView = guillotineView;
            this.openingView = openingView;
            this.closingView = closingView;
            this.actionBarView = actionBarView;
        }

        public GuillotineBuilder setGuillotineListener(GuillotineListener guillotineListener) {
            this.guillotineListener = guillotineListener;
            return this;
        }

        public GuillotineBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public GuillotineBuilder setStartDelay(long startDelay) {
            this.startDelay = startDelay;
            return this;
        }

        public GuillotineBuilder setRightToLeftLayout(boolean isRightToLeftLayout) {
            this.isRightToLeftLayout = isRightToLeftLayout;
            return this;
        }

        public GuillotineBuilder setInterpolator(TimeInterpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public void build() {
            new GuillotineAnimation(this);
        }
    }
}
