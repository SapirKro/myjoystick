package com.sapir.myjoystickapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import android.view.MotionEvent;

import com.sapir.myjoystickapp.model.ConnectStatus;
import com.sapir.myjoystickapp.model.MyThreadPool;
import com.sapir.myjoystickapp.model.clientToServer;

import java.util.concurrent.ExecutorService;

/**
 *
 */
////<<<---alirron--->>>>> X
////     /\
////     |
////   ELEVTOR  Y
////     |
////     |
///////  \/
public class MyNewJoystick extends View  {



    static float x;
    static float y;
    static int r=0,g=0,b=0;
    ExecutorService pool;
    ConnectStatus s1;
    float littleCircleRadisSize;
    Paint paint = null;
    private clientToServer c111;
    private double currentLittleCircleX = 0;
    private double currentLittleCircleY = 0;
    private int anotherStratXLittleCIrcle;
    private int anotherStratYLittleCIrcle;
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
        //do stuff that was in your original constructor...
    }
    //// private float limitYmin=y-radiousOfBigCircle;
    ///private float limitYmax=y+radiousOfBigCircle;

    /**
     * Interface definition for a callback to be invoked when a
     * JoystickView's button is moved
     */
   public interface OnMoveListener {


        void onMove(double x, double y);
    }
  private OnMoveListener mCallback;
    public MyNewJoystick(Context context) {
        super(context);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setARGB(255, r, g, b);

        setFocusable(true);



    }



    private void init() {
        paint = new Paint();
    }
 /*   protected void BigCircle(Canvas canvas) {
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
    }*/

   /* protected void littleCircle(Canvas canvas,int x,int y) {

        int radius;
        radius = 200;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x / 2, y / 2, radius, paint);

    }*/
    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setARGB(255, r, g, b);
       //// initPosition();
        //drawing the border
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(anotherStratXLittleCIrcle ,anotherStratYLittleCIrcle ,radiousOfBigCircleWithBorder,paint);
///big circle
        paint.setColor(Color.parseColor("#252525"));
        canvas.drawCircle(anotherStratXLittleCIrcle,anotherStratYLittleCIrcle ,radiousOfBigCircle,paint);

        ///little circle
        paint.setColor(Color.parseColor("#A3060D"));

        canvas.drawCircle(x,y, littleCircleRadisSize,paint);

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
    /*private int getStrength() {
        return (int) (100 * Math.sqrt((currentLittleCircleX - anotherStratXLittleCIrcle)
                * (currentLittleCircleX - anotherStratXLittleCIrcle) + (currentLittleCircleY - anotherStratYLittleCIrcle)
                * (currentLittleCircleY - anotherStratYLittleCIrcle)) / radiousOfBigCircle);
    }

    private int getAngle() {
        int angle = (int) Math.toDegrees(Math.atan2(anotherStratYLittleCIrcle - currentLittleCircleY, currentLittleCircleX - anotherStratXLittleCIrcle));
        return angle < 0 ? angle + 360 : angle; // make it as a regular counter-clock protractor
    }*/
    public void setOnMoveListener(OnMoveListener l) {
        mCallback = l;

    }
    public void addClientandPOOl(ExecutorService p1, ConnectStatus c, clientToServer sc){
        this.pool=p1;
        this.s1=c;
        this.c111=sc;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        if(action==MotionEvent.ACTION_UP){
            Log.d("DEBUG_TAG","Action was UP");
            x=anotherStratXLittleCIrcle;
            y=anotherStratYLittleCIrcle;
             /*   if (mCallback != null)
                    mCallback.onMove(getAngle(), getStrength());*/
           /// sendinfo(this.x,this.y);
            if (mCallback != null)
                mCallback.onMove((getX(this.x)), (getY(this.y)));
            this.invalidate();
            return true;
        }
        currentLittleCircleX=event.getX();
        currentLittleCircleY=event.getY();
        double c = Math.sqrt((currentLittleCircleX - anotherStratXLittleCIrcle) * (currentLittleCircleX - anotherStratXLittleCIrcle)
                + (currentLittleCircleY - anotherStratYLittleCIrcle) * (currentLittleCircleY - anotherStratYLittleCIrcle));
        if (c > radiousOfBigCircle) {
            return true;
           /// currentLittleCircleX = (int) ((currentLittleCircleX - anotherStratXLittleCIrcle) * radiousOfBigCircle / c + anotherStratXLittleCIrcle);
           /// currentLittleCircleY = (int) ((currentLittleCircleY - anotherStratYLittleCIrcle) * radiousOfBigCircle / c + anotherStratYLittleCIrcle);
        }
        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                ///while pressed-changed location to the first cordinted that has been pressed
                Log.d("DEBUG_TAG","Action was DOWN");
             ///   currentLittleCircleX=event.getX();

              //  currentLittleCircleY=event.getY();

               /* if (mCallback != null)
                    mCallback.onMove(getAngle(), getStrength());*/
                break;
            case (MotionEvent.ACTION_MOVE) :
                ///while mouse move,changed accorinly
                Log.d("DEBUG_TAG","Action was MOVE");
               /// currentLittleCircleX=event.getX();
                ///currentLittleCircleY=event.getY();
           /*    if (mCallback != null)
                    mCallback.onMove(getAngle(), getStrength());*/
                break;
            case (MotionEvent.ACTION_UP) :
                ///while relsed-changed location to the last cordinted that has been pressed
                Log.d("DEBUG_TAG","Action was UP");
                x=anotherStratXLittleCIrcle;
                y=anotherStratYLittleCIrcle;
             /*   if (mCallback != null)
                    mCallback.onMove(getAngle(), getStrength());*/
                this.invalidate();
                return true;
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

        x=(float)currentLittleCircleX;
        y=(float)currentLittleCircleY;
       if (mCallback != null)
            mCallback.onMove((getX(this.x)), (getY(this.y)));

       /// sendinfo(this.x ,this.y);
        this.invalidate();
        return true;

    }
    /**
     * This is called during layout when the size of this view has changed.
     * Here we get the center of the view and the radius to draw all the shapes.
     *
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldW Old width of this view.
     * @param oldH Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

       /// initPosition();
       anotherStratXLittleCIrcle = (int) (currentLittleCircleX = getWidth() / 2);
       anotherStratYLittleCIrcle = (int) (currentLittleCircleY = getHeight() / 2);
        x=anotherStratXLittleCIrcle;
        y= anotherStratYLittleCIrcle;
       // radius based on smallest size : height OR width
        int d = Math.min(w, h);
        this.radiousOfBigCircle=(int) ((d / 2.7) );

       //// mButtonRadius = (int) (d / 2 * littleCircleRadisSize);
        littleCircleRadisSize = (int) ((d / 11) );
        radiousOfBigCircleWithBorder= this.radiousOfBigCircle+8;

    }
    public double distance(float x,float y,float centerx ,float centery){
        double d = Math.sqrt((x - centerx) * (x - centerx) + (y - centery) *(y - centery) );
        return d;
    }

    public double getX(float x ) {
        double distanceXfromCenter=distance(x,0,this.anotherStratXLittleCIrcle ,0);

        if(x<this.anotherStratXLittleCIrcle){
            distanceXfromCenter=distanceXfromCenter*(-1);
        }

        distanceXfromCenter=(distanceXfromCenter/this.radiousOfBigCircle);
return distanceXfromCenter;


    }

    public double getY(float y ) {

        double distanceYfromCenter=distance(0,y,0 ,this.anotherStratYLittleCIrcle);

        if(y<this.anotherStratYLittleCIrcle){
            distanceYfromCenter=distanceYfromCenter*(-1);
        }
        distanceYfromCenter=(distanceYfromCenter/this.radiousOfBigCircle);

return    distanceYfromCenter;
    }
/*public void sendinfo(float x ,float y){
double distanceXfromCenter=distance(x,0,this.anotherStratXLittleCIrcle ,0);
    double distanceYfromCenter=distance(0,y,0 ,this.anotherStratYLittleCIrcle);
    if(x<this.anotherStratXLittleCIrcle){
        distanceXfromCenter=distanceXfromCenter*(-1);
    }
    if(y<this.anotherStratYLittleCIrcle){
        distanceYfromCenter=distanceYfromCenter*(-1);
    }
    distanceYfromCenter=(distanceYfromCenter/this.radiousOfBigCircle);
    distanceXfromCenter=(distanceXfromCenter/this.radiousOfBigCircle);
    Log.d("TheJoystick","Ymove "+distanceYfromCenter);
    Log.d("TheJoystick","Xmove "+distanceXfromCenter);
   if(s1.mystate==1){
        Runnable r1 = new MyThreadPool.sendData(c111,distanceXfromCenter,distanceYfromCenter,s1);

        pool.execute(r1);}
}*/


}

