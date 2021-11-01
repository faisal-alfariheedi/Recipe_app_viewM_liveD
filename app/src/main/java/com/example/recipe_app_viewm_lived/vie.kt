package com.example.recipe_app_viewm_lived

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.recipe_app_viewm_lived.R
import com.example.recipe_app_viewm_lived.Vm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class vie : Fragment() {
    lateinit var back: Button
    lateinit var tvtit: TextView
    lateinit var tving: TextView
    lateinit var tvins: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_vie, container, false)
        init(v)
        CoroutineScope(Dispatchers.IO).launch{
            var temp= Vm.pocket

            if (temp != null) {
                tvtit.text=temp.title
                tving.text=temp.ingredients
                tvins.text=temp.instructions
            }
        }
        back.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_vie_to_ma2)

        }

        return v
    }
    fun init(v: View) {

        back=v.findViewById(R.id.returnbut)
        tvtit=v.findViewById(R.id.tvtitle)
        tving=v.findViewById(R.id.tving)
        tvins=v.findViewById(R.id.tvins)
    }


}