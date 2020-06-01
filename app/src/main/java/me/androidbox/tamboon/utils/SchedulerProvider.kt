package me.androidbox.tamboon.utils

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler
    fun background(): Scheduler
    fun testScheduler(): Scheduler
}
