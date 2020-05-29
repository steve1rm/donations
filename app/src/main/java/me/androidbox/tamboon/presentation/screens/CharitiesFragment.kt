package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.androidbox.tamboon.R
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.di.CharitiesFragmentModule
import timber.log.Timber
import javax.inject.Inject

class CharitiesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_charities, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun injectDependencies() {
        val charitiesFragmentSubcomponent = getTamboonApplicationComponent()
            .charitiesFragmentSubcomponent(CharitiesFragmentModule(this@CharitiesFragment))

        charitiesFragmentSubcomponent.inject(this@CharitiesFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        /* Something bad happened !! */
        return (activity!!.application as TamboonApplication).tamboonApplicationComponent
    }
}
