package com.qingmei2.samplepaging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.qingmei2.samplepaging.db.Student
import com.qingmei2.samplepaging.db.StudentDb

class CommonViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = StudentDb.get(app).studentDao()

    val allStudents = LivePagedListBuilder(dao.getAllStudent(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         //配置分页加载的数量
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)     //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              //初始化加载的数量
            .build()).build()

    fun insertStudent(name: String) {
        dao.insert(Student(id = 0, name = name))
    }

    companion object {

        private const val PAGE_SIZE = 15

        private const val ENABLE_PLACEHOLDERS = false
    }
}