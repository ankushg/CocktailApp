package com.ankushg.cocktailapp.shared

actual fun currentTimeMillis(): Long = System.currentTimeMillis()

internal actual fun printThrowable(t: Throwable) {
    t.printStackTrace()
}
