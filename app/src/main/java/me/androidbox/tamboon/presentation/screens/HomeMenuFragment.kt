package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_homemenu.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.screens.listeners.FetchCharitiesListener
import javax.inject.Inject

class HomeMenuFragment : Fragment() {

    @Inject
    lateinit var fetchCharitiesListener: FetchCharitiesListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_homemenu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFetchCharities.setOnClickListener {
            fetchCharitiesListener.onFetchCharities()
        }
    }

    private fun injectDependencies() {
        val fragmentSubcomponent = getTamboonApplicationComponent()
            .fragmentSubcomponent(FragmentModule(this@HomeMenuFragment))

        fragmentSubcomponent.inject(this@HomeMenuFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        /* Something bad happened !! we should not proceed */
        return (activity!!.application as TamboonApplication).tamboonApplicationComponent
    }

}
