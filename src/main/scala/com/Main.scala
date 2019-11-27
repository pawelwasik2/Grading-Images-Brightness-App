package com

import java.io._
import java.io.IOException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File.separator
import com.typesafe.config._

object Main extends App {
  //load in, out dir and cutoff point from the conf file
  val conf = ConfigFactory.load()
  val srcDir = conf.getString("vars.in")
  val resultDir = conf.getString("vars.out")
  val border = conf.getInt("vars.point")

  //get path of every single jpg and png from in directory
  //val folder = new File(srcDir)
  val photos = new File(srcDir).listFiles.filter(_.isFile).toList

  print(photos)
  /*photos.map{el => {
    val image = ImageIO.read(el)
    for(i <- 0 until image.getWidth ){
      for(j <- 0 until image.getHeight){
        val singlePixel = image.getRGB(i, j)
        V = V + Calc.getMaxOfColors(singlePixel)
      }
      }
    0
    }}
    val width = image.getWidth
    val height = image.getHeight

    var V = 0.0

    for(i <- 0 until width ){
      for(j <- 0 until height){
        val singlePixel = image.getRGB(i, j)
        V = V + Calc.getMaxOfColors(singlePixel)
      }
    }

    //set score based on V from HSV color scale and make new name depends on it
    val vAVG = V/(width*height)
    val score = 100 - (vAVG * 100).round
    val newName = file.getName.split("\\.")
    if(score > border){
      newName(0) = newName(0) + "_dark_" + score
    }else{
      newName(0) = newName(0) + "_bright_" + score
    }

    //copy file to "out" dir with new name
    val source = file
    val destination = ".\\" + resultDir + "\\" + newName.mkString(".")
    val inChannel = new FileInputStream(source).getChannel
    val outChannel = new FileOutputStream(destination).getChannel
    outChannel.transferFrom(inChannel, 0, inChannel.size())
    inChannel.close()
    outChannel.close()
  )*/



  //iterate through every single pixel from photo and calculate average V for all of them
  /*for(file <- photos){
    val image: BufferedImage = ImageIO.read(file)
    val width = image.getWidth
    val height = image.getHeight

    var V = 0.0

    for(i <- 0 until width ){
      for(j <- 0 until height){
        val singlePixel = image.getRGB(i, j)
        V = V + Calc.getMaxOfColors(singlePixel)
      }
    }

    //set score based on V from HSV color scale and make new name depends on it
    val vAVG = V/(width*height)
    val score = 100 - (vAVG * 100).round
    val newName = file.getName.split("\\.")
    if(score > border){
      newName(0) = newName(0) + "_dark_" + score
    }else{
      newName(0) = newName(0) + "_bright_" + score
    }

    //copy file to "out" dir with new name
    val source = file
    val destination = ".\\" + resultDir + "\\" + newName.mkString(".")
    val inChannel = new FileInputStream(source).getChannel
    val outChannel = new FileOutputStream(destination).getChannel
    outChannel.transferFrom(inChannel, 0, inChannel.size())
    inChannel.close()
    outChannel.close()
  }*/
}
