package com.swensun.swdesign.ui.viewpager


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.swensun.swdesign.R


/**
 * A simple [Fragment] subclass.
 */
class CardFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        
    }
}
