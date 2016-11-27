package com.mathiasluo.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MathiasLuo on 16/11/27.
 */

public class StatusProgress extends View {

    public interface StatusProgressBarListener {
        void onProgressChange(int current, int max);
    }

    private int mMaxProgress = 100;
    private int mCurrentProgress = 0;


    private int mDefaultBarHeight = 4;
    private int mDefaultBarWidth = -1;

    private int mAttachColor;
    private int mUnAttachColor;


    private RectF mUnAttachRectF = new RectF(0, 0, 0, 0);
    private RectF mAttachRectF = new RectF(0, 0, 0, 0);

    private Paint mAttachPaint;
    private Paint mUnAttachPaint;


    private StatusProgressBarListener mStatusProgressBarListener;

    public void setStatusProgressBarListener(StatusProgressBarListener statusProgressBarListener) {
        this.mStatusProgressBarListener = statusProgressBarListener;
    }

    public StatusProgress(Context context) {
        this(context, null);
    }

    public StatusProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAttachColor = Color.rgb(66, 145, 241);
        mUnAttachColor = Color.rgb(66, 145, 241);
        initPaint();
    }

    private void initPaint() {
        mAttachPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAttachPaint.setColor(mAttachColor);

        mUnAttachPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mUnAttachPaint.setColor(mUnAttachColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mDefaultBarWidth, (int) dp2px(mDefaultBarHeight));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mAttachRectF.left = getPaddingLeft();
        mAttachRectF.top = 0;
        mAttachRectF.right = (getWidth() - getPaddingLeft() - getPaddingRight()) / (getMaxProgress() * 1.0f) * getCurrentProgress() + getPaddingLeft();
        mAttachRectF.bottom = getHeight() / 2.0f + mDefaultBarHeight / 2.0f;

        mUnAttachRectF.left = mAttachRectF.right;
        mUnAttachRectF.right = getWidth() - getPaddingRight();
        mUnAttachRectF.top =0;
        mUnAttachRectF.bottom = getHeight() / 2.0f + mDefaultBarHeight / 2.0f;

        canvas.drawRect(mAttachRectF, mAttachPaint);
        canvas.drawRect(mUnAttachRectF, mUnAttachPaint);

    }


    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        if (maxProgress > 0) {
            this.mMaxProgress = maxProgress;
            invalidate();
        }
    }

    public int getCurrentProgress() {
        return mCurrentProgress;
    }


    public void incrementProgressBy(int by) {
        if (by > 0) {
            setCurrentProgress(getCurrentProgress() + by);
        }

        if (mStatusProgressBarListener != null) {
            mStatusProgressBarListener.onProgressChange(getCurrentProgress(), getMaxProgress());
        }
    }

    public void setCurrentProgress(int currentProgress) {
        if (currentProgress < getMaxProgress() && currentProgress > 0) {
            this.mCurrentProgress = currentProgress;
            invalidate();
        }
    }

    public int getProgressHeight() {
        return mDefaultBarHeight;
    }

    public void setProgressHeight(int defaultBarHeight) {
        this.mDefaultBarHeight = defaultBarHeight;
    }

    public int getProgressWidth() {
        return mDefaultBarWidth;
    }

    public void setProgressWidth(int default_bar_width) {
        this.mDefaultBarWidth = default_bar_width;
    }

    public int getAttachColor() {
        return mAttachColor;
    }

    public void setAttachColor(int attachColor) {
        this.mAttachColor = attachColor;
        mAttachPaint.setColor(attachColor);
        invalidate();
    }

    public int getUnAttachColor() {
        return mUnAttachColor;
    }

    public void setUnAttachColor(int unAttachColor) {
        this.mUnAttachColor = unAttachColor;
        mUnAttachPaint.setColor(unAttachColor);
        invalidate();
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}