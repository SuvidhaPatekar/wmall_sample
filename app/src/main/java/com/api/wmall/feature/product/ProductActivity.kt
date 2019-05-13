package com.api.wmall.feature.product

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.api.wmall.R
import com.api.wmall.feature.common.SectionsPagerAdapter
import com.api.wmall.response.Category
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.progressBar
import kotlinx.android.synthetic.main.activity_product.toolbar
import kotlinx.android.synthetic.main.content_product.indicator
import kotlinx.android.synthetic.main.content_product.viewPager

class ProductActivity : AppCompatActivity() {
  lateinit var viewModel: ProductViewModel
  lateinit var viewPagerAdapter: SectionsPagerAdapter
  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_product)
    setSupportActionBar(toolbar)
    viewPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
    viewPagerAdapter.registerDataSetObserver(indicator.dataSetObserver)

    viewModel = ProductViewModel()

    disposable.add(viewModel.getViewStateObservable().subscribe {
      if (it.loading) {
        progressBar.visibility = View.VISIBLE
      } else {
        progressBar.visibility = View.GONE
      }

      if (it.categories != null) {
        showAllImages(it.categories)
      }
    })
    disposable.add(viewModel.loadData())
  }

  private fun showAllImages(categories: List<Category>) {
    viewPagerAdapter.clearAll()
    for (category in categories) {
      val productViewPagerFragment = ProductViewPagerFragment.newInstance(category)
      viewPagerAdapter.addFragment(
          productViewPagerFragment, category.slug
      )
    }

    if (viewPagerAdapter.count == 1) {
      indicator.visibility = View.GONE
    } else {
      indicator.visibility = View.VISIBLE
    }
    indicator.setViewPager(viewPager)
    viewPager.adapter = viewPagerAdapter
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }

}