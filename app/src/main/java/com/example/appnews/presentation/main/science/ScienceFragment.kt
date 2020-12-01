package com.example.appnews.presentation.main.science

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.R
import com.example.appnews.di.ContextModule
import com.example.appnews.di.DaggerAppComponent
import com.example.appnews.presentation.adapters.MainAdapter
import com.example.appnews.utils.Resource
import com.example.appnews.utils.factories.ViewModelProviderFactory
import com.example.appnews.utils.ext.injectViewModel
import kotlinx.android.synthetic.main.entertainment_fragment.*
import kotlinx.android.synthetic.main.science_fragment.*
import kotlinx.android.synthetic.main.science_fragment.loading

class ScienceFragment : Fragment(R.layout.science_fragment) {

    private var isLoading = false

    private lateinit var newsAdapter: MainAdapter
    private lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var scienceViewModel: ScienceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appComponent = DaggerAppComponent.builder().contextModule(ContextModule(requireContext())).build()
        viewModelProviderFactory = appComponent.getViewModelFactory()
        scienceViewModel = injectViewModel(viewModelProviderFactory)

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        observeMovies()
    }

    private fun observeMovies() {
        scienceViewModel.news.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("ScienceFragment", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        loading.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        loading.visibility = View.VISIBLE
        isLoading = true
    }

    private fun setupRecyclerView() {
        newsAdapter = MainAdapter()
        science_recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}