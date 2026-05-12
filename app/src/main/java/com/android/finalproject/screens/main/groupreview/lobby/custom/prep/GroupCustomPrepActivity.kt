package com.android.finalproject.screens.main.groupreview.lobby.custom.prep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.finalproject.R
import com.android.finalproject.data.adapter.QuestionAdapter
import com.android.finalproject.data.questions.QuizQuestion
import com.android.finalproject.screens.main.groupreview.lobby.custom.quiz.GroupCustomQuizActivity

class GroupCustomPrepActivity : AppCompatActivity(), GroupCustomPrepContract.View {
    private lateinit var presenter: GroupCustomPrepPresenter

    private lateinit var questionTypeGroup: RadioGroup
    private lateinit var rbMultipleChoice: RadioButton
    private lateinit var rbIdentification: RadioButton

    private lateinit var questionFormContainer: FrameLayout
    private lateinit var questionListView: ListView
    private lateinit var startBtn: LinearLayout

    private lateinit var adapter: QuestionAdapter
    private val displayedQuestions = ArrayList<QuizQuestion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_custom_prep)

        presenter = GroupCustomPrepPresenter(
            this,
            GroupCustomPrepModel()
        )

        bindViews()
        setupListView()
        setupListeners()

        presenter.onTypeSelected("MC")
    }

    private fun bindViews() {
        questionTypeGroup = findViewById(R.id.questionTypeGroup)
        rbMultipleChoice = findViewById(R.id.rbMultipleChoice)
        rbIdentification = findViewById(R.id.rbIdentification)

        questionFormContainer = findViewById(R.id.questionFormContainer)
        questionListView = findViewById(R.id.questionListView)
        startBtn = findViewById(R.id.startBtn)
    }

    private fun setupListView() {
        adapter = QuestionAdapter(this, displayedQuestions) { position ->
            presenter.deleteQuestion(position)
        }

        questionListView.adapter = adapter
    }

    private fun setupListeners() {
        questionTypeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbMultipleChoice -> presenter.onTypeSelected("MC")
                R.id.rbIdentification -> presenter.onTypeSelected("ID")
            }
        }

        startBtn.setOnClickListener {
            presenter.startReview()
        }
    }

    override fun showMultipleChoiceForm() {
        questionFormContainer.removeAllViews()

        val formView = layoutInflater.inflate(
            R.layout.form_multiple_choice,
            questionFormContainer,
            false
        )

        val etQuestion = formView.findViewById<EditText>(R.id.etQuestion)
        val etOptionA = formView.findViewById<EditText>(R.id.etOptionA)
        val etOptionB = formView.findViewById<EditText>(R.id.etOptionB)
        val etOptionC = formView.findViewById<EditText>(R.id.etOptionC)
        val etOptionD = formView.findViewById<EditText>(R.id.etOptionD)
        val etAnswer = formView.findViewById<EditText>(R.id.etAnswer)
        val addQuestionBtn = formView.findViewById<Button>(R.id.addQuestionBtn)

        addQuestionBtn.setOnClickListener {
            presenter.addMultipleChoiceQuestion(
                question = etQuestion.text.toString(),
                optionA = etOptionA.text.toString(),
                optionB = etOptionB.text.toString(),
                optionC = etOptionC.text.toString(),
                optionD = etOptionD.text.toString(),
                answer = etAnswer.text.toString()
            )

            etQuestion.text.clear()
            etOptionA.text.clear()
            etOptionB.text.clear()
            etOptionC.text.clear()
            etOptionD.text.clear()
            etAnswer.text.clear()
        }

        questionFormContainer.addView(formView)
    }

    override fun showIdentificationForm() {
        questionFormContainer.removeAllViews()

        val formView = layoutInflater.inflate(
            R.layout.form_identification,
            questionFormContainer,
            false
        )

        val etQuestion = formView.findViewById<EditText>(R.id.etQuestion)
        val etAnswer = formView.findViewById<EditText>(R.id.etAnswer)
        val addQuestionBtn = formView.findViewById<Button>(R.id.addQuestionBtn)

        addQuestionBtn.setOnClickListener {
            presenter.addIdentificationQuestion(
                question = etQuestion.text.toString(),
                answer = etAnswer.text.toString()
            )

            etQuestion.text.clear()
            etAnswer.text.clear()
        }

        questionFormContainer.addView(formView)
    }

    override fun refreshQuestionList(questions: List<QuizQuestion>) {
        displayedQuestions.clear()
        displayedQuestions.addAll(questions)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToCustomQuiz(questions: ArrayList<QuizQuestion>) {
        val intent = Intent(this, GroupCustomQuizActivity::class.java)

        intent.putParcelableArrayListExtra("questions", questions)

        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}