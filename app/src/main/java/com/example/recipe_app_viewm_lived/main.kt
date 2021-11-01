package com.example.recipe_app_viewm_lived

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.recipe_app_viewm_lived.network.APIClient
import com.example.recipe_app_viewm_lived.network.APIInterface
import com.example.recipe_app_viewm_lived.network.recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class main : Fragment() {
    lateinit var intitle: EditText
    lateinit var inauther: EditText
    lateinit var ingrediant: EditText
    lateinit var instraction: EditText
    lateinit var save: Button
    lateinit var view: Button
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    val apif = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_main, container, false)
        init(v)

        save.setOnClickListener {
            addresp(v)
        }
        view.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_main_to_ma2)

        }


        return v
    }

    private fun init(v: View) {
        intitle=v.findViewById(R.id.intit)
        inauther=v.findViewById(R.id.inauth)
        ingrediant=v.findViewById(R.id.iing)
        instraction=v.findViewById(R.id.inins)
        save=v.findViewById(R.id.savebut)
        view=v.findViewById(R.id.viewbut)
        tvw=v.findViewById(R.id.wait)
        prog=v.findViewById(R.id.progressBar)
    }

    fun addresp(v: View) {
        wait(true)
        if (intitle.text.isNotEmpty() && inauther.text.isNotEmpty() && ingrediant.text.isNotEmpty() && instraction.text.isNotEmpty()) {

            if (apif != null) {
                var f = recipe.dat(
                    intitle.text.toString(),
                    inauther.text.toString(),
                    ingrediant.text.toString(),
                    instraction.text.toString()
                )
                apif.addRecipie(f).enqueue(object : Callback<recipe.dat> {
                    override fun onResponse(call: Call<recipe.dat>, response: Response<recipe.dat>) {
                        wait(false)
                        intitle.setText("")
                        inauther.setText("")
                        ingrediant.setText("")
                        instraction.setText("")
                        Toast.makeText(v.context, "Save Success!", Toast.LENGTH_LONG).show()

                    }

                    override fun onFailure(call: Call<recipe.dat>, t: Throwable) {
                        Toast.makeText(v.context, "Error!", Toast.LENGTH_SHORT).show();
                        wait(false)

                    }
                })
            }

        } else {
            Toast.makeText(v.context, "you can`t submit an empty field", Toast.LENGTH_LONG).show()
        }

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