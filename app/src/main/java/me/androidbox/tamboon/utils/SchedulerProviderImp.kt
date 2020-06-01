package me.androidbox.tamboon.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

class SchedulerProviderImp : SchedulerProvider {

    override fun ui(): Scheduler =
        AndroidSchedulers.mainThread()

    override fun background(): Scheduler =
        Schedulers.io()

    override fun testScheduler(): Scheduler =
        TestScheduler()
}
