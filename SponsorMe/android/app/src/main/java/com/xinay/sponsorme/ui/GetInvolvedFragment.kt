package com.xinay.sponsorme.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xinay.sponsorme.R

class GetInvolvedFragment : Fragment() {

    companion object {
        fun newInstance(): GetInvolvedFragment {
            return GetInvolvedFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_get_involved, container, false)
    }
}