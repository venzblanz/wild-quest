package com.android.finalproject.screens.main.groupreview.lobby.custom.room

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.screens.main.groupreview.GroupReviewActivity
import com.android.finalproject.screens.main.groupreview.lobby.custom.quiz.GroupCustomQuizActivity
import com.android.finalproject.utils.getImgButtonView
import com.android.finalproject.utils.getLinearButtonView
import com.android.finalproject.utils.loadScreen

class GroupCustomRoomActivity : AppCompatActivity(), GroupCustomRoomContract.View {
    private lateinit var presenter: GroupCustomRoomPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = GroupCustomRoomPresenter(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_group_custom_room)
        onBackPressedDispatcher.addCallback(this@GroupCustomRoomActivity, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                // None doesn't allow user t o go back while on quiz
            }
        })
        getImgButtonView(R.id.dashboard_logobutton).setOnClickListener{
            presenter.navigateToPrevious()
        }
        getLinearButtonView(R.id.start_queue).setOnClickListener{
            presenter.navigateToGroupCustomQuiz()
        }
    }
    override fun navToGroupCustomQuiz(){
        loadScreen(this, GroupCustomQuizActivity::class.java)
    }
    override fun navToPrev(){
        loadScreen(this,
            GroupReviewActivity::class.java, null, Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
}