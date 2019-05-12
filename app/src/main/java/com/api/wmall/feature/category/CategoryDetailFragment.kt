package com.api.wmall.feature.category

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.api.wmall.R
import com.api.wmall.R.string
import kotlinx.android.synthetic.main.fragment_category_detail.btnBookNow
import kotlinx.android.synthetic.main.fragment_category_detail.tetAddress
import kotlinx.android.synthetic.main.fragment_category_detail.tlAddress

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CategoryDetailFragment : BottomSheetDialogFragment() {
  private var param1: String? = null
  private var param2: String? = null
  private var listener: OnFragmentInteractionListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      param1 = it.getString(ARG_PARAM1)
      param2 = it.getString(ARG_PARAM2)
    }

    btnBookNow.setOnClickListener {
      if (tetAddress.text.toString().isBlank()) {
        showToast(R.string.address_error)
      }else {
        showToast(R.string.order_placed)
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_category_detail, container, false)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is OnFragmentInteractionListener) {
      listener = context
    } else {
      throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
    }
  }

  override fun onDetach() {
    super.onDetach()
    listener = null
  }

  private fun showToast(stringId : Int) {
    Toast.makeText(context, getString(stringId), Toast.LENGTH_SHORT)
        .show()
  }

  interface OnFragmentInteractionListener {
    fun onFragmentInteraction(uri: Uri)
  }

  companion object {
    @JvmStatic
    fun newInstance(
      param1: String,
      param2: String
    ) =
      CategoryDetailFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}
