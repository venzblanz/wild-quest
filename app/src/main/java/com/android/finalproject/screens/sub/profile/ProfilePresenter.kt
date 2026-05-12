package com.android.finalproject.screens.sub.profile

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.launch

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val profileModel: ProfileModel,
    private val lifecycleScope: LifecycleCoroutineScope
) : ProfileContract.Presenter {

    override fun navToHome() {
        view.navigateToHome()
    }

    override fun setProfileID() {
        if (!profileModel.getUser().username.isNullOrEmpty()) {
            view.setProfileID("${profileModel.getUser().username}")
        }
    }

    override fun setEmail() {
        if (!profileModel.getUser().email.isNullOrEmpty()) {
            view.setEmail("${profileModel.getUser().email}")
        }
    }

    override fun loadStats() {
        val userId = profileModel.getUser().username.toIntOrNull() ?: 0

        lifecycleScope.launch {
            val count    = profileModel.getQuizCount(userId)
            val total    = profileModel.getTotalScore(userId)
            val best     = profileModel.getBestScore(userId)
            val accuracy = profileModel.getAccuracy(userId)

            view.setQuizCount(count.toString())
            view.setTotalScore(total.toString())
            view.setBestScore(best.toString())
            view.setAccuracy(accuracy)
        }
    }
}