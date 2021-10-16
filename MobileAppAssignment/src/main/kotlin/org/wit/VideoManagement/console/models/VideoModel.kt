package org.wit.videoManagement.console.models

data class VideoModel(var id: Long = 0,
                      var channel: String = "",
                      var video: String = "",
                      var likedAbout: String = "",
                      var starRating: Float = 0.0f,
                      var tags: String = "")