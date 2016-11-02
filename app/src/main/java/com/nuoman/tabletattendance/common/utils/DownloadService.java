package com.nuoman.tabletattendance.common.utils;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.nuoman.tabletattendance.common.NuoManConstant;

import java.io.File;

public class DownloadService extends Service {
    private DownloadManager dm;
    private long enqueue;
    private BroadcastReceiver receiver;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                File file = new File(Environment.getExternalStorageDirectory() + "/download/myApp.apk");
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
                startActivity(intent);
                stopSelf();
            }
        };

        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        startDownload();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void startDownload() {
        File file = new File(Environment.getExternalStorageDirectory() + "/download/myApp.apk");
        if (file.exists()) {
            file.delete();
        }

        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        DownloadManager.Request request = new DownloadManager.Request(
//                Uri.parse("http://d.koudai.com/com.koudai.weishop/1000f/weishop_1000f.apk"));
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(AppConfig.getStringConfig(NuoManConstant.DOWNLOAD_URL, "")));

        request.setMimeType("application/vnd.android.package-archive");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "myApp.apk");
        enqueue = dm.enqueue(request);
    }
}