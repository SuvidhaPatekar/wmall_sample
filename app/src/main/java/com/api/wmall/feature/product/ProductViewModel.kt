package com.api.wmall.feature.product

import com.api.wmall.data.WMallDataManager
import com.api.wmall.response.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class ProductViewModel {
    data class ViewState(
        val loading: Boolean,
        val categories: List<Category>? = null
    )

    private val viewState = BehaviorSubject.create<ViewState>()

    fun getViewStateObservable() = viewState.hide()!!

    fun loadData(): Disposable {
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
