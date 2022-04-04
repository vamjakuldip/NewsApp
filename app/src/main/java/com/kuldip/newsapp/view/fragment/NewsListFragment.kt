package com.kuldip.newsapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kuldip.newsapp.R
import com.kuldip.newsapp.view.adapter.NewsListAdapter
import com.kuldip.newsapp.view.view.ErrorView
import com.kuldip.presentation.viewmodel.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    @Inject
    lateinit var adapter: NewsListAdapter

    lateinit var rvNewsArticles: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var vgEmptyViewContainer: ViewGroup
    lateinit var pbProgress: ProgressBar
    lateinit var errorView: ErrorView
    private val viewModel: NewsListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        rvNewsArticles = view.findViewById(R.id.news_articles)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        vgEmptyViewContainer = view.findViewById(R.id.error_view_container)
        pbProgress = view.findViewById(R.id.progress)

        initializeViews()
        setUpViewModelBindings()

        return view
    }

    // Private Methods.
    private fun initializeViews() {
        val layoutManager = LinearLayoutManager(context)
        rvNewsArticles.layoutManager = layoutManager
        rvNewsArticles.adapter = adapter

        errorView = ErrorView(requireContext(), R.drawable.ic_error_outline, null, null)
        vgEmptyViewContainer.addView(errorView)
        vgEmptyViewContainer.visibility = View.GONE
        pbProgress.visibility = View.GONE
        swipeRefresh.setOnRefreshListener {
            viewModel.refreshNews()
        }
        rvNewsArticles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMore()
                }
            }
        })
    }

    private fun setUpViewModelBindings() {
        bindViewState()
        bindNewsArticles()
    }

    private fun bindViewState() {
        viewModel.viewStateObservable.observe(viewLifecycleOwner, { state ->
            swipeRefresh.isRefreshing = false
            when (state) {
                NewsListViewModel.NewsListViewState.LOADING -> updateViewForLoadingState()
                NewsListViewModel.NewsListViewState.DATA -> updateViewForDataState()
                NewsListViewModel.NewsListViewState.NO_INTERNET -> updateViewForNoInternetState()
                NewsListViewModel.NewsListViewState.ERROR -> updateViewForErrorState()
            }
        })
    }

    private fun bindNewsArticles() {
        viewModel.newsArticlesObservable.observe(viewLifecycleOwner, { newsArticles ->
            adapter.newsArticles.addAll(newsArticles)
            adapter.notifyDataSetChanged()
        })
    }

    private fun updateViewForLoadingState() {
        rvNewsArticles.visibility = View.GONE
        vgEmptyViewContainer.visibility = View.GONE
        pbProgress.visibility = View.VISIBLE
    }

    private fun updateViewForDataState() {
        rvNewsArticles.visibility = View.VISIBLE
        vgEmptyViewContainer.visibility = View.GONE
        pbProgress.visibility = View.GONE
    }

    private fun updateViewForNoInternetState() {
        rvNewsArticles.visibility = View.GONE
        errorView.updateView(
            R.drawable.ic_error_outline,
            getString(R.string.no_network_error_title),
            getString(R.string.no_network_error_message)
        )
        vgEmptyViewContainer.visibility = View.VISIBLE
        pbProgress.visibility = View.GONE
    }

    private fun updateViewForErrorState() {
        rvNewsArticles.visibility = View.GONE
        errorView.updateView(
            R.drawable.ic_error_outline,
            getString(R.string.unknown_error_title),
            getString(R.string.unknown_error_message)
        )
        vgEmptyViewContainer.visibility = View.VISIBLE
        pbProgress.visibility = View.GONE
    }
}
