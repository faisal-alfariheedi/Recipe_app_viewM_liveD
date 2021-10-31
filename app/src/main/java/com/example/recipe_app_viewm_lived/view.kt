package com.example.recipe_app_viewm_lived

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.recipe_app_viewm_lived.network.RecipeDB
import com.example.recipe_app_viewm_lived.network.RecipeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class view : AppCompatActivity() {
    lateinit var back: Button
    lateinit var tvtit: TextView
    lateinit var tving:TextView
    lateinit var tvins:TextView
    lateinit var db: RecipeDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        init()
        CoroutineScope(Dispatchers.IO).launch{
            var temp=db.getreci(intent.getIntExtra("tit",0))

        tvtit.text=temp.title
        tving.text=temp.ingredients
        tvins.text=temp.instructions
        }
        back.setOnClickListener{
            onBackPressed()

        }

    }
    fun init(){
        db= RecipeDB.getInstance(this).RecipeDao()
        back=findViewById(R.id.returnbut)
        tvtit=findViewById(R.id.tvtitle)
        tving=findViewById(R.id.tving)
        tvins=findViewById(R.id.tvins)
    }
}