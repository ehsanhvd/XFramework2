package com.hvd.xutils.utils;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.text.DecimalFormat;

public class XUtils {

    public static int dpToPx(Application application, int dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, application.getResources().getDisplayMetrics());
        return (int) px;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Long getLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String getFormattedNo(long amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String getLastCopiedText(Application application) {
        ClipboardManager clipboard = (ClipboardManager) application.getSystemService(Context.CLIPBOARD_SERVICE);
        // If it does contain data, decide if you can handle the data.
        if (!(clipboard.hasPrimaryClip())) {
            return null;
        } else if (!(clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
            // since the clipboard has data but it is not plain text
            return null;
        } else {
            //since the clipboard contains plain text.
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            // Gets the clipboard as text.
            return item.getText().toString();
        }
    }


    public static boolean shareImage(Context context, @Nullable String path) {
        if (path == null) {
            return false;
        }
        try {
            File imageFile = new File(path);
            Uri uri = Uri.fromFile(imageFile);

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/*");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            context.startActivity(Intent.createChooser(share, "Share Image"));

            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean viewImage(Context context, @Nullable String url) {
        if (url == null) {
            return false;
        }
        try {
            File file = new File(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean openUrl(Context context, @Nullable String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void shareText(Context context, @Nullable String text) {
        if (text == null) {
            return;
        }
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");

            context.startActivity(Intent.createChooser(sendIntent, "ارسال"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Typeface getTypeface(Context context, String path) {
        return Typeface.createFromAsset(context.getAssets(), path);
    }
}
