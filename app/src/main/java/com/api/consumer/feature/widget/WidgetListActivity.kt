package com.api.consumer.feature.widget

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.api.consumer.R
import com.api.consumer.R.string
import com.api.consumer.feature.product.ProductActivity
import com.api.consumer.feature.widget.WidgetListAdapter.OnClickListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.toolbar
import kotlinx.android.synthetic.main.activity_wmall.*
import kotlinx.android.synthetic.main.content_wmall.*

class WidgetListActivity : AppCompatActivity(),
    OnClickListener {
  private lateinit var adapter: WidgetListAdapter
  private lateinit var viewModel: WidgetListViewModel
  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wmall)
    setSupportActionBar(toolbar)
    toolbar.title = getString(string.categories)
    adapter = WidgetListAdapter(this)
    viewModel = WidgetListViewModel()
    rvProducts.isNestedScrollingEnabled = true
    rvProducts.adapter = adapter

    disposable.add(viewModel.getViewStateObservable().subscribe {
      progressBar.visibility = if (it.loading) {
        View.VISIBLE
      } else {
        View.GONE
      }

      adapter.items = it.listItems
    })
    disposable.add(viewModel.loadData())

    btnSearch.setOnClickListener {
      if (!search.text.toString().isBlank()) {
        Toast.makeText(this,
            " ${viewModel.searchData(search.text.toString())} results found for the search",
            Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun openProductActivity() {
    startActivity(Intent(this, ProductActivity::class.java))
  }

  override fun onCategoryClicked() {
    openProductActivity()
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }
}
