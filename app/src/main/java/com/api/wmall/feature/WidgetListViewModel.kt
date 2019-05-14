package com.api.wmall.feature

import com.api.wmall.data.WMallDataManager
import com.api.wmall.feature.Item.Product
import com.api.wmall.feature.Item.Title
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class WidgetListViewModel {
  data class ViewState(
    val loading: Boolean,
    val listItems: List<Item>? = null
  )

  private val viewState = BehaviorSubject.create<ViewState>()

  fun getViewStateObservable() = viewState.hide()!!

    fun loadData(): Disposable {
      viewState.onNext(ViewState(loading = true))

      return WMallDataManager.getWidgets()
          .subscribeOn(Schedulers.io())
          .observeOn(
              AndroidSchedulers.mainThread()
          ).subscribe { items, throwable ->
            throwable?.let {
              throwable.printStackTrace()
            } ?: viewState.onNext(ViewState(loading = false, listItems = items))
          }
    }

  fun searchData(input: String) : Int? {
    return viewState.value.listItems?.filter {
      item: Item ->  if(item is Product){item.category.contains(input, ignoreCase = true)}else{(item as Title).title.contains(input, ignoreCase = true)}
    }?.size
  }
}
