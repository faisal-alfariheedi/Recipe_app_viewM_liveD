package com.example.recipe_app_viewm_lived.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities=[recipe.dat::class],version=1,exportSchema = false)
abstract class RecipeDB: RoomDatabase() {

    companion object {
        @Volatile
        var instance: RecipeDB?=null
        fun getInstance(cont: Context): RecipeDB {
            return instance ?:synchronized(this){
                instance ?: buildDatabase(cont).also{ instance =it}
            }
        }
        fun buildDatabase(cont: Context): RecipeDB {
            return Room.databaseBuilder(cont, RecipeDB::class.java,"Celeb").build()
        }
    }
    abstract fun RecipeDao(): RecipeDao
}