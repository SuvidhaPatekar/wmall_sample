package com.api.consumer.feature.product

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.api.consumer.R
import com.api.consumer.feature.common.SectionsPagerAdapter
import com.api.consumer.feature.common.SegmentedProgressBar
import com.api.consumer.response.Category
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.progressBar
import kotlinx.android.synthetic.main.activity_product.toolbar
import kotlinx.android.synthetic.main.content_product.backgroundImage
import kotlinx.android.synthetic.main.content_product.indicator
import kotlinx.android.synthetic.main.content_product.viewPager

class ProductActivity : AppCompatActivity(), SegmentedProgressBar.UpdatePhotoListener {
  lateinit var viewModel: ProductViewModel
  lateinit var viewPagerAdapter: SectionsPagerAdapter
  private val disposable = CompositeDisposable()
  lateinit var categoryList: List<Category>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_product)
    setSupportActionBar(toolbar)
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

    viewPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
    viewPagerAdapter.registerDataSetObserver(indicator.dataSetObserver)
    viewModel = ProductViewModel()

    toolbar.setNavigationOnClickListener {
      onBackPressed()
    }

    disposable.add(viewModel.getViewStateObservable().subscribe {
      if (it.loading) {
        progressBar.visibility = View.VISIBLE
      } else {
        progressBar.visibility = View.GONE
      }

      if (it.categories != null) {
        categoryList = it.categories
        //showAllImages(it.categories)
      }
    })
    SegmentedProgressBar.registerUpdateListener(this)
    disposable.add(viewModel.loadData())
  }

  private fun showAllImages(categories: List<Category>) {
    viewPagerAdapter.clearAll()
    for (category in categories) {
      val productViewPagerFragment = ProductViewPagerFragment.newInstance(category)
      viewPagerAdapter.addFragment(
          productViewPagerFragment, category.title
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

  override fun changePhoto(number: Int) {
      Picasso.get().load(categoryList[number].image.mobile).into(backgroundImage)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }

  fun setToolBarTitle(title: String){
    toolbar.title = title
  }
}
