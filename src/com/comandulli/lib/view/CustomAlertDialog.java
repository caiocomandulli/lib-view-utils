package com.comandulli.lib.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.TextView;

/**
 * Instantiate your alert dialog with a custom color.
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public class CustomAlertDialog {

    /**
     * Alert dialog after build has been called int he alert builder.
     */
    protected AlertDialog alertDialog;
    /**
     * Alert builder.
     */
    protected final Builder alertBuilder;
    /**
     * Android context.
     */
    protected final Context context;
    /**
     * Your alert custom color.
     */
    protected int customColor;

    /**
     * Instantiate a default alert.
     *
     * @param context android context.
     */
    public CustomAlertDialog(Context context) {
        this.context = context;
        this.alertBuilder = new Builder(context);
    }

    /**
     * Instantiate alert with a message.
     *
     * @param context android context
     * @param message desired message
     */
    public CustomAlertDialog(Context context, String message) {
        this.context = context;
        this.alertBuilder = new Builder(context);
        this.alertBuilder.setMessage(message);
    }

    /**
     * Instantiate alert with message and title.
     *
     * @param context android context
     * @param title   desired title
     * @param message desired message
     */
    public CustomAlertDialog(Context context, String title, String message) {
        this.context = context;
        this.alertBuilder = new Builder(context);
        this.alertBuilder.setTitle(title).setMessage(message);
    }

    /**
     * Set the alert title
     *
     * @param title desired title
     * @return for you to chain methods.
     */
    public CustomAlertDialog setTitle(String title) {
        this.alertBuilder.setTitle(title);
        return this;
    }

    /**
     * Set the alert message
     *
     * @param message desired message.
     * @return for you to chain methods.
     */
    public CustomAlertDialog setMessage(String message) {
        this.alertBuilder.setMessage(message);
        return this;
    }

    /**
     * Set a positive button with a text and listener.
     *
     * @param text     positive button text
     * @param listener listener for the click action
     * @return for you to chain methods.
     */
    public CustomAlertDialog setPositive(String text, OnClickListener listener) {
        this.alertBuilder.setPositiveButton(text, listener);
        return this;
    }

    /**
     * Set a negative button with a text and listener.
     *
     * @param text     negative button text
     * @param listener listener for the click action
     * @return for you to chain methods.
     */
    public CustomAlertDialog setNegative(String text, OnClickListener listener) {
        this.alertBuilder.setNegativeButton(text, listener);
        return this;
    }

    /**
     * Set a netural button with a text and listener.
     *
     * @param text     neutral button text
     * @param listener listener for the click action
     * @return for you to chain methods.
     */
    public CustomAlertDialog setNeutral(String text, OnClickListener listener) {
        this.alertBuilder.setNeutralButton(text, listener);
        return this;
    }

    /**
     * Get this custom alert's alert builder.
     *
     * @return
     */
    public Builder getAlertBuilder() {
        return this.alertBuilder;
    }

    /**
     * Set a custom color for this alert.
     *
     * @param customColor the color
     * @return for you to chain methods.
     */
    public CustomAlertDialog setCustomColor(int customColor) {
        this.customColor = customColor;
        return this;
    }

    /**
     * Show this alert on the screen.
     *
     * @return the alert dialog reference.
     */
    public AlertDialog show() {
        this.alertDialog = this.alertBuilder.show();
        if (customColor != 0) {
            int dividerId = alertDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = alertDialog.findViewById(dividerId);
            divider.setBackgroundColor(context.getResources().getColor(customColor));
            int textViewId = alertDialog.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
            TextView tv = (TextView) alertDialog.findViewById(textViewId);
            tv.setTextColor(context.getResources().getColor(customColor));
        }
        return alertDialog;
    }

    /**
     * Set the view for this alert.
     *
     * @param v the view to be set
     */
    public void setView(View v) {
        this.alertBuilder.setView(v);
    }

}
