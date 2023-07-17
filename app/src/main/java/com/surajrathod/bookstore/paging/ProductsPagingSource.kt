package com.surajrathod.bookstore.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.surajrathod.bookstore.model.ProductItem
import com.surajrathod.bookstore.repository.ProductRepository
import java.lang.Exception

class ProductsPagingSource(val repository: ProductRepository) : PagingSource<Int, ProductItem>() {

    override fun getRefreshKey(state: PagingState<Int, ProductItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductItem> {
        return try {
            val position = params.key ?: 1
            val r = repository.fetchLocalProducts().map {
                repository.toProductItem(it)
            }
            LoadResult.Page(
                data = r,
                nextKey = position + 1,
                prevKey = if (position == 1) null else position - 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

}