package com.comandulli.lib.view;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Mask to be applied to a specific TextInput {@see com.comandulli.lib.view.TextInput}
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public abstract class TextMask implements TextWatcher {
    /**
     * Last watched value.
     */
    private String watched = "";
    /**
     * Input assigned.
     */
    private TextInput input;

    /**
     * Instantiate a mask without an input assigned.
     */
    public TextMask() {
    }

    /**
     * Instantiate a mask with an input assigned.
     *
     * @param input the input
     */
    public TextMask(TextInput input) {
        this.input = input;
    }

    /**
     * Assign this mask's input.
     *
     * @param input the input
     */
    public void setInput(TextInput input) {
        this.input = input;
    }

    /**
     * The assigned input of this mask.
     *
     * @return
     */
    public TextInput getInput() {
        return input;
    }

    /**
     * Implementation of the onTextChanged.
     * Applying masking operations.
     *
     * @param s      {@link #onTextChanged(CharSequence, int, int, int)}
     * @param start  {@link #onTextChanged(CharSequence, int, int, int)}
     * @param before {@link #onTextChanged(CharSequence, int, int, int)}
     * @param count  {@link #onTextChanged(CharSequence, int, int, int)}
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String value = s.toString();
        // avoid loop
        if (value.equals(watched)) {
            return;
        }
        // mask value
        value = mask(value);
        // deliver value
        watched = value;
        input.getEditText().setText(value);
        input.getEditText().setSelection(value.length());
    }

    /**
     * {@link #beforeTextChanged(CharSequence, int, int, int)}
     *
     * @param s     {@link #beforeTextChanged(CharSequence, int, int, int)}
     * @param start {@link #beforeTextChanged(CharSequence, int, int, int)}
     * @param count {@link #beforeTextChanged(CharSequence, int, int, int)}
     * @param after {@link #beforeTextChanged(CharSequence, int, int, int)}
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //stub
    }

    /**
     * {@link #afterTextChanged(Editable)}
     *
     * @param s {@link #afterTextChanged(Editable)}
     */
    @Override
    public void afterTextChanged(Editable s) {
        //stub
    }

    /**
     * Implement this method,
     * modify the value to apply your desired mask.
     *
     * @param value in input
     * @return the masked value.
     */
    public abstract String mask(String value);

}
