package com.android.finalproject.screens.main.soloreview

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.android.finalproject.R
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.screens.main.soloreview.lobby.competitive.loading.SoloCompeLoadingActivity
import com.android.finalproject.screens.main.soloreview.lobby.custom.prep.SoloCustomPrepActivity
import com.android.finalproject.screens.sub.faqs.FAQSActivity
import com.android.finalproject.screens.sub.history.HistoryActivity
import com.android.finalproject.screens.sub.leaderboards.LeaderboardActivity
import com.android.finalproject.screens.sub.logopage.LogoPageActivity
import com.android.finalproject.screens.sub.mygroups.MyGroupsActivity
import com.android.finalproject.screens.sub.profile.ProfileActivity
import com.android.finalproject.screens.sub.settings.SettingsActivity
import com.android.finalproject.utils.getButtonView
import com.android.finalproject.utils.getDrawerView
import com.android.finalproject.utils.getImgButtonView
import com.android.finalproject.utils.getLinearButtonView
import com.android.finalproject.utils.loadScreen
import com.android.finalproject.utils.setTextViewText
import com.android.finalproject.utils.start
import com.android.finalproject.utils.toast

class SoloReviewActivity : AppCompatActivity(), SoloReviewContract.View {
    private lateinit var presenter: SoloReviewPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_review)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        })

        presenter = SoloReviewPresenter(this, SoloReviewModel(application as WildQuestApp))

        presenter.setProfileID()

        //Navigation bar Buttons
        getImgButtonView(R.id.menu_icon).setOnClickListener{
            getDrawerView(R.id.drawerLayout).openDrawer(GravityCompat.START)
        }
        getImgButtonView(R.id.leaderboard_icon).setOnClickListener{
            presenter.navToLeaderboards()
        }
        getImgButtonView(R.id.notification_icon).setOnClickListener{
            getDrawerView(R.id.drawerLayout).openDrawer(GravityCompat.END)
        }

        //other icons
        getImgButtonView(R.id.message_icon).setOnClickListener{

        }
        getLinearButtonView(R.id.soloreview_compebutton).setOnClickListener{
            presenter.navToCompeRoom()
        }
        getLinearButtonView(R.id.soloreview_custombutton).setOnClickListener{
            presenter.navToCusPrep()
        }

        // Menu Buttons
        getButtonView(R.id.menu_homebtn).setOnClickListener {
            presenter.navToHome()
        }
        getButtonView(R.id.menu_profilebtn).setOnClickListener {
            presenter.navToProfile()
        }
        getButtonView(R.id.menu_groupsbtn).setOnClickListener {
            presenter.navToGroups()
        }
        getButtonView(R.id.menu_historybtn).setOnClickListener {
            presenter.navToHistory()
        }
        getButtonView(R.id.menu_settingsbtn).setOnClickListener {
            presenter.navToSettings()
        }
        getButtonView(R.id.menu_faqsbtn).setOnClickListener {
            presenter.navToFAQ()
        }
        getButtonView(R.id.menu_logoutbtn).setOnClickListener {
            presenter.logout()
        }
    }

    override fun navigateToCompetitiveRoom(){
        loadScreen(this, SoloCompeLoadingActivity::class.java)
    }
    override fun navigateToCustomPrep(){
        loadScreen(this,SoloCustomPrepActivity::class.java)
    }
    override fun navigateToHome(){
        loadScreen(this,DashboardActivity::class.java, null, Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
    override fun navigateToLeaderboards(){
        start(LeaderboardActivity::class.java)
    }
    override fun navigateToGroups(){
        start(MyGroupsActivity::class.java)
    }
    override fun navigateToProfile(){
        start(ProfileActivity::class.java)
    }
    override fun navigateToHistory(){
        start(HistoryActivity::class.java)
    }
    override fun navigateToSettings(){
        start(SettingsActivity::class.java)
    }
    override fun navigateToFAQ(){
        start(FAQSActivity::class.java)
    }
    override fun navigateToLogo(){
        start(LogoPageActivity::class.java)
    }
    override fun showSuccessLogoutMsg(){
        toast("Log out successfully!")
    }
    override fun setProfileID(id: String) {
        setTextViewText(R.id.menu_profiled_id,id)
    }
}