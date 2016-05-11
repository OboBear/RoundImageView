package com.me.obo.circleboardimage.XferModeView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by obo on 16/5/11.
 * Email:obo1993@gmail.com
 * Git:https://github.com/OboBear
 * Blog:http://blog.csdn.net/leilba
 */
public class XferModeView extends View {

    public XferModeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Paint paint = new Paint();
        mResultBitmap = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
        mDestBitmap = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
        mSrcBitmap = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
        mDestCanvas = new Canvas(mDestBitmap);
        paint.setColor(Color.RED);
        mDestCanvas.drawCircle(40,40,40,paint);

        mSrcCanvas = new Canvas(mSrcBitmap);
        paint.setColor(Color.BLUE);
        mSrcCanvas.drawCircle(60,60,40,paint);

        mResultCanvas = new Canvas(mResultBitmap);

    }

    Bitmap mDestBitmap;
    Bitmap mSrcBitmap;
    Bitmap mResultBitmap;
    Canvas mDestCanvas;
    Canvas mSrcCanvas;
    Canvas mResultCanvas;

    Paint xferPaint = new Paint();
    Paint nomalPaint = new Paint();

    private  PorterDuff.Mode [] modes = {
            PorterDuff.Mode.CLEAR,
            PorterDuff.Mode.ADD,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.LIGHTEN,

            PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.OVERLAY,
            PorterDuff.Mode.DST,
            PorterDuff.Mode.DST_ATOP,

            PorterDuff.Mode.DST_IN,
            PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.SRC,

            PorterDuff.Mode.SRC_ATOP,
            PorterDuff.Mode.SRC_IN,
            PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.SRC_OVER,

    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        nomalPaint.setTextSize(60);
        canvas.drawBitmap(mDestBitmap, 120 ,100 , nomalPaint);

        canvas.drawText("+",230,170,nomalPaint);


        canvas.drawBitmap(mSrcBitmap, 280 ,100 , nomalPaint);


        canvas.drawText("=  ?",400,170,nomalPaint);


        for (int i=0;i<modes.length;i++) {
//            mResultCanvas.drawColor(Color.rgb(200,200,200));
            mResultCanvas.drawColor(Color.WHITE);
            xferPaint.setXfermode(new PorterDuffXfermode(modes[i]));
            mResultCanvas.drawBitmap(mDestBitmap, 0, 0, nomalPaint);
            mResultCanvas.drawBitmap(mSrcBitmap, 0, 0, xferPaint);
            canvas.drawBitmap(mResultBitmap, 120 + 140 * (i % 4),250 + (i / 4 ) * 140, nomalPaint);
        }



    }
}
