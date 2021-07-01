package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding



const val ARG_OBJECT = "object"

class BlankFragment : Fragment() {


    private var _listener: ActionListener? = null
    private val listener get() = _listener!!
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private var currentFragment = 0


    override fun onAttach(context: Context) {
        super.onAttach(context)
        _listener = context as ActionListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {


            val quizObject: QuizObject = get(ARG_OBJECT) as QuizObject


            currentFragment = quizObject.numberQuestion


            binding.toolbar.title = "Question ${quizObject.numberQuestion}"
            binding.question.text = quizObject.question
            binding.optionOne.text = quizObject.answers[0]
            binding.optionTwo.text = quizObject.answers[1]
            binding.optionThree.text = quizObject.answers[2]
            binding.optionFour.text = quizObject.answers[3]
            binding.optionFive.text = quizObject.answers[4]


            binding.nextButton.isEnabled = false

            if (currentFragment < 2) {
                binding.previousButton.isEnabled = false
                binding.toolbar.navigationIcon = null
            }


            if (currentFragment > 4) binding.nextButton.text = "Submit"
        }

        binding.nextButton.setOnClickListener {
            if (listener.checkAnswersCount()) listener.runResultFragment()
                else listener.nextFragment()
        }

        binding.previousButton.setOnClickListener {
            listener.backFragment()
        }

        binding.toolbar.setOnClickListener {
            listener.backFragment()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkId ->
            when (checkId) {
                binding.optionOne.id -> listener.addAnswer(currentFragment, 1)
                binding.optionTwo.id -> listener.addAnswer(currentFragment, 2)
                binding.optionThree.id -> listener.addAnswer(currentFragment, 3)
                binding.optionFour.id -> listener.addAnswer(currentFragment, 4)
                binding.optionFive.id -> listener.addAnswer(currentFragment, 5)
            }

            binding.nextButton.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}