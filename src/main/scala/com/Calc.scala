package com

object Calc {
  def getMaxOfColors(rgb: Int): Double ={
    var b, g, r, mx = 0.0

    b = rgb & 0xff
    g = (rgb >> 8) & 0xff
    r = (rgb >> 16) & 0xff

    mx = (r/255 max g/255) max b/255
    mx = (mx * 10000).round / 10000.toDouble

    mx
  }
}
