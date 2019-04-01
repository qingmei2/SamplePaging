package com.qingmei2.samplepaging.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM Student ORDER BY name COLLATE NOCASE ASC")
    fun getAllStudent(): DataSource.Factory<Int, Student>

    @Insert
    fun insert(students: List<Student>)

    @Insert
    fun insert(student: Student)
}