package com.comandulli.lib.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * TextInput helps you with EditText validation and masking.
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public class TextInput {
    /**
     * If this input has an error.
     */
    private boolean hasError;
    /**
     * The EditText view of this input.
     */
    private final EditText editText;
    /**
     * The android context.
     */
    private final Context context;
    /**
     * Good Color, when the field is valid.
     */
    private final int goodColor;
    /**
     * Bad Color, when the filed is invalid.
     */
    private final int badColor;

    /**
     * Instantiates a text input.
     *
     * @param editText  EditText view
     * @param context   android context
     * @param goodColor the good color
     * @param badColor  the bad color
     */
    public TextInput(EditText editText, Context context, int goodColor, int badColor) {
        this.editText = editText;
        this.context = context;
        this.goodColor = goodColor;
        this.badColor = badColor;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isValid()) {
                    reset();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Set a mask for this input.
     *
     * @param mask the mask
     */
    public void setMask(TextMask mask) {
        mask.setInput(this);
        editText.addTextChangedListener(mask);
    }

    /**
     * Get this input's EditText view.
     *
     * @return
     */
    public EditText getEditText() {
        return editText;
    }

    /**
     * Declare this input as invalid.
     *
     * @param error the error to be displayed. {@see com.comandulli.lib.view.InputError}
     */
    public void invalidate(InputError error) {
        this.hasError = true;
        editText.setError(context.getString(error.getStringResource()));
        editText.setTextColor(context.getResources().getColor(badColor));
        editText.getBackground().setColorFilter(context.getResources().getColor(badColor), Mode.SRC_ATOP);
    }

    /**
     * Check if this input is valid.
     *
     * @return if it is valid
     */
    public boolean isValid() {
        return hasError;
    }

    /**
     * Get the text of this input.
     *
     * @return the text
     */
    public String getText() {
        return editText.getText().toString();
    }

    /**
     * Set the text of this input.
     *
     * @param text the text
     */
    public void setText(String text) {
        editText.setText(text);
        editText.setSelection(text.length());
    }

    /**
     * Reset the state of this input to its original state.
     */
    public void reset() {
        this.hasError = false;
        editText.setError(null);
        editText.setTextColor(context.getResources().getColor(goodColor));
        editText.getBackground().setColorFilter(null);
    }

}
