package me.androidbox.tamboon.presentation.utils

import java.util.*

object MockData {
    fun getInt(): Int = (1..10000).shuffled().first()

    fun getString(): String = UUID.randomUUID().toString()

    fun getBoolean(): Boolean = (0..1).shuffled().first() == 1
}
