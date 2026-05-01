package com.android.finalproject.screens.sub.profile

class ProfilePresenter(private val view: ProfileContract.View, private val profileModel: ProfileModel) :
    ProfileContract.Presenter {
    override fun navToHome() {
        view.navigateToHome()
    }

    override fun setProfileID() {
        if(!profileModel.getUser().username.isNullOrEmpty()){
            view.setProfileID("${profileModel.getUser().username}")
        }
    }

    override fun setEmail() {
        if(!profileModel.getUser().email.isNullOrEmpty()){
            view.setEmail("${profileModel.getUser().email}")
        }
    }
}