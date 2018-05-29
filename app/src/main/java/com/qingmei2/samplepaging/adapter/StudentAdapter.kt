package com.qingmei2.samplepaging.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.qingmei2.samplepaging.db.Student

class StudentAdapter : PagedListAdapter<Student, StudentViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder =
            StudentViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem == newItem
        }
    }
}