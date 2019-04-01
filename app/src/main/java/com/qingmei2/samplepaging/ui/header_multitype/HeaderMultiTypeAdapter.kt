package com.qingmei2.samplepaging.ui.header_multitype

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qingmei2.samplepaging.db.Student
import com.qingmei2.samplepaging.ui.viewholder.BannerViewHolder
import com.qingmei2.samplepaging.ui.viewholder.StudentViewHolder

class HeaderMultiTypeAdapter : PagedListAdapter<Student, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (position == 0) {
            true -> ITEM_TYPE_HEADER
            false -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_HEADER -> BannerViewHolder(parent)
            else -> StudentViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> holder.binds()
            is StudentViewHolder -> holder.bindTo(getStudentItem(position))
        }
    }

    private fun getStudentItem(position: Int): Student? {
        return getItem(position - 1)
    }

    // 这种多类型的Adapter存在很大的问题
    // 1.展示不全，因为第一个item展示了Header, 因此数据只展示了n-1条
    // 2.如果重写getItemCount()方法，指定item数量 +1，则界面刷新出现问题
    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                    oldItem == newItem
        }

        private const val ITEM_TYPE_HEADER = 99
    }
}