package com.api.wmall.feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.api.wmall.R
import com.api.wmall.R.string
import com.api.wmall.feature.product.ProductActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_product.toolbar
import kotlinx.android.synthetic.main.activity_wmall.*
import kotlinx.android.synthetic.main.content_wmall.*

class WidgetListActivity : AppCompatActivity(), WidgetListAdapter.OnClickListener {
    lateinit var adapter: WidgetListAdapter
    lateinit var viewModel: WidgetListViewModel
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wmall)
        setSupportActionBar(toolbar)
        toolbar.title = getString(string.categories)
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp)
        adapter = WidgetListAdapter(this)
        viewModel = WidgetListViewModel()

        rvProducts.adapter = adapter

        disposable.add(viewModel.getViewStateObservable().subscribe {
            progressBar.visibility = if (it.loading) {
                View.VISIBLE
            } else {
                View.GONE
            }

            if (it.listItems != null) {
                adapter.items = it.listItems
            }
        })
        disposable.add(viewModel.loadData())

        btnSearch.setOnClickListener {
            if (!search.text.toString().isBlank()) {
                Toast.makeText(this, " ${viewModel.searchData(search.text.toString())} results found for the search", Toast.LENGTH_SHORT).show()
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
