package com

import java.io.File
import java.io.IOException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object Main extends App {
  val border = 75

  val folder = new File(".\\too_dark")
  val photos = folder.listFiles.filter(_.isFile).toList
  println(photos)

  for(file <- photos){
    val image: BufferedImage = ImageIO.read(file)
    val width = image.getWidth
    val height = image.getHeight
    //println(width, height)

    var redSUM, greenSUM, blueSUM = 0

    for(i <- 0 to width - 1){
      for(j <- 0 to height - 1){
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

    if(score > border){
      println("dark " + score)
    }else{
      println("bright " + score)
    }
  }



  //val file = new File("C:\\Users\\Pawel\\Desktop\\IntellijProjects\\ScalacIntreview\\src\\main\\resources\\b.jpg")

}
