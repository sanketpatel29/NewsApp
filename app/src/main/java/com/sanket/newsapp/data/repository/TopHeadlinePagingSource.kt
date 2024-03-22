package com.sanket.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.Constants.DEFAULT_COUNTRY
import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.model.ApiArticle
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class TopHeadlinePagingSource(private val networkService: NetworkService) :
    PagingSource<Int, ApiArticle>() {
    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        return try {

            val page = params.key ?: Constants.Paging.INITIAL_PAGE

            val response = networkService.getTopHeadlines(
                country = DEFAULT_COUNTRY,
                page = page,
                pageSize = Constants.Paging.PAGE_SIZE
            )

            LoadResult.Page(
                data = response.apiArticles,
                prevKey = if (page == Constants.Paging.INITIAL_PAGE) null else page - 1,
                nextKey = if (response.apiArticles.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}