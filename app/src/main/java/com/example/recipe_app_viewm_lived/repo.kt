package com.example.recipe_app_viewm_lived

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.recipe_app_viewm_lived.network.RecipeDB
import com.example.recipe_app_viewm_lived.network.RecipeDao
import com.example.recipe_app_viewm_lived.network.recipe

class repo {
    var db:RecipeDao
    var list : LiveData<List<recipe.dat>>

    constructor(cont: Application) {
        db= RecipeDB.getInstance(cont).RecipeDao()
        list=db.getall()
    }

    fun addedit(rec: recipe.dat){
        insnote(db).execute(rec)
    }
    fun delete(rec: recipe.dat){
        delnote(db).execute(rec)
    }
    fun getAll():LiveData<List<recipe.dat>>{
        return list
    }
    fun addall(rec:List<recipe.dat>){
        insall(db).execute(rec)
    }
    class insall(var db: RecipeDao) : AsyncTask<List<recipe.dat>, Void, String>(){
        override fun doInBackground(vararg p0: List<recipe.dat>?): String {
            db.addall(p0[0]!!)
            return ""
        }
    }
    class insnote(var db: RecipeDao) : AsyncTask<recipe.dat, Void, String>(){
        override fun doInBackground(vararg p0: recipe.dat?): String {
            db.addeditRecipe(p0[0]!!)
            return ""
        }
    }
    class delnote(var db: RecipeDao) : AsyncTask<recipe.dat, Void, String>(){
        override fun doInBackground(vararg p0: recipe.dat?): String {
            db.deleteRecipe(p0[0]!!)
            return ""
        }
    }
}