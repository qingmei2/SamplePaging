package com.qingmei2.samplepaging.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.qingmei2.samplepaging.ioThread

@Database(entities = arrayOf(Student::class), version = 1)
abstract class StudentDb : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {

        private var instance: StudentDb? = null

        @Synchronized
        fun get(context: Context): StudentDb {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                        StudentDb::class.java, "StudentDatabase")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                ioThread {
                                    get(context).studentDao().insert(
                                            CHEESE_DATA.map { Student(id = 0, name = it) })
                                }
                            }
                        }).build()
            }
            return instance!!
        }
    }
}

private val CHEESE_DATA = arrayListOf(
        "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
        "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
        "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
        "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
        "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
        "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
        "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
        "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
        "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
        "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
        "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
        "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
        "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
)