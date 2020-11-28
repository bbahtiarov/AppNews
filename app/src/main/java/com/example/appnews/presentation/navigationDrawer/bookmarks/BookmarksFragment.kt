package com.example.appnews.presentation.navigationDrawer.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.di.ContextModule
import com.example.appnews.di.DaggerAppComponent
import com.example.appnews.presentation.adapters.MainAdapter
import com.example.appnews.utils.factories.ViewModelProviderFactory
import com.example.appnews.utils.injectViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.bookmarks_fragment.*

class BookmarksFragment : Fragment(R.layout.bookmarks_fragment) {

    private lateinit var newsAdapter: MainAdapter
    private lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var bookmarksViewModel: BookmarksViewModel

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appComponent = DaggerAppComponent.builder().contextModule(ContextModule(requireContext())).build()
        viewModelProviderFactory = appComponent.getViewModelFactory()
        bookmarksViewModel = injectViewModel(viewModelProviderFactory)

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {movie ->
            val bundle = Bundle().apply {
                putSerializable("article", movie)
            }
            findNavController().navigate(R.id.detailFragment, bundle)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val article = newsAdapter.differ.currentList[position]
                bookmarksViewModel.deleteArticle(article)
                Snackbar.make(view, getString(R.string.delete_article), Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        bookmarksViewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(bookmarks_recyclerView)
        }

        bookmarksViewModel.news.observe(viewLifecycleOwner, Observer {  movies ->
            newsAdapter.differ.submitList(movies)
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = MainAdapter()
        bookmarks_recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}