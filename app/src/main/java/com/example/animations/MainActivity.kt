package com.example.animations

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.animation.addListener
import com.example.animations.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var finish = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFirst.setOnClickListener {
            spin1()
        }
        binding.btnSecond.setOnClickListener {
            spin2()
        }
        binding.btnThird.setOnClickListener {
            spin3()
        }
    }


    private fun spin1() {

        val value1 = Random.nextInt(8)
        val value2 = Random.nextInt(8)
        val value3 = Random.nextInt(8)

        binding.firstAnim.animate().translationY(0f).withEndAction(Runnable {
            binding.firstAnim.animate().translationY(-binding.firstAnim.height.toFloat())
                .setDuration(1000.toLong())
                .withEndAction(
                    Runnable {
                        if (finish != 5) {
                            finish++
                            setImageColumn(
                                binding.first1,
                                binding.first2,
                                binding.first3,
                                value1,
                                value2,
                                value3
                            )
                            spin1()
                        } else {
                            finish = 0
                            setImageColumn(
                                binding.first1,
                                binding.first2,
                                binding.first3,
                                value1,
                                value2,
                                value3
                            )
                            binding.firstAnim.animate().translationY(0f).start()
                        }
                    }

                )
                .start()
        }).start()
    }

    private fun spin2() {

        val animationNorm = AnimationUtils.loadAnimation(this, R.anim.in_small) as Animation

        val animationSmall = AnimationUtils.loadAnimation(this, R.anim.in_small) as Animation
        animationSmall.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                if (finish != 5) {
                    finish++
                    setImageColumn(
                        binding.second1,
                        binding.second2,
                        binding.second3,
                        Random.nextInt(8),
                        Random.nextInt(8),
                        Random.nextInt(8)
                    )
                    binding.secondAnim.startAnimation(animationNorm)
                    spin2()
                } else {
                    finish = 0
                    setImageColumn(
                        binding.second1,
                        binding.second2,
                        binding.second3,
                        Random.nextInt(8),
                        Random.nextInt(8),
                        Random.nextInt(8)
                    )
                    binding.secondAnim.startAnimation(animationNorm)
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        binding.secondAnim.startAnimation(animationSmall)

    }


    private fun spin3() {
        val set = AnimatorInflater.loadAnimator(this, R.animator.animator)
        val setBack = AnimatorInflater.loadAnimator(this, R.animator.animator_back)
        setBack.setTarget(binding.thirdAnim)
        setBack.duration = 1000.toLong()


        set.setTarget(binding.thirdAnim)
        set.duration = 1000.toLong()
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                if (finish != 3) {
                    setImageColumn(
                        binding.third1,
                        binding.third2,
                        binding.third3,
                        Random.nextInt(8),
                        Random.nextInt(8),
                        Random.nextInt(8)
                    )
                    spin3()
                    finish++
                } else {
                    finish = 0
                    setBack.start()
                }
            }

            override fun onAnimationCancel(p0: Animator?) {
            
            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

        })
        set.start()
    }

    private fun setImageColumn(
        rImage1: ImageView,
        rImage2: ImageView,
        rImage3: ImageView,
        value1: Int,
        value2: Int,
        value3: Int
    ) {
        setImage(rImage1, value1)
        setImage(rImage2, value2)
        setImage(rImage3, value3)

    }

    private fun setImage(image: ImageView, value: Int) {
        when (value) {
            0 -> image.setImageResource(R.drawable.symbol08)
            1 -> image.setImageResource(R.drawable.symbol01)
            2 -> image.setImageResource(R.drawable.symbol02)
            3 -> image.setImageResource(R.drawable.symbol03)
            4 -> image.setImageResource(R.drawable.symbol04)
            5 -> image.setImageResource(R.drawable.symbol05)
            6 -> image.setImageResource(R.drawable.symbol06)
            7 -> image.setImageResource(R.drawable.symbol07)
        }
        image.tag = value.toString()

    }

}