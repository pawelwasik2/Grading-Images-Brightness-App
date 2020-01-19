package com

import java.io._
import javax.imageio.ImageIO
import java.io.File.separator
import com.typesafe.config._
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption._
//import org.apache.commons.io.FilenameUtils


object Main extends App {
  val conf = ConfigFactory.load()
  val photos = new File(conf.getString("vars.in")).listFiles.filter(_.isFile).toList

  photos.foreach{el => {
    val image = ImageIO.read(el)
    val s = (0 until image.getWidth())
      .map(x => (0 until image.getHeight())
        .map(y => getMaxOfColors(image.getRGB(x, y))).sum).sum
    val vAVG = s/(image.getWidth*image.getHeight)
    val newName = el.getName.split("\\.")
    if(calcScore(vAVG) > conf.getInt("vars.point")){
      newName(0) = newName(0) + "_dark_" + calcScore(vAVG)
    }else{
      newName(0) = newName(0) + "_bright_" + calcScore(vAVG)
    }

    val destination = conf.getString("vars.out") + separator + newName.mkString(".")
    Files.copy(Paths.get(el.getPath), Paths.get(destination), REPLACE_EXISTING)
  }}

  def calcScore(vAVG: Double): Int ={
    (100 - (vAVG * 100).round).toInt
  }

  def getMaxOfColors(rgb: Int): Int ={

    def getBlueFromRGB(rgb: Int): Int ={
      rgb & 0xff
    }
    def getRedFromRGB(rgb: Int): Int ={
      (rgb >> 16) & 0xff
    }
    def getGreenFromRGB(rgb: Int): Int ={
      (rgb >> 8) & 0xff
    }

    (getRedFromRGB(rgb)/255 max getGreenFromRGB(rgb)/255) max getBlueFromRGB(rgb)/255
  }
}
