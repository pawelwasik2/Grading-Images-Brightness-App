package com

import java.io._
import java.io.IOException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object Main extends App {
  val srcDir = "bright"
  val pathSrcDir = ".\\" + srcDir
  val resultDir = "result"

  val border = 75

  val folder = new File(pathSrcDir)
  val photos = folder.listFiles.filter(_.isFile).toList
  println(photos)

  for(file <- photos){
    val image: BufferedImage = ImageIO.read(file)
    val width = image.getWidth
    val height = image.getHeight

    var redSUM, greenSUM, blueSUM = 0

    for(i <- 0 until width ){
      for(j <- 0 until height){
        val singlePixel = image.getRGB(i, j)
        blueSUM = blueSUM + (singlePixel & 0xff)
        greenSUM = greenSUM + ((singlePixel >> 8) & 0xff)
        redSUM = redSUM + ((singlePixel >> 16) & 0xff)
        //println("avg after every single px" + redAVG, greenAVG, blueAVG)
        //val alpha = (singlePixel >> 24) & 0xff
      }
    }

    //nie za duze inty tu beda?

    val blueAVG = blueSUM/(width*height)
    val greenAVG = greenSUM/(width*height)
    val redAVG = redSUM/(width*height)

    println("PLIK: " + file + " R, G, B: " + redAVG, greenAVG, blueAVG)

    val rgbAVG = (blueAVG + greenAVG + redAVG)/3
    val score = 100 - (rgbAVG*100)/255

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


