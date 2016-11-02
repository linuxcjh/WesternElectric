package com.nuoman.westernele.common.utils;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

/**
 * 相机功能的单例，为确保操作的camera对象是同一个
 * Created by 杨小过 on 2016/5/25.
 */
public class CameraUtil {

    private static final String TAG = "camera";
    private static CameraUtil mCameraUtil;
    private Camera mCamera;
    private boolean isPreview = false;

    private CameraUtil() {

    }

    /**
     * 相机开启后的回调
     */
    public interface CameraOpenedCallback {
        void cameraHasOpened();
    }

    public static synchronized CameraUtil getInstance() {
        if (mCameraUtil == null)
            mCameraUtil = new CameraUtil();
        return mCameraUtil;
    }

    /**
     * 打开camera
     *
     * @param callback 相机开启后的回调函数
     */
    public void doOpenCamera(CameraOpenedCallback callback) {
        Log.d(TAG, "camera open...");
        mCamera = Camera.open(0);
        Log.d(TAG, "camera open over");
        callback.cameraHasOpened();
    }

    /**
     * 开启预览
     *
     * @param holder 开启预览所用的holder
     */
    public void doStartPreview(SurfaceHolder holder) {
        if (isPreview) {
            mCamera.stopPreview();
        }
        if (mCamera != null) {
            Camera.Parameters mParams = mCamera.getParameters();
            //调试使用，打印相机的相关参数
            logSupportPreviewSize(mParams);
            logSupportPictureSize(mParams);
            logSupportFocusMode(mParams);

            mParams.setPictureFormat(PixelFormat.JPEG);
            //设置预览尺寸和图片尺寸
            mParams.setPictureSize(640, 480);
            mParams.setPreviewSize(640, 480);
            mParams.setPreviewFpsRange(5, 10);
            mCamera.setParameters(mParams);


            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();
                isPreview = true;
                Log.d(TAG, "doStartPreview...");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 停止预览，释放Camera
     */
    public void doStopCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreview = false;
            mCamera.release();
            mCamera = null;
            Log.d(TAG,"stopCamera");
        }
    }


    /**
     * 拍照
     *
     * @param callback 拍照完成后对图片的回调
     */
    public void doTakePicture(Camera.PictureCallback callback) {
        if (isPreview && mCamera != null) {
            mCamera.takePicture(null, null, callback);
        }
    }



    /**
     * 打印支持的预览尺寸
     *
     * @param params 相机参数
     */
    public static void logSupportPreviewSize(Camera.Parameters params) {
        List<Camera.Size> previewSizes = params.getSupportedPreviewSizes();
        for (Camera.Size previewSize : previewSizes)
            Log.d(TAG, "previewSize Width:" + previewSize.width + "height:" + previewSize.height);

    }

    /**
     * 打印支持的图片尺寸
     *
     * @param params 相机参数
     */
    public static void logSupportPictureSize(Camera.Parameters params) {
        List<Camera.Size> pictureSizes = params.getSupportedPictureSizes();
        for (Camera.Size pictureSize : pictureSizes)
            Log.d(TAG, "pictureSize Width:" + pictureSize.width + "height:" + pictureSize.height);
    }

    /**
     * 打印支持的聚焦模式
     *
     * @param params 相机参数
     */
    public static void logSupportFocusMode(Camera.Parameters params) {
        List<String> focusModes=params.getSupportedFocusModes();
        for (String focusMode:focusModes)
            Log.d(TAG,"聚焦模式："+focusMode);
    }

}
