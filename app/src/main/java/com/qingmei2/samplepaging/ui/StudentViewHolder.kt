package com.qingmei2.samplepaging.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.qingmei2.samplepaging.R
import com.qingmei2.samplepaging.db.Student

class StudentViewHolder(parent: ViewGroup) : androidx.recyclerview.widget.RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var student: Student? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(student: Student?) {
        this.student = student
        nameView.text = student?.name
    }
}