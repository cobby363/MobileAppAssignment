package org.wit.videoManagement.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.videoManagement.console.controllers.VideoUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddVideoScreen : View("Add Video") {
    val model = ViewModel()
    val _channel = model.bind { SimpleStringProperty() }
    val _videoTitle = model.bind { SimpleStringProperty() }
    val videoUIController: VideoUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Title") {
                textfield(_channel).required()
            }
            field("Description") {
                textarea(_videoTitle).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        videoUIController.add(_channel.toString(),_videoTitle.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        videoUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _channel.value = ""
        _videoTitle.value = ""
        model.clearDecorators()
    }
}