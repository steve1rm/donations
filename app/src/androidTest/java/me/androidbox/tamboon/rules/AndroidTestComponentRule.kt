package me.androidbox.tamboon.rules

import android.content.Context
import me.androidbox.tamboon.di.AndroidTestApplicationModule
import me.androidbox.tamboon.di.AndroidTestComponent
import me.androidbox.tamboon.di.DaggerAndroidTestComponent
import me.androidbox.tamboon.di.TamboonApplication
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AndroidTestComponentRule(private val context: Context) : TestRule {

    private var androidTestComponent: AndroidTestComponent? = null

    fun getContext() = context

    private fun setupTestDependencies() {
        val application = context.applicationContext as TamboonApplication
        androidTestComponent = DaggerAndroidTestComponent
            .builder()
            .androidTestApplicationModule(AndroidTestApplicationModule(application))
            .build()

        application.tamboonApplicationComponent = androidTestComponent!!
    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    setupTestDependencies()
                    base?.evaluate()
                } finally {
                    androidTestComponent = null
                }
            }
        }
    }
}
