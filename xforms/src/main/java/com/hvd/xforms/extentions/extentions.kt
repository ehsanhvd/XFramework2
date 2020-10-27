package com.hvd.xforms.extentions

//not null
fun <T> NN(any: T?, op: ((it: T) -> Any)) {
    if (any != null) {
        op(any)
    }
}

