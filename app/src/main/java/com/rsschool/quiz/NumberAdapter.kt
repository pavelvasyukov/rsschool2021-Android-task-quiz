package com.rsschool.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class NumberAdapter(private val fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    private val dao = DataObjectAccess()

    override fun getItemCount(): Int = dao.getSize()

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 ->  fragment.theme?.applyStyle(R.style.Theme_Quiz_Purple, true)
            1 ->  fragment.theme?.applyStyle(R.style.Theme_Quiz_Blue, true)
            2 ->  fragment.theme?.applyStyle(R.style.Theme_Quiz_Green, true)
            3 ->  fragment.theme?.applyStyle(R.style.Theme_Quiz_Yellow, true)
            4 ->  fragment.theme?.applyStyle(R.style.Theme_Quiz_Orange, true)
            else -> fragment.theme?.applyStyle(R.style.Theme_Quiz_First, true)
        }

        val fragment = BlankFragment()
        fragment.arguments = Bundle().apply {
            putSerializable(ARG_OBJECT, dao.getQuizObject(position))
        }
        return fragment
    }

}