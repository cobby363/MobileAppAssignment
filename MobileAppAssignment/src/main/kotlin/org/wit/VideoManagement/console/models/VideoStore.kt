package org.wit.videoManagement.console.models

interface VideoStore {

    fun findAll(): List<VideoModel>
    fun findOne(id: Long): VideoModel?
    fun create(video: VideoModel)
    fun update(video: VideoModel)
}