package com.hvd.xframework;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hvd.xview.utils.XPopupMenu;
import com.hvd.xview.view.GlideImageView;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.DatePickerListener;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.DateTimePickerListener;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDatePicker;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDateTimePicker;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

//    private XImagePicker picker;


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

//        FormGeneratorHelper.INSTANCE.generateManual(this, (LinearLayout) findViewById(R.id.linFormParent));
        FormGeneratorHelper.INSTANCE.generateAuto(this, (LinearLayout) findViewById(R.id.linFormParent));
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
