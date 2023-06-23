package com.example.basicpaging3sampleappviews.ui.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicpaging3sampleappviews.R
import com.example.basicpaging3sampleappviews.databinding.FragmentListArticleBinding
import com.example.basicpaging3sampleappviews.utils.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListArticleFragment : Fragment(R.layout.fragment_list_article) {

    private val binding by viewBindings(FragmentListArticleBinding::bind)
    private val viewModel by viewModels<ListArticleViewModel>()
    private lateinit var adapter: ListArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObservers()
    }

    private fun setup() = with(binding) {
        adapter = ListArticleAdapter(viewLifecycleOwner) {

        }
        rvListArticles.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )
        rvListArticles.adapter = adapter

        val decoration = DividerItemDecoration(
            rvListArticles.context, DividerItemDecoration.VERTICAL
        )
        rvListArticles.addItemDecoration(decoration)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                /*viewModel.items.collectLatest {
                    adapter.submitData(it)
                }*/

                // OR USING THIS

                viewModel.articles.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }
    }
}