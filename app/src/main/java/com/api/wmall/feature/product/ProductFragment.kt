package com.api.wmall.feature.product

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.api.wmall.R
import com.api.wmall.feature.common.SectionsPagerAdapter
import com.api.wmall.response.Category
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_product.indicator
import kotlinx.android.synthetic.main.fragment_product.progressBar
import kotlinx.android.synthetic.main.fragment_product.viewPager

lateinit var viewModel: ProductViewModel
lateinit var viewPagerAdapter: SectionsPagerAdapter
private val disposable = CompositeDisposable()

class ProductFragment : Fragment() {
  private lateinit var categoryList: List<Category>
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_product, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ProductViewModel()
    viewPagerAdapter = SectionsPagerAdapter(activity!!.supportFragmentManager)
    viewModel.loadData()

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

  }

  private fun showAllImages(categories: List<Category>) {
    viewPagerAdapter.clearAll()
    categoryList = categories
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

  companion object {
    @JvmStatic
    fun newInstance() =
      ProductFragment().apply {
      }
  }
}
