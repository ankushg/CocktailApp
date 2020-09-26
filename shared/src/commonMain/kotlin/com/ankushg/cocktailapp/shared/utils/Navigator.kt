package com.ankushg.cocktailapp.shared.utils

class Navigator<T> private constructor(
    initialBackStack: List<T>,
) {
    constructor(initial: T) : this(
        listOf(initial)
    )

    constructor() : this(emptyList())

    private val backStack = initialBackStack.toMutableList()
    val current: T? get() = backStack.lastOrNull()

    fun back(): T? {
        if (canGoBack()) backStack.removeLast()
        return current
    }

    fun navigateTo(destination: T): T {
        backStack += destination
        return destination
    }

    fun replaceCurrent(destination: T): T {
        backStack[backStack.lastIndex] = destination
        return destination
    }

    private fun canGoBack(): Boolean = backStack.size > 1
}