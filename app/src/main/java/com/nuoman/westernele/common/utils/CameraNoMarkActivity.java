package com.nuoman.westernele.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.nuoman.westernelectric.R;

import java.io.FileOutputStream;

/**
 * @FILE : CameraWaterMarkActivity.java
 * @AUTHOR: 唐磊 CREAT AT 2015-1-8 上午下午2:18:31
 * @NOTE :[水印相机]
 */
public class CameraNoMarkActivity extends Activity implements
        OnClickListener, Callback, AutoFocusCallback {

    private SurfaceView surfaceView;
    private ImageButton button;
    private SurfaceHolder holder;
    private Camera camera;
    private TextView addr_tv, time_tv;
    private FrameLayout frameLayout;
    private String timeTitle;
    private Time t;
    private boolean isPhotograph;
    private int W, H, imageH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_water_camera_layout);
        init();

    }

    private void init() {
        button = (ImageButton) findViewById(R.id.button1);
        button.setOnClickListener(this);
        addr_tv = (TextView) findViewById(R.id.camer_addr);
        time_tv = (TextView) findViewById(R.id.camer_time);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 设置类型
        t = new Time();
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        W = mDisplayMetrics.widthPixels;
        H = mDisplayMetrics.heightPixels;

    }

    private void setParameter() {
        LayoutParams lp = (LayoutParams) frameLayout.getLayoutParams();
        imageH = lp.height = 640 * W / 480;
        lp.width = W;
        frameLayout.setLayoutParams(lp);

    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        try {
            Camera.Parameters params = camera.getParameters();
            params.setPictureFormat(PixelFormat.JPEG);
            params.setPictureSize(640, 480);// 设置保存图片大小
            params.setPreviewSize(640, 480);// 设置预览图片大小
            setParameter();
            camera.setParameters(params);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (NullPointerException e) {
        } catch (Exception e) {
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        try {
            camera = Camera.open();
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
            CameraNoMarkActivity.this.finish();

        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        try {
            camera.stopPreview();
            camera.release();
            camera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ShutterCallback shutter = new ShutterCallback() {

        @Override
        public void onShutter() {

        }

    };

    private PictureCallback jpeg = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.setRotate(90);
            bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(),
                    matrix, true);
            String pathName = getIntent().getStringExtra("path");
            isPhotograph = true;
            addWaterMarkBitmap(bm, pathName);
            setResult(RESULT_OK, new Intent());
            finish();
        }

    };

    public void addWaterMarkBitmap(Bitmap bm, String pathName) {
        int h = bm.getHeight();
        int w = bm.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.NORMAL);
        p.setColor(Color.WHITE);
        p.setTypeface(font);
        float bi = (float) h / (float) imageH;
        p.setTextSize(addr_tv.getTextSize() * bi);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));
        canvas.drawBitmap(bm, 0, 0, p);
        if (!TextUtils.isEmpty(addr_tv.getText())) {
            if (addr_tv.getLineCount() == 2) {
                Layout layout = addr_tv.getLayout();
                int aLineOfWords = layout.getLineEnd(0);// 0第一行
                // 返回指定行中最后一个字在整个字符串中的位置
                String str1 = addr_tv.getText().toString()
                        .substring(0, aLineOfWords);
                String str2 = addr_tv.getText().toString()
                        .substring(aLineOfWords);
                canvas.drawText(str1, addr_tv.getLeft() * bi,
                        (addr_tv.getTop() + addr_tv.getTextSize()) * bi, p);
                canvas.drawText(
                        str2,
                        addr_tv.getLeft() * bi,
                        (addr_tv.getTop() + 2 * addr_tv.getTextSize() + addr_tv
                                .getTextSize() / 5) * bi, p);
            } else {
                canvas.drawText(addr_tv.getText().toString(), addr_tv.getLeft()
                                * bi, (addr_tv.getTop() + addr_tv.getTextSize()) * bi,
                        p);
            }

        }
        if (!TextUtils.isEmpty(time_tv.getText())) {
            canvas.drawText(time_tv.getText().toString(), time_tv.getLeft()
                    * bi, (time_tv.getTop() + time_tv.getTextSize()) * bi, p);
        }
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(pathName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                stream.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

    private PictureCallback raw = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
        }
    };

    @Override
    public void onClick(View arg0) {
        arg0.setEnabled(false);
        camera.autoFocus(this);
    }

    @Override
    public void onAutoFocus(boolean arg0, Camera arg1) {
        camera.takePicture(shutter, raw, jpeg);

    }



    /**
     * 动态计算字体大小
     */
    private void setPaintByMeasureText() {
        TextPaint mTextPaint = addr_tv.getPaint();
        String text = addr_tv.getText().toString();
        float textWidth = mTextPaint.measureText(text);
        int maxTextWidth = W - 2 * Utils.dip2px(this, 10);
        if (textWidth > maxTextWidth) {
            int length = text.length();
            int wordWidth = 2 * maxTextWidth / length;
            int sp = Utils.px2sp(CameraNoMarkActivity.this, wordWidth);
            sp = sp > 15 ? 15 : sp;
            addr_tv.setTextSize(sp);
            time_tv.setTextSize(sp);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
