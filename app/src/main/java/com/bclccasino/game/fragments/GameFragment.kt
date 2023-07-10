package com.bclccasino.game.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import com.bclccasino.game.databinding.FragmentGameBinding
import com.bclccasino.game.utils.AppPreferences
import java.util.Random

class GameFragment : Fragment() {

    private var mBinding: FragmentGameBinding? = null
    private val binding get() = mBinding!!

    private val sectors = intArrayOf(750, 450, 200, 125, 100, 75, 45, 25)
    private val sectorDegrees = IntArray(sectors.size)
    private var spinning = false
    private var randomSectorIndex = 0
    private val random = Random()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateSectorDegrees()

        mBinding?.apply {

            lastText.text = AppPreferences.getLastRes().toString()

            spin.setOnClickListener {
                if (!spinning) {
                    spin()
                    spinning = false
                    spin.visibility = View.GONE
                }
            }
            resultQ.setOnClickListener {
                resultQ.visibility = View.GONE
                spin.visibility = View.VISIBLE
                resultShadow.visibility = View.GONE
                lastText.text = AppPreferences.getLastRes().toString()
            }

            delete.setOnClickListener {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }

    private fun spin() {
        randomSectorIndex = random.nextInt(sectors.size)
        val randomDegree = generateRandomDegreeToSpinTo()

        val rotateAnimation = RotateAnimation(0f,
            randomDegree.toFloat(), RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        rotateAnimation.duration = 3500
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val result = sectors[sectors.size - (randomSectorIndex + 1)]
                mBinding?.apply {
                    resultQ.visibility = View.VISIBLE
                    resultShadow.visibility = View.VISIBLE
                    resultText.text = result.toString()
                    AppPreferences.setLastRes(result)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
        mBinding?.mainWheel?.startAnimation(rotateAnimation)
    }

    private fun generateRandomDegreeToSpinTo(): Int {
        return (360 * sectors.size) + sectorDegrees[randomSectorIndex]
    }

    private fun generateSectorDegrees() {
        val sectorDegree = 360 / sectors.size
        for (i in sectors.indices) {
            sectorDegrees[i] = (i + 1) * sectorDegree
        }
    }
}