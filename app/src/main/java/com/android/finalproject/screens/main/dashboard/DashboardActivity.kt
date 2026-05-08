package com.android.finalproject.screens.main.dashboard

import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.android.finalproject.R
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.screens.main.groupreview.GroupReviewActivity
import com.android.finalproject.screens.main.soloreview.SoloReviewActivity
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
import com.android.finalproject.utils.setTextViewText
import com.android.finalproject.utils.start
import com.android.finalproject.utils.toast

class DashboardActivity : AppCompatActivity(), DashboardContract.View {
    private lateinit var presenter: DashboardPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        presenter = DashboardPresenter(this,DashboardModel(application as WildQuestApp))
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val view = layoutInflater.inflate(R.layout.dialog_exit, null)

                val dialog = AlertDialog.Builder(this@DashboardActivity)
                    .setView(view)
                    .create()

                view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
                    dialog.dismiss()
                }

                view.findViewById<Button>(R.id.btn_exit).setOnClickListener {
                    finishAffinity()
                }

                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        })
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
        getLinearButtonView(R.id.dashboard_solobutton).setOnClickListener{
            presenter.navToSoloRev()
        }
        getLinearButtonView(R.id.dashboard_groupbutton).setOnClickListener{
            presenter.navToGroupRev()
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


    override fun navigateToSoloRev(){
        start(SoloReviewActivity::class.java)
    }
    override fun navigateToGroupRev(){
        start(GroupReviewActivity::class.java)
    }
    override fun navigateToHome(){
        start(DashboardActivity::class.java)
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