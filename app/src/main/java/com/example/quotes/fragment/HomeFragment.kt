package com.example.quotes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.R
import com.example.quotes.adapter.QuoteAdapter
import com.example.quotes.databinding.FragmentHomeBinding
import com.example.quotes.mvvm.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var quoteAdapter: QuoteAdapter
    private val viewModel : QuoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.observeQuotesLiveData().observe(viewLifecycleOwner) { quote ->
            quoteAdapter.differ.submitList(quote)
        }

        onItemClick()
    }

    private fun onItemClick() {
        quoteAdapter.onItemClick = {
            val bundle = Bundle().apply {
                putParcelable("quote", it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }

    private fun setupRecyclerView() {
        quoteAdapter = QuoteAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = quoteAdapter
        }
    }
}