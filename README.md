# View Utilities for Android

Classes that help with common view necessities.

## Contents

- [Custom Colored Alert Dialog] (#custom-colored-alert-dialog)
- [Fade Animation] (#fade-animation)
- [Text Input with Error and Mask] (#text-input-with-error-and-mask)
- [Locked Click Listener] (#locked-click-listener)
- [Progress Button] (#progress-button)

### Custom Colored Alert Dialog

`CustomAlertDialog` allows you to customize the color of the standard alert dialog.

Original

<img src="https://github.com/caiocomandulli/lib-view-utils/blob/master/uncolored.jpg" width="300">

Colored!

<img src="https://github.com/caiocomandulli/lib-view-utils/blob/master/dialog.jpg" width="300">

### Fade Animation

`FadeAnim` makes splash screens and resource loading very easy.

```java
FadeAnim fadeAnim = new FadeAnim(context, R.anim.fadein, R.anim.fadeout);
```

We instantiate a `FadeAnim`, `R.anim.fadein` and `R.anim.fadeout` are the default animations, but you can use your own too!

````java
fadeAnim.chainFadeIn(logo, 500, false).maintainStatic(500).chainFadeOut(logo, 500, true);
````

Next we chain a fade in animation with `chainFadeIn(ImageView, int, boolean)` to our logo with a duration of half a second (in ms), 
right after we tell it to maintain that image for half a second with `maintainStatic(int)`, 
finally we tell it to fade out the image for half a second with `chainFadeOut(ImageView, int, boolean)`, but only if a function `trigger()` has been called.

The `trigger()` function allow you to hold this animation while any other code runs first,
such as an asset being loaded, only after your code has run you trigger the final part of the animation.

````java
fadeAnim.closeChain(new Runnable() {
    @Override
    public void run() {
        // move on with your application
    }
});
````

The `closeChain(Runnable)` dictates the code to be run after the entire animation is done.
Such as moving to another activity.

````java
fadeAnim.animate();
````

Finally `animate()` tells the animation to initiate the behaviour that you defined in the previous methods.

### Text Input with Error and Mask

Needing input validation and masking is pretty standard in forms.

````java
TextInput input = new TextInput(editText, context, Color.Green, Color.Red);
````

We can manage our `EditText` behaviour through the `TextInput` class.

````java
input.invalidate(new InputError(R.string.invalid);
````

When we call `invalidate(InputError)` we tell our input that its contents are not valid.
Thus applying the invalid color to it. `InputError` contains the error message.

````java
input.reset();
````

We reset our input state with `reset()`.

````java
public class VoucherMask extends TextMask {

	public VoucherMask() {
		super();
	}

	public VoucherMask(TextInput input) {
		super(input);
	}

	@Override
	public String mask(String value) {
		String newValue = value;
		int origSize = newValue.length();
		// ensure only uppercase letters and numbers
		newValue = newValue.toUpperCase(Locale.getDefault());
		newValue = newValue.replaceAll("[^A-Z0-9]", "");
		// input line
		if (origSize > 3) {
			newValue = newValue.substring(0, 3) + "-" + newValue.substring(3);
		}
		return newValue;
	}

}
````

Example of how to implement a `TextMask`.

The `mask(String)` is applied every time the field is modified.
Its return is the new value with the mask applied.

### Locked Click Listener

`LockedClickListener` prevents `OnClickListener` to fire multiple times before its process has not yet finished.

````java
@Override
public void lockedOnClick(View v) {
    // execute code
    unlock();
}
````

We implement `lockedOnClick(View)` instead, and when our code is finished we call `unlock()` to allow the button to be clicked again.

### Progress Button

`ProgressButtonTouchListener` integrates a `ProgressBar` to a `OnTouchListener`.

```java
button.setOnTouchListener(new ProgressButtonTouchListener(progressBar, duration) {
    @Override
    public void onCancelProgress() {
        // if the user releases before time
    }

    @Override
    public void onEndProgress() {
        // if he hold for the duration
    }
});
````

This allows a button to be hold during an amount of time, which is tracked by the `ProgressBar`.
If the button is released before time, we reset the animation, if the time runs out we trigger the
listener.

## Install Library

__Step 1.__ Get this code and compile it

__Step 2.__ Define a dependency within your project. For that, access to Properties > Android > Library and click on add and select the library

##  License

MIT License. See the file LICENSE.md with the full license text.

## Author

[![Caio Comandulli](https://avatars3.githubusercontent.com/u/3738961?v=3&s=150)](https://github.com/caiocomandulli "On Github")

Copyright (c) 2016 Caio Comandulli

## Compatibility

This Library is valid for Android systems from version Android 4.4 (android:minSdkVersion="19" android:targetSdkVersion="19").