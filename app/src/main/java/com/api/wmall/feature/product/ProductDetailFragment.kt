package com.api.wmall.feature.product

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.api.wmall.R
import kotlinx.android.synthetic.main.fragment_product_detail.btnBookNow
import kotlinx.android.synthetic.main.fragment_product_detail.ivCross
import kotlinx.android.synthetic.main.fragment_product_detail.tetAddress

class ProductDetailFragment : BottomSheetDialogFragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_product_detail, container, false)
  }

  override fun onViewCreated(
    view: View
      ,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    btnBookNow.setOnClickListener {
      if (tetAddress.text.toString().isBlank()) {
        showToast(R.string.address_error)
      } else {
        showToast(R.string.order_placed)
        dismiss()
      }
    }

    ivCross.setOnClickListener {
      dismiss()
    }
  }

  override fun onStart() {
    super.onStart()
    dialog?.also {
      val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)
      bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
      val behavior = BottomSheetBehavior.from<View>(bottomSheet)
      behavior.peekHeight = 700
      view?.requestLayout()
    }
  }

  private fun showToast(stringId: Int) {
    Toast.makeText(context, getString(stringId), Toast.LENGTH_SHORT)
        .show()
  }

  companion object {
    @JvmStatic
    fun newInstance() = ProductDetailFragment().apply {}
  }
}
