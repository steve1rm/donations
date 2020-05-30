package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_charities.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.di.CharitiesFragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import org.parceler.Parcels
import timber.log.Timber
import javax.inject.Inject

class CharitiesFragment : Fragment() {

    @Inject
    lateinit var charitiesAdapter: CharitiesAdapter

    @Inject
    lateinit var charitySelectedListener: CharitySelectedListener

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

        arguments?.let {
            val charities =
                Parcels.unwrap<List<Charity>>(it.getParcelable(TamboonActivity.TAMBOON_CHARITY_KEY))

            rvCharities.adapter = charitiesAdapter
            rvCharities.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            rvCharities.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
            charitiesAdapter.populate(charities)
            charitiesAdapter.setSelectedCharity(::onCharityClicked)
            charitiesAdapter.notifyDataSetChanged()
        }
    }

    private fun onCharityClicked(charity: Charity) {
        Timber.d("charity: ${charity.name}")
        charitySelectedListener.onCharitySelected(charity)
    }

    private fun injectDependencies() {
        val charitiesFragmentSubcomponent = getTamboonApplicationComponent()
            .charitiesFragmentSubcomponent(CharitiesFragmentModule(this@CharitiesFragment))

        charitiesFragmentSubcomponent.inject(this@CharitiesFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        /* Something bad happened !! we should not proceed */
        return (activity!!.application as TamboonApplication).tamboonApplicationComponent
    }
}
