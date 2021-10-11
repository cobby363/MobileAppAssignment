package org.wit.VideoManagement.console.main

import VideoMemStore
import mu.KotlinLogging
import org.wit.VideoManagement.console.controllers.PlacemarkController
//import org.wit.placemark.console.models.PlacemarkMemStore
import org.wit.VideoManagement.console.models.VideoModel
import org.wit.VideoManagement.console.views.VideoView

private val logger = KotlinLogging.logger {}

val placemarks = VideoMemStore()
val controller = PlacemarkController()
val placemarkView = VideoView()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = placemarkView.menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> placemarkView.listPlacemarks(placemarks)
            4 -> searchPlacemark()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun addPlacemark(){
    var aPlacemark = VideoModel()

    if (placemarkView.addPlacemarkData(aPlacemark))
        placemarks.create(aPlacemark)
    else
        logger.info("Placemark Not Added")
}

fun updatePlacemark() {

    placemarkView.listPlacemarks(placemarks)
    var searchId = placemarkView.getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null) {
        if(placemarkView.updatePlacemarkData(aPlacemark)) {
            placemarks.update(aPlacemark)
            placemarkView.showPlacemark(aPlacemark)
            logger.info("Placemark Updated : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Placemark Not Updated...")
}

fun searchPlacemark() {
    val aPlacemark = search(placemarkView.getId())!!
    placemarkView.showPlacemark(aPlacemark)
}


fun search(id: Long) : VideoModel? {
    var foundPlacemark = placemarks.findOne(id)
    return foundPlacemark
}

fun dummyData() {
    placemarks.create(VideoModel(title = "New York New York", description = "So Good They Named It Twice"))
    placemarks.create(VideoModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
    placemarks.create(VideoModel(title = "Waterford City", description = "You get great Blaas Here!!"))
}