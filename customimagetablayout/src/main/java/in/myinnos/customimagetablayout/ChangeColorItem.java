package in.myinnos.customimagetablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class ChangeColorItem extends View {

    private int mIconColor = 0xFF45C01A;
    private int mTextColor = 0xFF45C01A;
    private int mTabLineColor = 0xFF45C01A;
    private Bitmap mIconBitmap;
    private String mText = "";
    private String mLineText = "___________________________";

    private Bitmap mBitmap;

    private float mAlpha;

    private Rect mIconRect;
    private Rect mTextBound;
    private Paint mTextPaint;
    private Rect mLineBound;
    private Paint mLinePaint;
    private int mLineTextSize = 100;
    private String tabPosition = "bottom";

    public ChangeColorItem(Context context) {
        this(context, null);
    }

    public ChangeColorItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     */
    public ChangeColorItem(Context context, AttributeSet attrs,
                           int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ChangeColorItem);
        int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.ChangeColorItem_item_icon) {
                BitmapDrawable drawable = (BitmapDrawable) typedArray.getDrawable(attr);
                if (drawable != null)
                    mIconBitmap = drawable.getBitmap();

            } else if (attr == R.styleable.ChangeColorItem_item_icon_color) {
                mIconColor = typedArray.getColor(attr, 0xFF45C01A);

            } else if (attr == R.styleable.ChangeColorItem_item_text_color) {
                mTextColor = typedArray.getColor(attr, 0xFF45C01A);

            } else if (attr == R.styleable.ChangeColorItem_item_tab_color) {
                mTabLineColor = typedArray.getColor(attr, 0xFF45C01A);

            } else if (attr == R.styleable.ChangeColorItem_item_text) {
                mText = typedArray.getString(attr);

            } else if (attr == R.styleable.ChangeColorItem_item_tab_position) {
                tabPosition = typedArray.getString(attr);

            } else if (attr == R.styleable.ChangeColorItem_item_text_size) {
                mTextSize = (int) typedArray.getDimension(attr, TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
                                getResources().getDisplayMetrics()));

            }

        }
        typedArray.recycle();

        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0Xff555555);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

        // Hacking the view to add Stepper line to bottom of the view
        mLineBound = new Rect();
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setDither(true);
        mLinePaint.setTextSize(mLineTextSize);
        mLinePaint.setColor(0Xff555555);
        mLinePaint.getTextBounds(mLineText, 0, mLineText.length(), mLineBound);

        mIconRect = new Rect();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight() - getPaddingTop()
                - getPaddingBottom() - mTextBound.height());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - (mTextBound.height() + iconWidth) / 2;
        mIconRect.set(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mIconBitmap == null) {
            mIconBitmap = textAsBitmap("-");
        }
        if (mBitmap == null) {
            mBitmap = textAsBitmap("-");
        }
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
        int alpha = (int) Math.ceil(255 * mAlpha);
        setupTargetBitmap(alpha);
        drawSourceText(canvas, alpha);
        drawTargetText(canvas, alpha);
        drawSourceLineText(canvas, alpha);
        drawTargetLineText(canvas, alpha);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    /**
     */
    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(0Xff555555);
        mTextPaint.setAlpha(255 - alpha);
        int x = getMeasuredWidth() / 2 - mTextBound.width() / 2;
        int y = mIconRect.bottom + mTextBound.height();
        canvas.drawText(mText, x, y, mTextPaint);
    }

    /**
     */
    private void drawTargetLineText(Canvas canvas, int alpha) {
        mLinePaint.setColor(mTabLineColor);
        mLinePaint.setAlpha(alpha);
        int x = getMeasuredWidth() / 2 - mLineBound.width() / 2;
        int y;
        if (tabPosition.equals("top")) {
            y = mTextBound.bottom + mLineBound.height();
        } else {
            y = mIconRect.bottom + mTextBound.height() + 5;
        }
        canvas.drawText(mLineText, x, y, mLinePaint);
    }

    /**
     */
    private void drawSourceLineText(Canvas canvas, int alpha) {
        mLinePaint.setColor(0Xff555555);
        mLinePaint.setAlpha(255 - alpha);
        int x = getMeasuredWidth() / 2 - mLineBound.width() / 2;
        int y = mIconRect.bottom + mLineBound.height();
        //canvas.drawText(mLineText, x, y, mLinePaint);
    }

    /**
     */
    private void setupTargetBitmap(int alpha) {
        if (mBitmap == null) {
            mBitmap = textAsBitmap("-");
        }
        if (mIconBitmap == null) {
            mIconBitmap = textAsBitmap("-");
        }
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);
        Paint mPaint = new Paint();
        mPaint.setColor(mIconColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);

    }

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_ALPHA = "status_alpha";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA, mAlpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }


    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public Bitmap textAsBitmap(String text) {
        Paint paint = new Paint();
        paint.setTextSize(1);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = 1; // round
        int height = 1;
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }
}
