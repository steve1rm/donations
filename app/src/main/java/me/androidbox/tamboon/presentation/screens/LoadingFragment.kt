package me.androidbox.tamboon.presentation.screens

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import javax.inject.Inject
import kotlinx.android.synthetic.main.inprogress.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import timber.log.Timber

class LoadingFragment : Fragment() {
    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

    private val rotationAnimator: Animator by lazy<Animator> {
        AnimatorInflater.loadAnimator(activity, R.animator.loading_progress)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.inprogress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rotationAnimator.setTarget(ivProgress)
        startLoading()

        val navController = Navigation.findNavController(view)

        arguments?.let { bundle ->
            if (bundle.isEmpty) {
                registerForCharities(navController)
                tamboonViewModel.getListOfCharities()
            } else {
                val donation = LoadingFragmentArgs.fromBundle(bundle).donation
                registerForDonations(navController)
                tamboonViewModel.submitDonation(donation)
            }
        }
    }

    private fun registerForCharities(navController: NavController) {
        tamboonViewModel.registerForCharities().observe(viewLifecycleOwner, Observer {
            stopLoading()
            Timber.d("Charities ${it.charityList}")
            if (it.charityList.isNotEmpty()) {
                val navDirection =
                        LoadingFragmentDirections.actionLoadingFragmentToCharitiesFragment(it.charityList.toTypedArray())
                navController.navigate(navDirection)
            } else {
                gotoHomeMenu(navController)
            }
        })
    }

    private fun registerForDonations(navController: NavController) {
        tamboonViewModel.registerForDonations().observe(viewLifecycleOwner, Observer {
            stopLoading()
            Timber.d("DonationResult $it")
            if (it.isSuccess && it.errorCode.isNotEmpty() && it.errorMessage.isNotEmpty()) {
                navController.navigate(R.id.action_loadingFragment_to_successFragment)
            } else {
                gotoHomeMenu(navController)
            }
        })
    }

    private fun gotoHomeMenu(navController: NavController) {
        navController.navigate(R.id.action_loadingFragment_to_homeMenuFragment)
    }

    private fun startLoading() {
        if (!rotationAnimator.isRunning) {
            rotationAnimator.start()
        }
    }

    private fun stopLoading() {
        if (rotationAnimator.isRunning) {
            rotationAnimator.end()
        }
    }

    override fun onDestroyView() {
        stopLoading()

        super.onDestroyView()
    }

    private fun injectDependencies() {
            getTamboonApplicationComponent()
                .fragmentSubcomponent(FragmentModule(this))
                .inject(this)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (requireActivity().application as TamboonApplication).tamboonApplicationComponent
    }
}
