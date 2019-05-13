package com.api.wmall.feature

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.api.wmall.R
import com.api.wmall.feature.category.ProductViewPagerFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_wmall.progressBar
import kotlinx.android.synthetic.main.activity_wmall.toolbar
import kotlinx.android.synthetic.main.content_wmall.rvProducts

class WMallActivity : AppCompatActivity() {

  lateinit var adapter: WMallAdapter
  lateinit var viewModel: WMallViewModel
  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wmall)
    setSupportActionBar(toolbar)

    adapter = WMallAdapter()
    viewModel = WMallViewModel()

    rvProducts.adapter = adapter

    disposable.add(viewModel.getViewStateObservable().subscribe {
      if (it.loading) {
        progressBar.visibility = View.VISIBLE
      } else {
        progressBar.visibility = View.GONE
      }

      if (it.listItems != null) {
        adapter.setItems(it.listItems)
      }
    })

    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    ft.replace(R.id.container, ProductViewPagerFragment.newInstance(), "NewFragmentTag")
    ft.commit()

    //disposable.add(viewModel.loadData())
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }
}
