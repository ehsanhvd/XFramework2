package com.tpa.formbuilder.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Order(val order: Int) {
}