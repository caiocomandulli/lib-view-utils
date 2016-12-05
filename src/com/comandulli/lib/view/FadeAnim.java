package com.comandulli.lib.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Support class for controlling fade animations in ImageView, perfect for splashscreens!
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public class FadeAnim {

    /**
     * Android context.
     */
    private final Context context;
    /**
     * Resource of the fade in animation.
     */
    private final int fadeInRes;
    /**
     * Resource of the fade out animation.
     */
    private final int fadeOutRes;
    /**
     * List of procedures to be run when this animation is executed.
     */
    private final List<Runnable> procedures = new ArrayList<>();
    /**
     * Current procedure running.
     */
    private int currentProcedure;
    /**
     * List of views to be used by fade procedures.
     */
    private final List<ImageView> views = new ArrayList<>();
    /**
     * If {@link FadeAnim#trigger()} has been called
     */
    private boolean alreadyTriggered;
    /**
     * If it is waiting for a {@link FadeAnim#trigger()} call.
     */
    private boolean waitingForTrigger;

    /**
     * Instantiate a fade animation cycle.
     *
     * @param context    android context
     * @param fadeInRes  resource for the fade in animation
     * @param fadeOutRes resource for the fade out animation
     */
    public FadeAnim(Context context, int fadeInRes, int fadeOutRes) {
        this.context = context;
        this.fadeInRes = fadeInRes;
        this.fadeOutRes = fadeOutRes;
    }

    /**
     * Chain a Fade In procedure.
     *
     * @param imageView      target image view
     * @param timeout        duration of the procedure
     * @param waitForTrigger if the animation should wait for a {@link FadeAnim#trigger()} call after finishing this procedure
     * @return this object for inline invokes
     */
    public FadeAnim chainFadeIn(final ImageView imageView, final long timeout, final boolean waitForTrigger) {
        if (!views.contains(imageView)) {
            views.add(imageView);
        }
        final Animation animation = AnimationUtils.loadAnimation(context, fadeInRes);
        Runnable procedure = new Runnable() {
            @Override
            public void run() {
                if (alreadyTriggered || !waitForTrigger) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.startAnimation(animation);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentProcedure++;
                            procedures.get(currentProcedure).run();
                        }
                    }, timeout);
                } else {
                    waitingForTrigger = true;
                }
            }
        };
        procedures.add(procedure);
        return this;
    }

    /**
     * Chain a Fade Out procedure.
     *
     * @param imageView      target image view
     * @param timeout        duration of the procedure
     * @param waitForTrigger if the animation should wait for a {@link FadeAnim#trigger()} call after fininshing this procedure
     * @return this object for inline invokes
     */
    public FadeAnim chainFadeOut(final ImageView imageView, final long timeout, final boolean waitForTrigger) {
        if (!views.contains(imageView)) {
            views.add(imageView);
        }
        final Animation animation = AnimationUtils.loadAnimation(context, fadeOutRes);
        Runnable procedure = new Runnable() {
            @Override
            public void run() {
                if (alreadyTriggered || !waitForTrigger) {
                    imageView.startAnimation(animation);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setVisibility(View.GONE);
                            currentProcedure++;
                            if (procedures.size() != currentProcedure) {
                                procedures.get(currentProcedure).run();
                            }
                        }
                    }, timeout);
                } else {
                    waitingForTrigger = true;
                }
            }
        };
        procedures.add(procedure);
        return this;
    }

    /**
     * Tell the chain to wait for a specific time before moving on to the next procedure.
     *
     * @param timeout duration of the wait
     * @return this object for inline invokes
     */
    public FadeAnim maintainStatic(final long timeout) {
        Runnable procedure = new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentProcedure++;
                        if (procedures.size() != currentProcedure) {
                            procedures.get(currentProcedure).run();
                        }
                    }
                }, timeout);
            }
        };
        procedures.add(procedure);
        return this;
    }

    /**
     * Close a chain of animations with a runnable procedure,
     * the runnable will be executed after all previous procedures are done.
     *
     * @param runnable the code to be run
     * @return this object for inline invokes
     */
    public FadeAnim closeChain(final Runnable runnable) {
        Runnable procedure = new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(runnable, 500);
            }
        };
        procedures.add(procedure);

        return this;
    }

    /**
     * Tell this animation to start its first procedure.
     */
    public void animate() {
        for (ImageView view : views) {
            view.setVisibility(View.GONE);
        }
        currentProcedure = 0;
        new Handler().postDelayed(procedures.get(currentProcedure), 1000);
    }

    /**
     * Tell this animation that trigger has been called.
     */
    public void trigger() {
        alreadyTriggered = true;
        if (waitingForTrigger) {
            waitingForTrigger = false;
            procedures.get(currentProcedure).run();
        }
    }

    /**
     *  The animation should wait for a {@link FadeAnim#trigger()} call when this procedure is run.
     * @param timeout duration of the procedure
     * @return this object for inline invokes
     */
    public FadeAnim waitForTrigger(final long timeout) {
        Runnable procedure = new Runnable() {
            @Override
            public void run() {
                if (alreadyTriggered) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentProcedure++;
                            if (procedures.size() != currentProcedure) {
                                procedures.get(currentProcedure).run();
                            }
                        }
                    }, timeout);
                } else {
                    waitingForTrigger = true;
                }
            }
        };
        procedures.add(procedure);
        return this;
    }

}
