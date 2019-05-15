package com.api.consumer.feature.product

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.api.consumer.R
import com.api.consumer.feature.common.SectionsPagerAdapter
import com.api.consumer.response.Category
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.content_product.*

class ProductActivity : AppCompatActivity() {
  private lateinit var viewModel: ProductViewModel
  private lateinit var viewPagerAdapter: SectionsPagerAdapter
  private val disposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_product)
    setSupportActionBar(toolbar)
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

    viewPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
    viewModel = ProductViewModel()

    toolbar.setNavigationOnClickListener {
      onBackPressed()
    }

    disposable.add(viewModel.getViewStateObservable().subscribe {
      progressBar.visibility = if (it.loading) {
        View.VISIBLE
      } else {
        View.GONE
      }
      showAllImages(it.categories)
    })
    disposable.add(viewModel.fetchCategory())

    viewPager.adapter = viewPagerAdapter
    indicator.setViewPager(viewPager)
    viewPagerAdapter.registerDataSetObserver(indicator.dataSetObserver)
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        setToolBarTitle(viewModel.viewState.value.categories[position].title)
      }

      override fun onPageSelected(position: Int) {

      }
    })
  }

  private fun showAllImages(categories: List<Category>) {
    viewPagerAdapter.clearAll()
    categories.forEach {
      viewPagerAdapter.addFragment(ProductViewPagerFragment.newInstance(it), it.title)
    }

    indicator.visibility = if (viewPagerAdapter.count == 1) {
      View.GONE
    } else {
      View.VISIBLE
    }
    viewPagerAdapter.notifyDataSetChanged()
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }

  fun setToolBarTitle(title: String){
    toolbar.title = title
  }
}
