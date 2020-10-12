package com.tpa.formbuilder.validator

import android.widget.EditText
import com.hvd.xforms.Validator
import com.hvd.xutils.utils.ValidationUtils

class IranNationalCodeValidator: Validator {
    override fun isValid(editText: EditText): Boolean {
        return ValidationUtils.isValidNationalCode(editText.text.toString())
    }
}