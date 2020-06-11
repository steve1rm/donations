package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.databinding.FragmentCharitiesBinding
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import timber.log.Timber
import javax.inject.Inject

class CharitiesFragment : Fragment() {

    @Inject
    lateinit var charitiesAdapter: CharitiesAdapter

    private lateinit var binding: FragmentCharitiesBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharitiesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        arguments?.let {
            if(!it.isEmpty) {
                val charities = CharitiesFragmentArgs.fromBundle(it).charityList.toList()

                binding.rvCharities.adapter = charitiesAdapter
                binding.rvCharities.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.rvCharities.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))

                charitiesAdapter.populate(charities)
                charitiesAdapter.setSelectedCharity(::onCharityClicked)
                charitiesAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onCharityClicked(charity: Charity) {
        Timber.d("charity: ${charity.name}")
        val navDestination =
            CharitiesFragmentDirections.actionCharitiesFragmentToDonationFragment(charity)
        navController.navigate(navDestination)
    }

    override fun onDestroyView() {
        /* Clear the list as the CharitiesFragmentArgs still contains the charities and
         * will continue to add duplicates. Find a better way to avoid this */
        charitiesAdapter.clearCharitiesList()
        super.onDestroyView()
    }

    private fun injectDependencies() {
        getTamboonApplicationComponent()
            .fragmentSubcomponent(FragmentModule(this@CharitiesFragment))
            .inject(this@CharitiesFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (requireActivity().application as TamboonApplication).tamboonApplicationComponent
    }
}
