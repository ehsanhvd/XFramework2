package com.hvd.xframework

import com.tpa.formbuilder.annotation.*
import com.tpa.formbuilder.validator.MandatoryValidator

data class Person(
    @Order(0)
    @Input(displayName = "name and family hint", validator = MandatoryValidator::class)
    var name: String,

    @Order(1)
    @IranTelInput(displayName = "Tel", mandatory = true)
    var tel: String = "",

    @Order(2)
    @DatePicker(displayName = "birthday")
    var birthDay: Long = 0,

    @Order(3)
    @DateTimePicker(displayName = "buyDate")
    var buyDate: Long = 0,

    @Order(4)
    @EmailInput(displayName = "email")
    var email: String = "",

    @WrapContent
    @Order(5)
    @KeepRow
    @CheckBox(displayName = "subscribe news")
    var subscribed: Boolean = true,

    @Order(6)
    @Spinner(displayName = "gender", itemsArray =  R.array.gender)
    var gender: Int = 1,

    @Order(6)
    @RadioButton(displayName = "gender", itemsArray =  R.array.inviteType)
    var inviteType: Int = 0
    ) {
}