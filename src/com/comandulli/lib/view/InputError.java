package com.comandulli.lib.view;

/**
 * Specifies that an error has ocurred in a TextInput {@see com.comandulli.lib.view.TextInput}
 *
 * @author <a href="mailto:caioa.comandulli@gmail.com">Caio Comandulli</a>
 * @since 1.0
 */
public class InputError {

    /**
     * Resource of the error text.
     */
    private final int errorStringResource;

    /**
     * Instantiate an error.
     *
     * @param errorStringResource the error text resource
     */
    public InputError(int errorStringResource) {
        this.errorStringResource = errorStringResource;
    }

    /**
     * Get this error's resource string.
     *
     * @return the resource
     */
    public int getStringResource() {
        return errorStringResource;
    }

}
