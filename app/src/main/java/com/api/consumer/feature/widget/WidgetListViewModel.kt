package com.api.consumer.feature.widget

import com.api.consumer.data.WMallDataManager
import com.api.consumer.feature.Item
import com.api.consumer.feature.Item.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class WidgetListViewModel {
  data class ViewState(
    val loading: Boolean,
    val listItems: List<Item> = listOf()
  )

  private val viewState = BehaviorSubject.create<ViewState>()

  fun getViewStateObservable() = viewState.hide()!!

    fun loadData(): Disposable {
      viewState.onNext(
          ViewState(loading = true)
      )

      return WMallDataManager.getWidgets()
          .subscribeOn(Schedulers.io())
          .observeOn(
              AndroidSchedulers.mainThread()
          ).subscribe { items, throwable ->
            throwable?.let {
              throwable.printStackTrace()
            } ?: viewState.onNext(
                ViewState(
                    loading = false, listItems = items
                )
            )
          }
    }

    fun searchData(input: String) = viewState.value.listItems
        .filterIsInstance<Product>()
        .filter { it.category.contains(input, ignoreCase = true) }
        .size
}
