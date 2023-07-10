package com.bclccasino.game.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bclccasino.game.R
import com.bclccasino.game.databinding.FragmentNoConnectionBinding
import com.bclccasino.game.databinding.FragmentStartBinding

class NoConnectionFragment : Fragment() {

    private var mBinding: FragmentNoConnectionBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentNoConnectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}