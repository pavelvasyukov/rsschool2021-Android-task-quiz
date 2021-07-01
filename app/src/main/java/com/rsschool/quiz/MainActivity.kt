package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.rsschool.quiz.databinding.ActivityMainBinding


const val EXTRA_MESSAGE = "com.rsschool.quiz.MESSAGE"


class MainActivity : FragmentActivity(), ActionListener {

    private lateinit var adapter: NumberAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var binding: ActivityMainBinding
    private val dataQuiz = DataObjectAccess()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NumberAdapter(this)

        viewPager = binding.pager
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> window.statusBarColor = getColor(R.color.secondaryDarkColorPurple)
                    1 -> window.statusBarColor = getColor(R.color.secondaryDarkColorBlue)
                    2 -> window.statusBarColor = getColor(R.color.secondaryDarkColorGreen)
                    3 -> window.statusBarColor = getColor(R.color.secondaryDarkColorYellow)
                    4 -> window.statusBarColor = getColor(R.color.secondaryDarkColorOrange)
                    else -> window.statusBarColor = getColor(R.color.secondaryDarkColor)
                }
            }
        }
        )
    }

    override fun nextFragment() {
        ++viewPager.currentItem
    }

    override fun backFragment() {
        --viewPager.currentItem
    }

    override fun runResultFragment() {

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, dataQuiz.getResultMessage())
        }
        startActivity(intent)
        finish()
    }

    override fun addAnswer(numberQuest: Int, numberAnswer: Int) {
        dataQuiz.addAnswer(numberQuest, numberAnswer)
    }

    override fun checkAnswersCount(): Boolean {
        return dataQuiz.checkAnswers()
    }

}