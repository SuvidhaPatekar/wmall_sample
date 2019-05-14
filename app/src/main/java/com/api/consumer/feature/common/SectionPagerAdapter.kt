package com.api.consumer.feature.common

import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import java.util.ArrayList

class SectionsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private val fragments = ArrayList<Fragment>()
  private val fragmentTitle = ArrayList<String>()

  fun addFragment(
    fragment: Fragment,
    title: String
  ) {
    fragments.add(fragment)
    fragmentTitle.add(title)
  }

  override fun getItem(position: Int): Fragment? {
    try {
      return fragments[position]
    } catch (e: IndexOutOfBoundsException) {
      return null
    }

  }

  fun clearAll() {
    fragments.clear()
    fragmentTitle.clear()
  }

  override fun saveState(): Parcelable? {
    return null
  }

  override fun getItemPosition(`object`: Any): Int {
    return PagerAdapter.POSITION_NONE
  }

  override fun getCount(): Int {
    return fragments.size
  }

  override fun getPageTitle(position: Int): CharSequence? {
    return fragmentTitle[position]
  }
}
