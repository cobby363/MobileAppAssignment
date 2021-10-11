package org.wit.VideoManagement.console.controllers

import VideoMemStore
import mu.KotlinLogging
import org.wit.VideoManagement.console.models.VideoModel
import org.wit.VideoManagement.console.views.VideoView

class PlacemarkController {

    val placemarks = VideoMemStore()
    val placemarkView = VideoView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 1.0")
    }

    fun menu() :Int { return placemarkView.menu() }

    fun add(){
        var aPlacemark = VideoModel()

        if (placemarkView.addPlacemarkData(aPlacemark))
            placemarks.create(aPlacemark)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {

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

    fun search() {
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
}