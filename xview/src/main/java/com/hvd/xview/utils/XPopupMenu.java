package com.hvd.xview.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;


public class XPopupMenu<T> extends PopupMenu {

    private T item;
    private PopupListener popupListener;

    public XPopupMenu(Context context, View anchor, @MenuRes int menuRes, final T item, @NotNull final PopupListener popupListener) {
        super(context, anchor);
        this.item = item;
        getMenuInflater().inflate(menuRes, getMenu());
        this.popupListener = popupListener;
        setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (popupListener != null){
                    popupListener.onMenuClick(XPopupMenu.this, menuItem, item);
                }
                return true;
            }
        });
    }

    public static <V> XPopupMenu createNew(Activity activity, @MenuRes int menuRes, View anchor, @Nullable V item, @NotNull PopupListener popupListener) {
        XPopupMenu popup;
        popup = new XPopupMenu(activity, anchor, menuRes, item, popupListener);
        return popup;
    }

    public interface PopupListener<T> {
        void onMenuClick(PopupMenu popup, MenuItem menuItem, T item);
    }
}
