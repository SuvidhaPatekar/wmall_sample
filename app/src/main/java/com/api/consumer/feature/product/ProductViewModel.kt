package com.api.consumer.feature.product

import com.api.consumer.data.WMallDataManager
import com.api.consumer.response.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class ProductViewModel {
  data class ViewState(
      val loading: Boolean,
      val categories: List<Category> = listOf()
  )

  val viewState = BehaviorSubject.create<ViewState>()

  fun getViewStateObservable() = viewState.hide()!!

  fun fetchCategory(): Disposable {
    viewState.onNext(ViewState(loading = true))

    return WMallDataManager.getCategoryDetails()
        .subscribeOn(Schedulers.io())
        .observeOn(
            AndroidSchedulers.mainThread()
        )
        .subscribe { response, throwable ->
          if (throwable == null) {
            viewState.onNext(ViewState(loading = false, categories = response))
          } else {
            throwable.printStackTrace()
            viewState.onNext(ViewState(loading = false))
          }
        }
  }
}
