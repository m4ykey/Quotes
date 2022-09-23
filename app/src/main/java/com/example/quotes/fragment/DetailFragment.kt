package com.example.quotes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quotes.databinding.FragmentDetailBinding
import com.example.quotes.model.Quotes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var quotes : Quotes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let {
            DetailFragmentArgs.fromBundle(it).also { args ->
                quotes = args.quote
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtAuthorDetail.text = " -  ${quotes.author}"
        binding.txtQuoteDetail.text = quotes.text

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}