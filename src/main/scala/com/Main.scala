package com

import java.io._
import java.io.IOException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object Main extends App {
  //get out directory, in directory and "cut-off" point from config file
  var srcDir = ""
  var resultDir = ""
  var border = 0
  val configFilename = "config.txt"
  try{
    val configFile = io.Source.fromFile(configFilename)
    val configLines = configFile.getLines().mkString(",").split(",")
    srcDir = configLines(0)
    resultDir = configLines(1)
    border = configLines(2).toInt
    configFile.close()
  }catch{
    case ioe: IOException => ioe.printStackTrace()
  }

  //get path of every single jpg and png from in directory
  val pathSrcDir = ".\\" + srcDir
  val folder = new File(pathSrcDir)
  val photos = folder.listFiles.filter(_.isFile).toList



  //iterate through every single pixel from photo and calculate average V for all of them
  for(file <- photos){
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
  }
}
