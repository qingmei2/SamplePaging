package com.qingmei2.samplepaging.ui.header_proxy

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qingmei2.samplepaging.db.Student
import com.qingmei2.samplepaging.ui.viewholder.HeaderViewHolder
import com.qingmei2.samplepaging.ui.viewholder.FooterViewHolder
import com.qingmei2.samplepaging.ui.viewholder.StudentViewHolder

class HeaderProxyAdapter : PagedListAdapter<Student, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_HEADER
            itemCount - 1 -> ITEM_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_HEADER -> HeaderViewHolder(parent)
            ITEM_TYPE_FOOTER -> FooterViewHolder(parent)
            else -> StudentViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindsHeader()
            is FooterViewHolder -> holder.bindsFooter()
            is StudentViewHolder -> holder.bindTo(getStudentItem(position))
        }
    }

    private fun getStudentItem(position: Int): Student? {
        return getItem(position - 1)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 2
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, 1))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem == newItem
        }

        private const val ITEM_TYPE_HEADER = 99
        private const val ITEM_TYPE_FOOTER = 100
    }
}