package com.mathiasluo.library;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by MathiasLuo on 16/11/27.
 */

public class StatusProgressUtil {
    public static StatusProgress setStatusProgress(Activity context) {
        StatusProgress statusProgress = new StatusProgress(context);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                // Allows the view to be on top of the StatusBar
                WindowManager.LayoutParams.TYPE_TOAST,
                // Keeps the button presses from going to the background window
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        // Enables the notification to recieve touch events
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        // Draws over status bar
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.TOP | Gravity.CENTER;

        context.getWindowManager().addView(statusProgress, layoutParams);

        return statusProgress;
    }


}
