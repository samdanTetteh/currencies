package com.example.currencies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.currencies.R
import com.example.currencies.ViewModel.ViewModel

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var viewModel : ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_main, container, false)
        viewModel = ViewModelProviders.of(requireActivity()).get(ViewModel::class.java)

        viewModel.currencyData.observe(this, Observer {
            Log.d(LOG_TAG, "${it.baseCurrency} ${it.rates.size}")

        })



        return view
    }

}
