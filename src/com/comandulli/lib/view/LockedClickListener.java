package com.comandulli.lib.view;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Prevents monkeys from breaking your application's buttons.
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public abstract class LockedClickListener implements OnClickListener {

    /**
     * If this listener's button has already been clicked.
     */
    private boolean hasBeenClicked;

    /**
     * Implementation of the {@link #onClick(View)} method, preventing from any further
     * onClick calls to fire if this {@link #onClick(View)} has not finished its process.
     *
     * @param view {@link #onClick(View)}
     */
    @Override
    public void onClick(View view) {
        if (!hasBeenClicked) {
            hasBeenClicked = true;
            lockedOnClick(view);
        }
    }

    /**
     * Unlock this listener, allowing it to fire again.
     */
    public void unlock() {
        hasBeenClicked = false;
    }

    /**
     * Function to be executed at {@link #onClick(View)},
     * this listener gets locked right after, preventing multiple
     * {@link #onClick(View)} to be fired.
     *
     * @param view
     */
    public abstract void lockedOnClick(View view);

}
