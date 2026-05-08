package com.android.finalproject.screens.main.groupreview.lobby.competitive.room

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.screens.main.groupreview.GroupReviewActivity
import com.android.finalproject.screens.main.groupreview.lobby.competitive.loading.GroupCompeLoadingActivity
import com.android.finalproject.utils.getImgButtonView
import com.android.finalproject.utils.getLinearButtonView
import com.android.finalproject.utils.loadScreen

class GroupCompeRoomActivity : AppCompatActivity(), GroupCompeRoomContract.View {
    private lateinit var presenter: GroupCompeRoomPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = GroupCompeRoomPresenter(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_group_compe_room)
        onBackPressedDispatcher.addCallback(this@GroupCompeRoomActivity, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                // None doesn't allow user t o go back while on quiz
            }
        })
        getImgButtonView(R.id.dashboard_logobutton).setOnClickListener{
            presenter.navigateToPrevious()
        }
        getLinearButtonView(R.id.start_queue).setOnClickListener{
            presenter.navigateToGroupCompeQuiz()
        }
    }
    override fun navToGroupCompeQuiz(){
        loadScreen(this, GroupCompeLoadingActivity::class.java)
    }
    override fun navToPrev(){
        loadScreen(this,GroupReviewActivity::class.java, null, Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
}