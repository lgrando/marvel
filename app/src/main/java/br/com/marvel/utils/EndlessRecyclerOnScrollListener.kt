package br.com.marvel.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {

    private var isUpdatingAdapter = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as GridLayoutManager
        if (dy > 0) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

            if (isUpdatingAdapter) {
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    isUpdatingAdapter = false
                    onLoadMore()
                }
            }
        }
    }

    abstract fun onLoadMore()
}