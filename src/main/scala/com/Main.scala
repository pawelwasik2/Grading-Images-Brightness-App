package com

import java.io._
import java.io.IOException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object Main extends App {
  var srcDir = ""
  var resultDir = ""
  val configFilename = "config.txt"
  try{
    val configFile = io.Source.fromFile(configFilename)
    val configLines = configFile.getLines().mkString(",").split(",")
    srcDir = configLines(0)
    resultDir = configLines(1)
    configFile.close()
  }catch{
    case ioe: IOException => ioe.printStackTrace()
  }

  val pathSrcDir = ".\\" + srcDir

  val border = 75

  val folder = new File(pathSrcDir)
  val photos = folder.listFiles.filter(_.isFile).toList
  println(photos)

  for(file <- photos){
    val image: BufferedImage = ImageIO.read(file)
    val width = image.getWidth
    val height = image.getHeight

    var redSUM, greenSUM, blueSUM = 0
    var V = 0.0
    var b = 0.0
    var g = 0.0
    var r = 0.0
    var max = 0.0

    for(i <- 0 until width ){
      for(j <- 0 until height){
        val singlePixel = image.getRGB(i, j)
        blueSUM = blueSUM + (singlePixel & 0xff)
        greenSUM = greenSUM + ((singlePixel >> 8) & 0xff)
        redSUM = redSUM + ((singlePixel >> 16) & 0xff)
        b = (singlePixel & 0xff)
        g = ((singlePixel >> 8) & 0xff)
        r = ((singlePixel >> 16) & 0xff)
        max = (r/255 max g/255) max b/255
        V = V + max
      }
    }

    //nie za duze inty tu beda?

    val blueAVG = blueSUM/(width*height)
    val greenAVG = greenSUM/(width*height)
    val redAVG = redSUM/(width*height)
    val vAVG = V/(width*height)

    println("PLIK: " + file + " R, G, B: " + redAVG, greenAVG, blueAVG, " V: " + vAVG)

    val rgbAVG = (blueAVG + greenAVG + redAVG)/3
    //val score = 100 - (rgbAVG*100)/255
    val score = 100 - (vAVG * 100).round
    //println(score)

    val newName = file.getName.split("\\.")

    if(score > border){
      println("dark " + score)
      newName(0) = newName(0) + "_dark_" + score
    }else{
      println("bright " + score)
      newName(0) = newName(0) + "_bright_" + score
    }

    val source = file
    val destination = ".\\" + resultDir + "\\" + newName.mkString(".")
    val inChannel = new FileInputStream(source).getChannel()
    val outChannel = new FileOutputStream(destination).getChannel()
    outChannel.transferFrom(inChannel, 0, inChannel.size())
    inChannel.close()
    outChannel.close()
  }
}


