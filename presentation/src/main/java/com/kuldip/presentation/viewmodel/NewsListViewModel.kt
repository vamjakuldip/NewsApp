package com.kuldip.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuldip.domain.model.CreateNewsRequest
import com.kuldip.domain.usecase.GetNewsArticlesUseCase
import com.kuldip.presentation.mapper.NewsArticleUIMapper
import com.kuldip.presentation.model.NewsArticleUIModel
import com.kuldip.presentation.util.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
    private val mapper: NewsArticleUIMapper,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private var page = 1
    private val pageSize = 10
    private var hasMore = true

    // Inner Types.
    enum class NewsListViewState {
        LOADING,
        DATA,
        NO_INTERNET,
        ERROR
    }

    // Properties.
    private val viewState: MutableLiveData<NewsListViewState> by lazy {
        MutableLiveData<NewsListViewState>()
    }
    private val newsArticles: MutableLiveData<List<NewsArticleUIModel>> by lazy {
        MutableLiveData<List<NewsArticleUIModel>>()
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    // View Observable Properties.
    val viewStateObservable: LiveData<NewsListViewState> get() = viewState
    val newsArticlesObservable: LiveData<List<NewsArticleUIModel>> get() = newsArticles


    // Init.
    init {
        fetchNewsArticles(CreateNewsRequest(pageSize = pageSize, page = page))
    }

    // ViewModel Methods.
    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    // Private Methods.
    private fun fetchNewsArticles(createNewsRequest: CreateNewsRequest) {
        viewState.value = NewsListViewState.LOADING
        val disposable = getNewsArticlesUseCase.execute(createNewsRequest)
            .map { newsArticles ->
                return@map newsArticles.map { mapper.mapToView(it) }
            }
            .subscribeOn(schedulerProvider.backgroundScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribe(this::fetchNewsArticlesSuccess, this::fetchNewsArticlesError)
        disposables.add(disposable)
    }

    fun refreshNews() {
        page = 1
        fetchNewsArticles(CreateNewsRequest(pageSize = pageSize, page = page))
    }

    private fun fetchNewsArticlesSuccess(articles: List<NewsArticleUIModel>) {
        viewState.value = NewsListViewState.DATA
        newsArticles.value = articles
        hasMore = !articles.isNullOrEmpty()
    }

    private fun fetchNewsArticlesError(e: Throwable) {
        if (e is HttpException) {
            viewState.value = NewsListViewState.ERROR
        } else {
            viewState.value = NewsListViewState.ERROR
        }
    }

    fun loadMore() {
        if (hasMore) {
            page++
            fetchNewsArticles(CreateNewsRequest(pageSize = pageSize, page = page))
        }
    }
}