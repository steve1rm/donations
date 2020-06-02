package me.androidbox.tamboon.utils

object Untilites {

    fun loadFromResources(path: String): String {
        this.javaClass.classLoader?.let {
            val stream = it.getResource(path)
            return String(stream.readBytes())
        } ?: run {
            return ""
        }
    }
}