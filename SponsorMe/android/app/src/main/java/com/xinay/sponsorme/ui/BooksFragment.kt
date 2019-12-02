package com.xinay.sponsorme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xinay.sponsorme.R

class BooksFragment : Fragment() {

    companion object {
        fun newInstance(): BooksFragment {
            return BooksFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }
}