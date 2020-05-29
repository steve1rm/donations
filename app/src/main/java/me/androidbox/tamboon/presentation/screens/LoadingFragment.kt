package me.androidbox.tamboon.presentation.screens

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.inprogress.*
import me.androidbox.tamboon.R

class LoadingFragment : Fragment() {
    private val rotationAnimator: Animator by lazy<Animator> {
        AnimatorInflater.loadAnimator(activity, R.animator.loading_progress)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.inprogress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotationAnimator.setTarget(ivProgress)
        startLoading()
    }

    private fun startLoading() {
        if(!rotationAnimator.isRunning) {
            rotationAnimator.start()
        }
    }

    private fun stopLoading() {
        if(rotationAnimator.isRunning) {
            rotationAnimator.end()
        }
    }

    override fun onDestroyView() {
        stopLoading()
        super.onDestroyView()
    }
}
