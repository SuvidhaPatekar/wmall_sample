package com.api.wmall.feature

import com.api.wmall.data.WMallDataManager
import com.api.wmall.response.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class WMallViewModel {
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
        )
        .subscribe { response, throwable ->
          if (throwable == null) {
            viewState.onNext(ViewState(loading = false))
          } else {
            throwable.printStackTrace()
          }
        }
  }
}
