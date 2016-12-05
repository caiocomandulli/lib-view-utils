package com.comandulli.lib.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * ScrollableTextView is your standard TextView with a scroll functionality!
 * <p>
 * When the total height of the text is greater than your desired height,
 * it scrolls automatically the text, allowing your user to read it entirely.
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public class ScrollableTextView extends TextView {
    /**
     * Default time it takes to start the scrolling.
     */
    public static final int TIME_TO_START = 1500;
    /**
     * Default time it takes for the scrolling to reset.
     */
    public static final int TIME_TO_RESET = 1500;
    /**
     * Default rate at which the scroll speed is applied.
     */
    public static final int TICK = 34;
    /**
     * Default scrolling speed.
     */
    public static final int SCROLL_SPEED = 1;
    /**
     * Current scroll applied.
     */
    private int currentScroll = 0;
    /**
     * Time it takes to start the scrolling.
     */
    private int timeToStart = TIME_TO_START;
    /**
     * Time it takes for the scrolling to reset.
     */
    private int timeToReset = TIME_TO_RESET;
    /**
     * Rate at which the scroll speed is applied.
     */
    private int tick = TICK;
    /**
     * Scrolling speed.
     */
    private int scrollSpeed = SCROLL_SPEED;

    /**
     * {@see android.widget.TextView}
     *
     * @param context {@see android.widget.TextView}
     */
    public ScrollableTextView(Context context) {
        super(context);
        establish();

    }

    /**
     * {@see android.widget.TextView}
     *
     * @param context {@see android.widget.TextView}
     * @param attrs   {@see android.widget.TextView}
     */
    public ScrollableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        establish();
    }

    /**
     * {@see android.widget.TextView}
     *
     * @param context      {@see android.widget.TextView}
     * @param attrs        {@see android.widget.TextView}
     * @param defStyleAttr {@see android.widget.TextView}
     */
    public ScrollableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        establish();
    }

    /**
     * Established the behaviour of the scrolling text view.
     */
    private void establish() {
        setSelected(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getLineCount() * getLineHeight() > getMeasuredHeight()) {
                    currentScroll += scrollSpeed;
                }
                final Runnable upperThis = this;
                if (currentScroll > getLineCount() * getLineHeight() - getMeasuredHeight()) {
                    currentScroll = 0;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scrollTo(0, currentScroll);
                            new Handler().postDelayed(upperThis, timeToStart);
                        }
                    }, timeToReset);
                } else {
                    scrollTo(0, currentScroll);
                    new Handler().postDelayed(this, tick);
                }
            }
        }, timeToStart);
    }

    /**
     * Get the scrolling speed.
     *
     * @return the speed
     */
    public int getScrollSpeed() {
        return scrollSpeed;
    }

    /**
     * Set the scrolling speed.
     *
     * @param scrollSpeed the speed
     */
    public void setScrollSpeed(int scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    /**
     * Get the rate at which the scroll speed is applied.
     *
     * @return tick rate
     */
    public int getTick() {
        return tick;
    }

    /**
     * Set the rate at which the scroll speed is applied.
     *
     * @param tick tick rate
     */
    public void setTick(int tick) {
        this.tick = tick;
    }

    /**
     * Set the time it takes for the scrolling to reset.
     *
     * @param timeToReset the time
     */
    public void setTimeToReset(int timeToReset) {
        this.timeToReset = timeToReset;
    }

    /**
     * Get the time it takes for the scrolling to reset.
     *
     * @return the time
     */
    public int getTimeToReset() {
        return timeToReset;
    }

    /**
     * Set the time it takes to start the scrolling.
     *
     * @param timeToStart the time
     */
    public void setTimeToStart(int timeToStart) {
        this.timeToStart = timeToStart;
    }

    /**
     * Get the time it takes to start the scrolling.
     *
     * @return the time
     */
    public int getTimeToStart() {
        return timeToStart;
    }

}
