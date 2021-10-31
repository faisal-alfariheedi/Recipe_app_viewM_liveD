package com.example.recipe_app_viewm_lived

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recipe_app_viewm_lived.network.recipe

class Vm(application: Application) : AndroidViewModel(application) {
    var rep=repo(application)
    private var list=rep.getAll()

    fun addedit(rec: recipe.dat){
        rep.addedit(rec)
    }
    fun delete(rec: recipe.dat){
        rep.delete(rec)
    }
    fun getAll():LiveData<List<recipe.dat>>{
        return list
    }
    fun addall(rec:List<recipe.dat>){
        rep.addall(rec)
    }

}