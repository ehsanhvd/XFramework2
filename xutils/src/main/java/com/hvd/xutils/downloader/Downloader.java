package com.hvd.xutils.downloader;

import android.Manifest;
import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.hvd.xutils.utils.XUtils;

import org.jetbrains.annotations.Nullable;

import java.io.File;

public class Downloader {

    private static final int REQ_WRITE_PERM = 742;
    private AppCompatActivity activity;
    private String urlToDownload;

    public Downloader(AppCompatActivity activity){
        this.activity = activity;
    }

    public void requestDownload(@Nullable String url) {
        if (url == null || url.isEmpty()){
            return;
        }

        urlToDownload = url;
        if (!hasWritePerm(activity)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_WRITE_PERM);
            return;
        }

        startDownload(activity.getApplication(), urlToDownload);
    }

    private boolean hasWritePerm(AppCompatActivity appCompatActivity) {
        return XUtils.hasPermissions(appCompatActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void onRequestPermissionsResult(int requestCode) {
        if (hasWritePerm(activity)) {
            if (requestCode == REQ_WRITE_PERM) {
                startDownload(activity.getApplication(), urlToDownload);
            }
        }
    }

    private void startDownload(Application application,  @NonNull String url){

        String fileName = extractLastSegment(url);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(fileName);
        request.setMimeType("*/*");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        DownloadManager manager = (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    private String extractLastSegment(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }

    @Nullable
    public static Uri onReceive(Context context, Intent intent) {
        DownloadManager dm = ((DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE));
        DownloadManager.Query query = new DownloadManager.Query();
        long dlId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        if (dlId == -1) {
            return null;
        }
        query.setFilterById(dlId);

        Cursor cursor = dm.query(query);
        if (cursor.moveToFirst()){
            String localUriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
            Uri localUri = Uri.parse(localUriString);

            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(localUri.getPath()));
        }
        return null;
    }
}
