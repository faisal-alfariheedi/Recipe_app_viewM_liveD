package com.example.recipe_app_viewm_lived

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_app_viewm_lived.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ma2 : Fragment() {
    lateinit var rv: RecyclerView
    lateinit var but: Button
    lateinit var apif: APIInterface
    var res: ArrayList<recipe.dat> = arrayListOf()
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var db: RecipeDao
    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_ma2, container, false)

        init(v)
        but.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_ma2_to_main)

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


                        activity?.runOnUiThread{ wait(false)}
                    }

                }

                override fun onFailure(call: Call<List<recipe.dat>>, t: Throwable) {

                    wait(false)
                    Toast.makeText(v.context, t.message, Toast.LENGTH_SHORT).show();
                }
            })
        }

        return v
    }

    private fun init(v: View) {
        db= RecipeDB.getInstance(v.context).RecipeDao()
        but = v.findViewById(R.id.addres)
        rv = v.findViewById(R.id.rvma)
        rv.layoutManager = LinearLayoutManager(v.context)
        var ad= RVAdapter(v.context)
        rv.adapter = ad
        mvm.getAll().observe(viewLifecycleOwner,{
            ad.setrv(it)
            Toast.makeText(v.context,"updated",Toast.LENGTH_SHORT).show()
        })
        apif = APIClient().getClient()?.create(APIInterface::class.java)!!
        tvw=v.findViewById(R.id.wait)
        prog=v.findViewById(R.id.progressBar)
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