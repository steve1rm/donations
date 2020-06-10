package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import me.androidbox.tamboon.R
import me.androidbox.tamboon.databinding.FragmentHomemenuBinding
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import javax.inject.Inject

class HomeMenuFragment : Fragment() {

    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

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

        binding.btnFetchCharities.setOnClickListener {
            tapFetchCharities(navController)
        }
    }

    private fun tapFetchCharities(navController: NavController) {
        navController.navigate(R.id.action_homeMenuFragment_to_loadingFragment)
    }

    private fun injectDependencies() {
        getTamboonApplicationComponent()
            .fragmentSubcomponent(FragmentModule(this@HomeMenuFragment))
            .inject(this@HomeMenuFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (requireActivity().application as TamboonApplication).tamboonApplicationComponent
    }
}
