package com.gandan.android.poc.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Purpose of this class is practicing creating custom view.
 * It will draw circle in center of view.
 * Reference : https://github.com/passsy/android-HoloCircularProgressBar
 *
 * chandra (otama.ariq@gmail.com)
 */
public class CircleView extends View {

    private static final String TAG = CircleView.class.getSimpleName();

    /**
     * tag color
     */
    public static final String COLOR = "color";

    /**
     * tag state saved
     */
    public static final String STATE_SAVED = "state_saved";

    /**
     * Background's paint.
     *
     */
    private Paint mColorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * The rectangle enclosing the circle.
     */
    private final RectF mCircleBounds = new RectF();

    /**
     * Circle start point
     */
    private float mStartAngle = 0;

    /**
     * Circle angle from start point
     */
    private float mSweepAngle = 360;

    /**
     * Circle's color
     */
    private int mCircleColor;

    /**
     * The size of circle's stroke which use for painting
     */
    private int mCircleStrokeWidth;
    /**
     * The Translation offset x which gives us the ability to use our own
     * coordinates system.
     */
    private float mTranslationOffsetX;

    /**

     * The Translation offset y which gives us the ability to use our own
     * coordinates system.
     */
    private float mTranslationOffsetY;

    private final int mStrokeWidth = 10;

    /**
     * Radius of the circle
     *
     * <p>
     * Note: (Re)calculated in {@link #onMeasure(int, int)}.
     * </p>
     */
    private float mRadius;

    public CircleView(final Context context) {
        this(context, null);
    }

    public CircleView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(final Context context, final AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setColor(Color.CYAN);
        setWheelSize(mStrokeWidth);
        updateColor();
    }

    @Override
    public void onDraw(final Canvas canvas) {
        // translate the circle bound
        canvas.translate(mTranslationOffsetX, mTranslationOffsetY);

        // do drawing here
        canvas.drawArc(mCircleBounds, mStartAngle, mSweepAngle, false, mColorPaint);
    }

    /**
 	 * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, height);

        final float halfWidth = min * 0.5f;
        mRadius = halfWidth - mStrokeWidth * 2;

        mCircleBounds.set(-mRadius, -mRadius, mRadius, mRadius);

        mTranslationOffsetX = halfWidth;
        mTranslationOffsetY = halfWidth;
    }

    /**
     *  update background and circle color. Invalidate at the end function
     */
    public void updateColor() {
        mColorPaint.setColor(mCircleColor);
        mColorPaint.setStyle(Paint.Style.STROKE);
        mColorPaint.setStrokeWidth(mCircleStrokeWidth);

        invalidate();
    }

    /**
     *  set circle color
     */
    public void setColor(final int color) {
       mCircleColor  = color;
    }

    /**
     * set how big is the circle
     *
     * @param dimension - int
     */
    public void setWheelSize(final int dimension) {
        mCircleStrokeWidth = dimension;
    }

    /**
 	 * (non-Javadoc)
     *
     * @see android.view.View#onSaveInstanceState()
     */
    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SAVED, super.onSaveInstanceState());
        bundle.putInt(COLOR, mCircleColor);
        return bundle;
    }

    /**
  	 * (non-Javadoc)
     *
     * @see android.view.View#onRestoreInstanceState(android.os.Parcelable)
     */
    @Override
    protected void onRestoreInstanceState(final Parcelable state) {
        if( state instanceof  Bundle) {
            final Bundle bundle = (Bundle) state;
            setColor(bundle.getInt(COLOR));
            updateColor();

            super.onRestoreInstanceState(bundle.getParcelable(STATE_SAVED));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
