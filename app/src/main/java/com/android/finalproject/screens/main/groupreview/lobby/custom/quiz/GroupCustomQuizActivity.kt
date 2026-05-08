package com.android.finalproject.screens.main.groupreview.lobby.custom.quiz

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
import com.android.finalproject.R.anim
import com.android.finalproject.R.drawable
import com.android.finalproject.R.id
import com.android.finalproject.R.layout
import com.android.finalproject.data.questions.Questions
import com.android.finalproject.screens.main.dashboard.DashboardActivity
import com.android.finalproject.screens.main.groupreview.lobby.custom.scoretally.GroupCustomScoreTallyActivity
import com.android.finalproject.utils.loadScreen
import com.google.android.material.textfield.TextInputEditText


class GroupCustomQuizActivity : AppCompatActivity(),GroupCustomQuizContract.View {
    private lateinit var presenter: GroupCustomQuizPresenter
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
    private var questions : ArrayList<Questions>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_group_custom_quiz)

        questions = intent.getParcelableArrayListExtra<Questions>("questions")

        prevBtn          = findViewById(id.previousBtn)
        tvScore          = findViewById(id.tv_score)
        tvQuestionNumber = findViewById(id.tv_question_number)
        tvQuestion       = findViewById(id.tv_question)
        answerBtn        = findViewById(id.answerBtn)
        btnLabel         = findViewById(id.btn_label)

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

        val model = GroupCustomQuizModel(this)
        presenter = GroupCustomQuizPresenter(this, model)
        presenter.startQuiz()


    }
    fun getQuestion() = questions

    // ── GroupQuizContract.View ────────────────────────────────────────────────

    override fun showAnswerSection(type: String) {
        val questionIndex = presenter.getIndex()

        // re-inflate if type changed OR if it's a different question index
        if (currentStubType == type && currentStubIndex == questionIndex) return

        val container = findViewById<FrameLayout>(id.quiz_answer_container)
        container.removeAllViews()

        layoutInflater.inflate(
            when (type) {
                "identification" -> layout.partial_identification_quiz
                else             -> layout.partial_multiplechoice_quiz
            },
            container,
            true
        )

        currentStubType  = type
        currentStubIndex = questionIndex

        if (type == "identification") {
            etAnswer = container.findViewById(id.etAnswer)
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
                    findViewById<TextInputEditText>(id.etAnswer).setBackgroundResource(drawable.et_empty)
                    Toast.makeText(this,"Cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            etAnswer = null

            btnOptionA = container.findViewById(id.btnOptionA)
            btnOptionB = container.findViewById(id.btnOptionB)
            btnOptionC = container.findViewById(id.btnOptionC)
            btnOptionD = container.findViewById(id.btnOptionD)

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

    override fun showQuestion(question: Questions, questionNumber: Int, total: Int) {
        tvQuestionNumber.text = "Question $questionNumber / $total"
        tvQuestion.text = question.question
        buttonPressed = false

        prevBtn.visibility = if (questionNumber > 1) View.VISIBLE else View.GONE

        // reset multiple choice buttons text + background + enabled state
        listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD)
            .forEachIndexed { i, btn ->
                btn?.isEnabled = true
                btn?.text = "${'A' + i}.  ${question.choices.getOrElse(i) { "" }}"
                btn?.setBackgroundResource(drawable.button_outlined)
            }

        // reset identification input
        etAnswer?.isEnabled = true
        etAnswer?.text?.clear()

        // reset answer button label and visibility based on type
        if (question.type == "identification") {
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
                    btn.setBackgroundResource(drawable.button_correct)
                !wasCorrect && text.contains(selectedAnswer) ->
                    btn.setBackgroundResource(drawable.button_wrong)
                !wasCorrect && text.contains(correctAnswer) ->
                    btn.setBackgroundResource(drawable.button_correct)
                else ->
                    btn.setBackgroundResource(drawable.button_outlined)
            }
        }
        etAnswer?.isEnabled = false;
        etAnswer?.setBackgroundResource(drawable.et_correct)

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
                btn.setBackgroundResource(drawable.button_correct)
            }
        }
        etAnswer?.setBackgroundResource(drawable.et_correct)
        tvQuestion.removeCallbacks(nextQuestionRunnable)
        tvQuestion.postDelayed(nextQuestionRunnable, 1000)
    }

    override fun highlightWrong(selectedAnswer: String, correctAnswer: String) {
        listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD).forEach { btn ->
            val text = btn?.text?.toString() ?: return@forEach
            when {
                text.contains(selectedAnswer) -> btn.setBackgroundResource(drawable.button_wrong)
                text.contains(correctAnswer)  -> btn.setBackgroundResource(drawable.button_correct)
            }
        }
        etAnswer?.setBackgroundResource(drawable.et_wrong)
        tvQuestion.removeCallbacks(nextQuestionRunnable)
        tvQuestion.postDelayed(nextQuestionRunnable, 1000)
    }

    override fun navigateToResults(score: Int, total: Int) {
        val intent = Intent(this, GroupCustomScoreTallyActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", total)
        overridePendingTransition(anim.fade_in, anim.fade_out)
        startActivity(intent)
    }

    override fun navigateToHome() {
        loadScreen(this,
            DashboardActivity::class.java, null, Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        tvQuestion.removeCallbacks(nextQuestionRunnable)
    }
}