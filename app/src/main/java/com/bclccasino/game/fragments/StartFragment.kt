package com.bclccasino.game.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bclccasino.game.R
import com.bclccasino.game.databinding.FragmentStartBinding
import com.bclccasino.game.databinding.FragmentWebBinding
import com.bclccasino.game.utils.KEY_INTENT
import com.bclccasino.game.utils.LoadScreenSettings

class StartFragment : Fragment() {

    private var mBinding: FragmentStartBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when(activity?.intent?.extras?.getSerializable(KEY_INTENT) as LoadScreenSettings){
            LoadScreenSettings.WEB -> findNavController().navigate(R.id.action_startFragment_to_webFragment)
            LoadScreenSettings.ERROR -> findNavController().navigate(R.id.action_startFragment_to_noConnectionFragment)
            else -> {}
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding?.apply {
            play.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_gameFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}