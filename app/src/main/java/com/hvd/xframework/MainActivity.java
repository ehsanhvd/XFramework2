package com.hvd.xframework;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.DatePickerListener;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.DateTimePickerListener;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDatePicker;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.datecontroller.XDateTimePicker;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.btnDatePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                XDateTimePicker xDateTimePicker = new XDateTimePicker(MainActivity.this);
                xDateTimePicker.showDateTimePicker(new DateTimePickerListener() {
                    @Override
                    public void onDateTimePicked(@NotNull XDateTimePicker xDateTimePicker, int year, int month, int day, int hour, int min) {

                    }
                });
            }
        });
    }
}
