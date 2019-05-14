package com.api.wmall.feature.product

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.api.wmall.R
import com.api.wmall.R.string
import com.api.wmall.response.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_view_pager.btnShareWhatsApp
import kotlinx.android.synthetic.main.fragment_product_view_pager.btnViewDetails
import kotlinx.android.synthetic.main.fragment_product_view_pager.ivProduct

private const val CATEGORY = "CATEGORY"

class ProductViewPagerFragment : Fragment() {
  private lateinit var category: Category

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      category = arguments!!.getSerializable(CATEGORY) as Category
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_product_view_pager, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    btnViewDetails.setOnClickListener {
      showProductDetails()
    }

    ivProduct.setOnClickListener {
      showProductDetails()
    }

    btnShareWhatsApp.setOnClickListener {
      shareOnWhatsApp()
    }
    (activity as ProductActivity).setToolBarTitle(category.title)
    setData()
  }

  private fun showProductDetails() {
    val productDetailFragment = ProductDetailFragment.newInstance(category)
    productDetailFragment.show(activity?.supportFragmentManager, "productDetailFragment")
  }

  private fun setData() {
    Picasso.get()
        .load(category.image.mobile)
        .into(ivProduct)
  }

  private fun shareOnWhatsApp() {
    Intent(Intent.ACTION_SEND).apply {
      type = "text/plain"
      setPackage("com.whatsapp")
      putExtra(
          Intent.EXTRA_TEXT,
          "Please check out this amazing ${category.title} product on WMall App | ${getString(
              R.string.app_link
          )}"
      )
    }
        .let {
          try {
            startActivity(it)
          } catch (ex: ActivityNotFoundException) {
            Toast.makeText(activity, getString(string.whatsapp_error), Toast.LENGTH_SHORT)
                .show()
          }
        }
  }

  companion object {
    @JvmStatic
    fun newInstance(category: Category) =
      ProductViewPagerFragment().apply {
        arguments = Bundle().apply {
          putSerializable(CATEGORY, category)
        }
      }
  }
}
