package com.journaldev.androidmvvmbasics.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.view.MotionEvent;

public class MyNewJoystick extends View  {

    public interface OnMoveListener {

        /**
         * Called when a JoystickView's button has been moved
         * @param angle current angle
         * @param strength current strength
         */
        void onMove(int angle, int strength);
    }

    private static final String DEBUG_TAG ="DEBUG_TAG" ;
    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */

    static float x=300;
    static float y=300;
    static int r=0,g=0,b=0;
    final static int radius=30;
    private long mLoopInterval =100;
    Paint paint = null;
    private float littleCircleRadisSize=50;
    private int mPosX = 0;
    private int mPosY = 0;
    private float currentLittleCircleX = 0;
    private float currentLittleCircleY = 0;
    private int anotherStratXLittleCIrcle = 300;
    private int anotherStratYLittleCIrcle = 300;
    private int radiousOfBigCircle=200;

    private OnMoveListener mCallback;
    /**
     * The allowed direction of the button is define by the value of this parameter:
     * - a negative value for horizontal axe
     * - a positive value for vertical axe
     * - zero for both axes
     */
    private int mButtonDirection = 0;
    public MyNewJoystick(Context context) {
        super(context);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(255, r, g, b);

        setFocusable(true);



    }
    /**
     * Register a callback to be invoked when this JoystickView's button is moved
     * @param l The callback that will run
     */
    public void setOnMoveListener(OnMoveListener l) {
        setOnMoveListener(l, 100);
    }


    /**
     * Register a callback to be invoked when this JoystickView's button is moved
     * @param l The callback that will run
     * @param loopInterval Refresh rate to be invoked in milliseconds
     */
    public void setOnMoveListener(OnMoveListener l, int loopInterval) {
        mCallback = l;
        mLoopInterval = loopInterval;
    }
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
    protected void BigCircle(Canvas canvas) {
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 350;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x / 2, y / 2, radius, paint);
    }

    private void initPosition() {
        // get the center of view to position circle
       anotherStratXLittleCIrcle = (int) (currentLittleCircleX = getWidth() / 2);
       anotherStratYLittleCIrcle = (int) (currentLittleCircleY = getWidth() / 2);
    }

    protected void littleCircle(Canvas canvas,int x,int y) {

        int radius;
        radius = 200;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x / 2, y / 2, radius, paint);

    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setARGB(255, r, g, b);
       //// initPosition();
        //drawing the border
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(anotherStratXLittleCIrcle ,anotherStratYLittleCIrcle ,(radiousOfBigCircle+4),paint);
///big circle
        paint.setColor(Color.parseColor("#252525"));
        canvas.drawCircle(anotherStratXLittleCIrcle,anotherStratYLittleCIrcle ,radiousOfBigCircle,paint);

        ///little circle
        paint.setColor(Color.parseColor("#A3060D"));
        canvas.drawCircle(x,y,littleCircleRadisSize,paint);

        /*
        super.onDraw(canvas);
        paint.setARGB(255, r, g, b);

        //drawing the circle
        canvas.drawCircle(x,y,radius,paint);*/
        /// BigCircle(canvas);
        // littleCircle(canvas);
/*
        int x = getWidth();
        int y = getHeight();
        mPosY=y / 2;
        mPosX=x / 2;
        int radius;
        radius = 350;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x / 2, y / 2, radius, paint);
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(mPosX, mPosY, 100, paint);*/


    }
/*

    public boolean onTouchEvent(MotionEvent event) {
        // if disabled we don't move the


        // to move the button according to the finger coordinate
        // (or limited to one axe according to direction option
        mPosY = mButtonDirection < 0 ? mCenterY : (int) event.getY(); // direction negative is horizontal axe
        mPosX = mButtonDirection > 0 ? mCenterX : (int) event.getX(); // direction positive is vertical axe
        return true;
    }*/
    /**
     * Process the strength as a percentage of the distance between the center and the border.
     * @return the strength of the button
     */
    private int getStrength() {
        return (int) (100 * Math.sqrt((currentLittleCircleX - anotherStratXLittleCIrcle)
                * (currentLittleCircleX - anotherStratXLittleCIrcle) + (currentLittleCircleY - anotherStratYLittleCIrcle)
                * (currentLittleCircleY - anotherStratYLittleCIrcle)) / radiousOfBigCircle);
    }

    private int getAngle() {
        int angle = (int) Math.toDegrees(Math.atan2(anotherStratYLittleCIrcle - currentLittleCircleY, currentLittleCircleX - anotherStratXLittleCIrcle));
        return angle < 0 ? angle + 360 : angle; // make it as a regular counter-clock protractor
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();



        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                ///while pressed-changed location to the first cordinted that has been pressed
                Log.d("DEBUG_TAG","Action was DOWN");
                currentLittleCircleX=event.getX();
                currentLittleCircleY=event.getY();
                if (mCallback != null)
                    mCallback.onMove(getAngle(), getStrength());
                break;
            case (MotionEvent.ACTION_MOVE) :
                ///while mouse move,changed accorinly
                Log.d("DEBUG_TAG","Action was MOVE");
                currentLittleCircleX=event.getX();
                currentLittleCircleY=event.getY();
                break;
            case (MotionEvent.ACTION_UP) :
                ///while relsed-changed location to the last cordinted that has been pressed
                Log.d("DEBUG_TAG","Action was UP");
                currentLittleCircleX=anotherStratXLittleCIrcle;
                currentLittleCircleY=anotherStratYLittleCIrcle;
                if (mCallback != null)
                    mCallback.onMove(getAngle(), getStrength());
                break;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("DEBUG_TAG","Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("DEBUG_TAG","Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
        ///cumptue the current radius
        ///check if we cross the borader
        double c = Math.sqrt((currentLittleCircleX - anotherStratXLittleCIrcle) * (currentLittleCircleX - anotherStratXLittleCIrcle)
                + (currentLittleCircleY - anotherStratYLittleCIrcle) * (currentLittleCircleY - anotherStratYLittleCIrcle));
        if (c > radiousOfBigCircle ) {
            currentLittleCircleX = (int) ((currentLittleCircleX - anotherStratXLittleCIrcle) * radiousOfBigCircle / c + anotherStratXLittleCIrcle);
            currentLittleCircleY = (int) ((currentLittleCircleY - anotherStratYLittleCIrcle) * radiousOfBigCircle / c + anotherStratYLittleCIrcle);
        }
        x=currentLittleCircleX;
        y=currentLittleCircleY;
        this.invalidate();
        return true;
    /*x=(int)event.getX()-(radius/2);      //logic to plot the circle in exact touch place
    y=(int)event.getY()-(radius/2);
    //System.out.println("X,Y:"+"x"+","+y);
    randColor();
    invalidate();


    if (event.getAction() == MotionEvent.ACTION_UP) {
        paint.setARGB(12, r, g, b);
    }
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
        paint.setARGB(12, r, g, b);
    }
*/


    }

    public void setButtonSizeRatio(float newRatio) {
        if (newRatio > 0.0f & newRatio <= 1.0f) {
            littleCircleRadisSize = newRatio;
        }
    }

    public void randColor()
    {
        //r=(int)(Math.random()*255);
        //g=(int)(Math.random()*255);
        //b=(int)(Math.random()*255);
        r=1;
        g=2;
        b=3;
        //Toast.makeText(c, "r,g,b="+r+","+g+","+b,Toast.LENGTH_SHORT).show();
    }

    public void dissColor(){

        r=0;
        g=0;
        b=0;

    }
}

