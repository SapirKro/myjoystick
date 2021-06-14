package com.sapir.myjoystickapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.view.MotionEvent;


////<<<---alirron--->>>>> X
////     /\
////     |
////   ELEVTOR  Y
////     |
////     |
///////  \/
public class MyNewJoystick extends View {

    static float x;
    static float y;
    static int r = 0, g = 0, b = 0;
    float littleCircleRadisSize;
    Paint paint = null;
    private double currentLittleCircleX = 0;
    private double currentLittleCircleY = 0;
    private int myCenterX;
    private int myCenterY;
    private int radiousOfBigCircle;
    private int radiousOfBigCircleWithBorder;


    public MyNewJoystick(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyNewJoystick(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();

    }


    public interface onTouchEventListener {


        void onMove(double x, double y);
    }

    private onTouchEventListener mListener;

    public MyNewJoystick(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(255, r, g, b);
        setFocusable(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        paint.setARGB(255, r, g, b);

        //drawing the border
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(myCenterX, myCenterY, radiousOfBigCircleWithBorder, paint);
        ///big circle
        paint.setColor(Color.parseColor("#252525"));
        canvas.drawCircle(myCenterX, myCenterY, radiousOfBigCircle, paint);
        ///little circle
        paint.setColor(Color.parseColor("#A3060D"));

        canvas.drawCircle(x, y, littleCircleRadisSize, paint);
    }

    public void setonTouchEventListener(onTouchEventListener l) {
        mListener = l;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_UP) {
            Log.d("DEBUG_TAG", "Action was UP");
            x = myCenterX;
            y = myCenterY;
            if (mListener != null)
                mListener.onMove((getX(this.x)), (getY(this.y)));
            this.invalidate();
            return true;
        }
        currentLittleCircleX = event.getX();
        currentLittleCircleY = event.getY();
        double c = Math.sqrt((currentLittleCircleX - myCenterX) * (currentLittleCircleX - myCenterX)
                + (currentLittleCircleY - myCenterY) * (currentLittleCircleY - myCenterY));
        if (c > radiousOfBigCircle) {
            return true;
            /// currentLittleCircleX = (int) ((currentLittleCircleX - myCenterX) * radiousOfBigCircle / c + myCenterX);
            /// currentLittleCircleY = (int) ((currentLittleCircleY - myCenterY) * radiousOfBigCircle / c + myCenterY);
        }
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d("DEBUG_TAG", "Action was DOWN");
                break;
            case (MotionEvent.ACTION_MOVE):
                Log.d("DEBUG_TAG", "Action was MOVE");
                break;
            default:
                return super.onTouchEvent(event);
        }

        ///cumptue the current radius
        ///check if we cross the borader

        x = (float) currentLittleCircleX;
        y = (float) currentLittleCircleY;
        if (mListener != null)
            mListener.onMove((getX(this.x)), (getY(this.y)));


        this.invalidate();
        return true;

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        myCenterX = (int) (currentLittleCircleX = getWidth() / 2);
        myCenterY = (int) (currentLittleCircleY = getHeight() / 2);
        x = myCenterX;
        y = myCenterY;
        int min = 0;
        if (w < h) {
            min = w;
        } else {
            min = h;
        }

        this.radiousOfBigCircle = (int) ((min / 2.7));
        littleCircleRadisSize = (int) ((min / 11));
        radiousOfBigCircleWithBorder = this.radiousOfBigCircle + 8;

    }

    public double distance(float x, float y, float centerx, float centery) {
        return Math.sqrt((x - centerx) * (x - centerx) + (y - centery) * (y - centery));
    }

    public double getX(float x) {
        double distanceXfromCenter = distance(x, 0, this.myCenterX, 0);

        if (x < this.myCenterX) {
            distanceXfromCenter = distanceXfromCenter * (-1);
        }

        distanceXfromCenter = (distanceXfromCenter / this.radiousOfBigCircle);
        return distanceXfromCenter;


    }

    public double getY(float y) {

        double distanceYfromCenter = distance(0, y, 0, this.myCenterY);

        if (y < this.myCenterY) {
            distanceYfromCenter = distanceYfromCenter * (-1);
        }
        distanceYfromCenter = (distanceYfromCenter / this.radiousOfBigCircle);

        return distanceYfromCenter;
    }


}

