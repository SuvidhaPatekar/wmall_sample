package com.api.consumer.feature.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style.STROKE
import android.graphics.Path
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.view.View
import com.api.consumer.R

class SegmentedProgressBar(context: Context, @Nullable attrs: AttributeSet) : View(
    context, attrs
) {
  private var paint: Paint
  private var fillingPaint: Paint
  private var startX = 0.0f
  private val diff = 20
  private var i = 0
  private var fillStartX = 0.0f
  private val path: Path
  private val fillingPath: Path
  private var count: Int = 0
  private var noOfPhoto = 0

  internal var animator: Runnable = object : Runnable {
    override fun run() {
      for (j in 0 until count) {
        if (i <= width / count) {
          i++
        } else {
          i = 0
          fillStartX += width / count + diff
          if (noOfPhoto <= count - 1) {
            noOfPhoto++
            IUpdatePhotoListener!!.changePhoto(noOfPhoto)
          }

        }
        invalidate()
      }
      postDelayed(this, 30)
    }
  }

  init {

    paint = Paint()
    fillingPaint = Paint()
    path = Path()
    fillingPath = Path()

    paint.strokeWidth = 10f
    paint.style = STROKE
    paint.color = Color.BLACK

    fillingPaint.strokeWidth = 10f
    fillingPaint.style = STROKE
    fillingPaint.color = Color.RED

    val array = context.theme
        .obtainStyledAttributes(attrs, R.styleable.SegmentedProgressBar, 0, 0)
    try {
      count = array.getInteger(R.styleable.SegmentedProgressBar_count, 3)
    } catch (e: Exception) {
      e.printStackTrace()
    } finally {
      array.recycle()
    }



    post(animator)

  }

  override fun onDraw(canvas: Canvas) {
    drawRect(canvas)
    drawFillingRect(canvas)
  }

  private fun drawRect(canvas: Canvas) {
    for (j in 0 until count) {
      path.moveTo(startX, 0.0f)
      path.lineTo(startX + width / count, 0.0f)
      startX += width / count + diff
      path.close()
      canvas.drawPath(path, paint)
      if (j == count - 1) {
        startX = 0.0f
      }
    }

  }

  private fun drawFillingRect(canvas: Canvas) {
    for (j in 0 until count) {
      fillingPath.moveTo(fillStartX, 0.0f)
      fillingPath.lineTo(fillStartX + i, 0.0f)
      canvas.drawPath(fillingPath, fillingPaint)
    }
  }

  interface UpdatePhotoListener {
    fun changePhoto(number: Int)
  }

  companion object {
    private var IUpdatePhotoListener: UpdatePhotoListener? = null

    fun registerUpdateListener(IUpdatePhotoListener: UpdatePhotoListener) {
      SegmentedProgressBar.IUpdatePhotoListener = IUpdatePhotoListener
    }
  }

}
