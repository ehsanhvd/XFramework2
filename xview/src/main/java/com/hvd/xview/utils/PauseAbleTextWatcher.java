package com.hvd.xview.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class PauseAbleTextWatcher implements TextWatcher {
    private boolean pause;
    private EditText editText;

    public PauseAbleTextWatcher(EditText editText) {
        this.editText = editText;
    }

    public void pause(){
        pause = true;
    }

    public void unPause(){
        pause = false;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (pause){
            return;
        }
        afterAllowedTextChanged(s);
    }

    protected abstract void afterAllowedTextChanged(Editable s);

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    public void setTextWithoutWatcher(String text){
        pause();
        editText.setText(text);
        unPause();
    }

    public EditText getEditText() {
        return editText;
    }
}
