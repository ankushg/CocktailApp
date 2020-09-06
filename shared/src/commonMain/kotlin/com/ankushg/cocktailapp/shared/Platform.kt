package com.ankushg.cocktailapp.shared

expect fun currentTimeMillis(): Long

internal expect fun printThrowable(t: Throwable)
