package com.hvd.xview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.hvd.xview.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerString extends CustomSpinner<String> {

    private String text;

    public CustomSpinnerString(@NotNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomSpinnerString(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomSpinnerString(@NotNull Context context, @NotNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomSpinnerString, defStyleAttr, 0);

        CharSequence[] items = ta.getTextArray(R.styleable.CustomSpinnerString_android_entries);
        text = ta.getString(R.styleable.CustomSpinnerString_android_text);
        if (text == null){
            text = getContext().getString(R.string.chooseOne);
        }

        if (items != null){

            List<String> stringItems = new ArrayList<>();

            for (CharSequence charSequence : items) {
                stringItems.add(charSequence.toString());
            }

            set(stringItems);
        }
        ta.recycle();
    }

    @Override
    public void set(@NotNull List<? extends String> items) {
        List<String> stringItems = new ArrayList<>();
        stringItems.add("[ " + text + " ]");

        for (CharSequence charSequence : items) {
            stringItems.add(charSequence.toString());
        }
        super.set(stringItems);
    }
}
