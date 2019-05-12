package com.api.wmall.feature

import com.api.wmall.response.Response
import io.reactivex.subjects.BehaviorSubject

class WMallViewModel {
  data class ViewState(
    val loading: Boolean,
    val response: Response? = null
  )

  private val viewState = BehaviorSubject.create<ViewState>()

  fun getViewStateObservable() = viewState.hide()!!
}
