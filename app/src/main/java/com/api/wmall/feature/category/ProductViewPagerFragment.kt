package com.api.wmall.feature.category

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.api.wmall.R
import com.api.wmall.R.string
import kotlinx.android.synthetic.main.fragment_product_view_pager.btnShareWhatsApp
import kotlinx.android.synthetic.main.fragment_product_view_pager.btnViewDetails
import kotlinx.android.synthetic.main.fragment_product_view_pager.ivProduct

class ProductViewPagerFragment : Fragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
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
  }

  private fun showProductDetails() {
    ProductDetailFragment.newInstance()
        .show(activity?.supportFragmentManager, "product_detail_fragment")
  }

  private fun shareOnWhatsApp() {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.setPackage("com.whatsapp")
    intent.putExtra(Intent.EXTRA_TEXT, getString(string.whatsapp_share_msg))
    try {
      activity?.startActivity(intent)
    } catch (ex: android.content.ActivityNotFoundException) {
      Toast.makeText(activity, "Whatsapp have not been installed.", Toast.LENGTH_SHORT)
          .show()
    }
  }

  companion object {
    @JvmStatic fun newInstance() =
      ProductViewPagerFragment().apply { arguments = Bundle().apply {} }
  }
}
