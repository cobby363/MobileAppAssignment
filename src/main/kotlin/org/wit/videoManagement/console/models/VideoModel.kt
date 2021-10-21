package org.wit.videoManagement.console.models

data class VideoModel(var id: Long = 0,
                      var channel: String = "",
                      var videoTitle: String = "",
                      var likedAbout: String = "",
                      var starRating: Int = 0,
                      var tags: ArrayList<String> = arrayListOf()
)