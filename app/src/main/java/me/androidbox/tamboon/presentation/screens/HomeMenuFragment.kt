package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_homemenu.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.databinding.FragmentHomemenuBinding
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.screens.listeners.FetchCharitiesListener
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import org.parceler.Parcels
import timber.log.Timber
import javax.inject.Inject

class HomeMenuFragment : Fragment() {

    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

    @Inject
    lateinit var fetchCharitiesListener: FetchCharitiesListener

    private lateinit var binding: FragmentHomemenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomemenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(view)

   //     registerForCharities(navController)

        binding.btnFetchCharities.setOnClickListener {
            tapFetchCharities(navController)
        }
    }

    private fun tapFetchCharities(navController: NavController) {
        navController.navigate(R.id.action_homeMenuFragment_to_loadingFragment)
     //   tamboonViewModel.getListOfCharities()

    }

    private fun registerForCharities(navController: NavController) {
        tamboonViewModel.registerForCharities().observe(viewLifecycleOwner, Observer {
            Timber.d("Charities ${it.charityList}")
            if(it.charityList.isNotEmpty()) {
             //   val navDirection = HomeMenuFragmentDirections.actionHomeMenuFragmentToCharitiesFragment(it.charityList.toTypedArray())
            //    navController.navigate(navDirection)
                //        displayListOfCharities(it.charityList)
            }
            else {
                Toast.makeText(requireContext(), "Failed to retrieve charities", Toast.LENGTH_LONG).show()
                //    startHomeMenu()
            }
        })
    }

    private fun injectDependencies() {
        val fragmentSubcomponent = getTamboonApplicationComponent()
            .fragmentSubcomponent(FragmentModule(this@HomeMenuFragment))

        fragmentSubcomponent.inject(this@HomeMenuFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (requireActivity().application as TamboonApplication).tamboonApplicationComponent
    }
}
