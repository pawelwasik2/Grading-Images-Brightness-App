package com

import java.io.File
import java.io.IOException
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


object Main extends App {
  //val file = new File("./bright/a.jpg")


  //val resourcePath = getClass.getResource("/bright/a.jpg")

  //println(resourcePath.getPath)

  val file = new File("C:\\Users\\Pawel\\Desktop\\IntellijProjects\\ScalacIntreview\\src\\main\\resources\\a.jpg")
  val image: BufferedImage = ImageIO.read(file)
  val width = image.getWidth
  val height = image.getHeight
  println(width, height)
  val singlePixel = image.getRGB(0, 0)

  val blue = singlePixel & 0xff
  val red = (singlePixel >> 16) & 0xff
  val green = (singlePixel >> 8) & 0xff
  val alpha = (singlePixel >> 24) & 0xff




  println(alpha, red, green, blue)


  for(i <- 0 to height){
    for(j <- 0 to width){

    }
  }
}
