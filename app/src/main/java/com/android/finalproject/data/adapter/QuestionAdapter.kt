package com.android.finalproject.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.android.finalproject.R
import com.android.finalproject.data.questions.Questions

class QuestionAdapter(
    private val context: Context,
    private val questions: ArrayList<Questions>,
    private val onDelete: (Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = questions.size

    override fun getItem(position: Int): Any = questions[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_custom_question, parent, false)

        val questionPreview = view.findViewById<TextView>(R.id.questionPreview)
        val questionType = view.findViewById<TextView>(R.id.questionType)
        val deleteBtn = view.findViewById<ImageButton>(R.id.deleteQuestionBtn)

        val item = questions[position]

        questionPreview.text = item.question
        questionType.text = item.type

        deleteBtn.setOnClickListener {
            onDelete(position)
        }

        return view
    }
}