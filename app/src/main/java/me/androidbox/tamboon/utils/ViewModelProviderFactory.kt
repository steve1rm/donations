package me.androidbox.tamboon.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException
import kotlin.reflect.KClass

class ViewModelProviderFactory<T: ViewModel>(
    private val kClass: KClass<T>,
    private val creator: () -> T) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(modelClass)) {
            return creator() as T
        }
        else {
            throw IllegalStateException("Cannot create ViewModel from class $modelClass")
        }
    }
}
