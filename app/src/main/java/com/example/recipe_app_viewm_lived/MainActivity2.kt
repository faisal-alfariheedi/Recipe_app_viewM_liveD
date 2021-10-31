package com.example.recipe_app_viewm_lived

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app_viewm_lived.*
import com.example.recipe_app_viewm_lived.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity2 : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var but: Button
    lateinit var apif: APIInterface
    var res: ArrayList<recipe.dat> = arrayListOf()
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var db: RecipeDao
    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
        but.setOnClickListener{
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }
        if (apif != null) {
            apif.getRecipies()?.enqueue(object : Callback<List<recipe.dat>> {
                override fun onResponse(call: Call<List<recipe.dat>>, response: Response<List<recipe.dat>>) {
                    Log.d("message body", response.body()!![1].title!!)
                    if(response.body()!=null){
                        mvm.addall(response.body()!!)
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        var t = response.body()
                        if (t != null) {
                            db.addall(t)
                        }


                        runOnUiThread{ wait(false)}
                    }

                }

                override fun onFailure(call: Call<List<recipe.dat>>, t: Throwable) {

                    wait(false)
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show();
                }
            })
        }

    }
    fun init() {
        db= RecipeDB.getInstance(this).RecipeDao()
        but = findViewById(R.id.addres)
        rv = findViewById(R.id.rvma)
        rv.layoutManager = LinearLayoutManager(this)
        var ad=RVAdapter(this)
        rv.adapter = ad
        mvm.getAll().observe(this,{
            ad.setrv(it)
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show()
        })
        apif = APIClient().getClient()?.create(APIInterface::class.java)!!
        tvw=findViewById(R.id.wait)
        prog=findViewById(R.id.progressBar)
        wait(true)
    }
    fun wait(a:Boolean){
        if(a){
            prog.isVisible = true
            tvw.isVisible = true
        }else{
            prog.isVisible=false
            tvw.isVisible=false
        }

    }
}