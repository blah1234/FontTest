package com.example.fonttest.ui.main

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fonttest.R
import com.example.fonttest.databinding.FragmentMainBinding
import android.graphics.PorterDuffXfermode




/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
            setContext(requireContext())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        val textView: TextView = binding.sectionLabel

        initTextColor()

        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    private fun initTextColor() {
        val textView: TextView = binding.sectionLabel

        val tab = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1

        if (tab == 0) {
            textView.setTextColor(requireContext().resources.getColor(R.color.player_vibrant_tertiary))
        } else {
            textView.setTextColor(Color.WHITE)
            textView.alpha = requireContext().resources.getFloat(R.dimen.player_vibrant_tertiary_alpha)
        }

        if (tab == 2) {
            val layerPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)
            layerPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
            textView.setLayerType(View.LAYER_TYPE_SOFTWARE, layerPaint)
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}