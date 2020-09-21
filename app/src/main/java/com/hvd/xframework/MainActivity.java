package com.hvd.xframework;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hvd.xcustomview.view.GlideImageView;
import com.hvd.xview.utils.XImagePicker;
import com.hvd.xview.utils.XPopupMenu;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.DatePickerListener;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.DateTimePickerListener;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDatePicker;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDateTimePicker;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.btnDateTimePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });

        ((GlideImageView) findViewById(R.id.circleImageView)).setImageResource(R.mipmap.ic_launcher);
        ((GlideImageView) findViewById(R.id.circleImageView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new XImagePicker(MainActivity.this).startWithActivity(new XImagePicker.ImagePickerListener() {
                    @Override
                    public void onImagePicked(@NotNull Uri uri) {

                    }
                });
            }
        });
    }

    private void showMenu() {
        XPopupMenu<String> xPopupMenu = new XPopupMenu<>(MainActivity.this, findViewById(R.id.btnDateTimePicker), R.menu.date_time, "", new XPopupMenu.PopupListener<String>() {
            @Override
            public void onMenuClick(PopupMenu popup, MenuItem menuItem, String item) {
                switch (menuItem.getItemId()) {
                    case R.id.menuDate:
                        showDatePicker();
                        break;
                    case R.id.menuTime:
                        showDateTimePicker();
                        break;
                }
            }
        });
        xPopupMenu.show();
    }

    private void showDatePicker() {
        XDatePicker xDatePicker = new XDatePicker(MainActivity.this);
        xDatePicker.showDatePicker(new DatePickerListener() {
            @Override
            public void onDatePicked(@NotNull DatePickerDialog datePickerDialog, int year, int month, int day) {

            }
        });
    }

    private void showDateTimePicker() {
        XDateTimePicker xDateTimePicker = new XDateTimePicker(MainActivity.this);
        xDateTimePicker.showDateTimePicker(new DateTimePickerListener() {
            @Override
            public void onDateTimePicked(@NotNull XDateTimePicker xDateTimePicker, int year, int month, int day, int hour, int min) {

            }
        });
    }
}
