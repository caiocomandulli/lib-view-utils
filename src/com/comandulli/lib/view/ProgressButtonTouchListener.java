package com.comandulli.lib.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

/**
 * A progress button, the longer the user holds the button down
 * the more the progress bar goes forward, at the end,
 * we trigger the button.
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public class ProgressButtonTouchListener implements OnTouchListener {
    /**
     * If this button is down.
     */
    private boolean isDown;
    /**
     * Controller of the progress bar animation.
     */
    private ObjectAnimator progressAnimator;
    /**
     * Progress Bar to be animated.
     */
    private final ProgressBar progressBar;
    /**
     * Duration of the progress animation.
     */
    private final int milisec;

    /**
     * Instantiate a Progress Button.
     *
     * @param progressBar progress bar view
     * @param milisec     duration
     */
    public ProgressButtonTouchListener(ProgressBar progressBar, int milisec) {
        this.progressBar = progressBar;
        this.milisec = milisec;
    }

    /**
     * Implementation of the OnTouch, controlling the progress bar.
     *
     * @param v     {@link #onTouch(View, MotionEvent)}
     * @param event {@link #onTouch(View, MotionEvent)}
     * @return {@link #onTouch(View, MotionEvent)}
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isDown) {
                isDown = true;
                progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
                progressAnimator.setDuration(milisec);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();
                progressAnimator.addListener(new AnimatorListener() {
                    boolean isCancelled;

                    @Override
                    public void onAnimationStart(Animator animation) {
                        isCancelled = false;
                        onStartProgress();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (!isCancelled) {
                            progressBar.setProgress(0);
                            onEndProgress();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        isCancelled = true;
                        progressBar.setProgress(0);
                        onCancelProgress();
                    }
                });
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (progressAnimator != null) {
                progressAnimator.cancel();
            }
            isDown = false;
        }
        return false;
    }

    /**
     * {@link #onStartProgress()}
     */
    @SuppressWarnings("EmptyMethod")
    public void onStartProgress() {
        // stub
    }

    /**
     * {@link #onEndProgress()}
     */
    @SuppressWarnings("EmptyMethod")
    public void onEndProgress() {
        // stub
    }

    /**
     * {@link #onCancelProgress()}
     */
    @SuppressWarnings("EmptyMethod")
    public void onCancelProgress() {
        // stub
    }

}
