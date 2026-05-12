package com.android.finalproject.screens.main.groupreview.lobby.competitive.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.data.questions.QuizQuestion
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.screens.main.groupreview.lobby.competitive.scoretally.GroupCompeScoreTallyActivity
import com.android.finalproject.utils.loadScreen
import com.google.android.material.textfield.TextInputEditText

class GroupCompeQuizActivity : AppCompatActivity(), GroupCompeQuizContract.View {

    private lateinit var presenter: GroupCompeQuizPresenter
    private lateinit var prevBtn: Button
    private lateinit var answerBtn: LinearLayout
    private lateinit var btnLabel: TextView

    // card views
    private lateinit var tvScore: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestion: TextView

    // answer views — wired after container inflates
    private var etAnswer: TextInputEditText? = null
    private var btnOptionA: Button? = null
    private var btnOptionB: Button? = null
    private var btnOptionC: Button? = null
    private var btnOptionD: Button? = null

    // state tracking
    private var buttonPressed: Boolean = false
    private var currentStubType: String? = null
    private var currentStubIndex: Int = -1

    // named runnable so removeCallbacks can actually cancel it
    private val nextQuestionRunnable = Runnable { presenter.nextQuestion() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_group_quiz)

        prevBtn          = findViewById(R.id.previousBtn)
        tvScore          = findViewById(R.id.tv_score)
        tvQuestionNumber = findViewById(R.id.tv_question_number)
        tvQuestion       = findViewById(R.id.tv_question)
        answerBtn        = findViewById(R.id.answerBtn)
        btnLabel         = findViewById(R.id.btn_label)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })

        // default answer/next button behaviour — overridden per question type
        answerBtn.setOnClickListener {
            if (buttonPressed) {
                buttonPressed = false
                tvQuestion.removeCallbacks(nextQuestionRunnable)
                presenter.nextQuestion()
            }
        }

        prevBtn.visibility = View.GONE
        prevBtn.setOnClickListener {
            tvQuestion.removeCallbacks(nextQuestionRunnable)
            presenter.previousQuestion()
        }

        val model = GroupCompeQuizModel(this)
        presenter = GroupCompeQuizPresenter(this, model, lifecycleScope)
        presenter.startQuiz()
    }

    // ── SoloQuizContract.View ────────────────────────────────────────────────

    override fun showAnswerSection(type: String) {
        val questionIndex = presenter.getIndex()
        val normalizedType = type.lowercase()

        if (currentStubType == normalizedType && currentStubIndex == questionIndex) return

        val container = findViewById<FrameLayout>(R.id.quiz_answer_container)
        container.removeAllViews()

        layoutInflater.inflate(
            when (normalizedType) {
                "identification" -> R.layout.partial_identification_quiz
                else -> R.layout.partial_multiplechoice_quiz
            },
            container,
            true
        )

        currentStubType = normalizedType
        currentStubIndex = questionIndex

        if (type == "identification") {
            etAnswer = container.findViewById(R.id.etAnswer)
            btnOptionA = null
            btnOptionB = null
            btnOptionC = null
            btnOptionD = null

            answerBtn.visibility = View.VISIBLE
            btnLabel.text = "Answer"
            answerBtn.setOnClickListener {
                val answer = etAnswer?.text?.toString()?.trim() ?: return@setOnClickListener
                if (answer.isNotEmpty() && !buttonPressed) {
                    buttonPressed = true
                    presenter.submitAnswer(answer)
                }else{
                    findViewById<TextInputEditText>(R.id.etAnswer).setBackgroundResource(R.drawable.et_empty)
                    Toast.makeText(this,"Cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            etAnswer = null

            btnOptionA = container.findViewById(R.id.btnOptionA)
            btnOptionB = container.findViewById(R.id.btnOptionB)
            btnOptionC = container.findViewById(R.id.btnOptionC)
            btnOptionD = container.findViewById(R.id.btnOptionD)

            answerBtn.visibility = View.GONE

            listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD).forEach { btn ->
                btn?.isEnabled = true
                btn?.setOnClickListener {
                    if (!buttonPressed) {
                        buttonPressed = true
                        val answer = btn.text.toString().substringAfter(".  ")
                        presenter.submitAnswer(answer)
                    }
                }
            }
        }
    }

    override fun showQuestion(question: QuizQuestion, questionNumber: Int, total: Int) {
        tvQuestionNumber.text = "Question $questionNumber / $total"
        tvQuestion.text = question.questionText
        buttonPressed = false

        prevBtn.visibility = if (questionNumber > 1) View.VISIBLE else View.GONE

        val options = listOf(
            question.optionA,
            question.optionB,
            question.optionC,
            question.optionD
        )

        listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD)
            .forEachIndexed { i, btn ->
                btn?.isEnabled = true
                btn?.text = "${'A' + i}.  ${options.getOrElse(i) { "" }}"
                btn?.setBackgroundResource(R.drawable.button_outlined)
            }

        etAnswer?.isEnabled = true
        etAnswer?.text?.clear()

        if (question.type.equals("Identification", ignoreCase = true)) {
            answerBtn.visibility = View.VISIBLE
            btnLabel.text = "Answer"
        } else {
            answerBtn.visibility = View.GONE
        }
    }

    override fun showPreviousAnswer(selectedAnswer: String, correctAnswer: String) {
        // cancel any pending auto-advance
        tvQuestion.removeCallbacks(nextQuestionRunnable)

        val wasCorrect = selectedAnswer.trim().equals(correctAnswer.trim(), ignoreCase = true)

        // show Next button and wire it up — outside forEach
        answerBtn.visibility = View.VISIBLE
        btnLabel.text = "Next"
        answerBtn.setOnClickListener {
            tvQuestion.removeCallbacks(nextQuestionRunnable)
            presenter.nextQuestion()
        }

        // highlight buttons and disable them
        listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD).forEach { btn ->
            btn?.isEnabled = false
            val text = btn?.text?.toString() ?: return@forEach
            when {
                wasCorrect && text.contains(selectedAnswer) ->
                    btn.setBackgroundResource(R.drawable.button_correct)
                !wasCorrect && text.contains(selectedAnswer) ->
                    btn.setBackgroundResource(R.drawable.button_wrong)
                !wasCorrect && text.contains(correctAnswer) ->
                    btn.setBackgroundResource(R.drawable.button_correct)
                else ->
                    btn.setBackgroundResource(R.drawable.button_outlined)
            }
        }
        etAnswer?.isEnabled = false;
        etAnswer?.setBackgroundResource(R.drawable.et_correct)

        // for identification — show what they typed, disable input
        etAnswer?.setText(selectedAnswer)
        etAnswer?.isEnabled = false

        buttonPressed = true
    }

    override fun showScore(score: Int) {
        tvScore.text = "Score: $score"
    }

    override fun highlightCorrect(correctAnswer: String) {
        listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD).forEach { btn ->
            if (btn?.text?.toString()?.contains(correctAnswer) == true) {
                btn.setBackgroundResource(R.drawable.button_correct)
            }
        }
        etAnswer?.setBackgroundResource(R.drawable.et_correct)
        tvQuestion.removeCallbacks(nextQuestionRunnable)
        tvQuestion.postDelayed(nextQuestionRunnable, 1000)
    }

    override fun highlightWrong(selectedAnswer: String, correctAnswer: String) {
        listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD).forEach { btn ->
            val text = btn?.text?.toString() ?: return@forEach
            when {
                text.contains(selectedAnswer) -> btn.setBackgroundResource(R.drawable.button_wrong)
                text.contains(correctAnswer)  -> btn.setBackgroundResource(R.drawable.button_correct)
            }
        }
        etAnswer?.setBackgroundResource(R.drawable.et_wrong)
        tvQuestion.removeCallbacks(nextQuestionRunnable)
        tvQuestion.postDelayed(nextQuestionRunnable, 1000)
    }

    override fun navigateToResults(score: Int, total: Int) {
        val extras = Bundle().apply {
            putInt("score", score)
            putInt("total", total)
        }
        loadScreen(this, GroupCompeScoreTallyActivity::class.java, extras)
    }

    override fun navigateToHome() {
        loadScreen(this, DashboardActivity::class.java, null, Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        tvQuestion.removeCallbacks(nextQuestionRunnable)
    }
}