package com.example.s5_0706.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s5_0706.R
import com.example.s5_0706.adapter.HomeListAdapter.HomeListAdapter
import com.example.s5_0706.databinding.FragmentHomeBinding
import org.json.JSONArray

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var b=FragmentHomeBinding.inflate(layoutInflater)
        var json=requireContext().assets.open("show.json").bufferedReader().readText()
        b.list.adapter= HomeListAdapter(requireContext(), JSONArray(json))
        json=requireContext().assets.open("news.json").bufferedReader().readText()
        b.list2.adapter= HomeListAdapter(requireContext(), JSONArray(json))
        return b.root
    }

}