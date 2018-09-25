package com.example.katayama.storageapp.model

class ImageUploadInfo {

    var imageName: String? = null

    var imageURL: String? = null

    constructor() {

    }

    constructor(name: String, url: String) {

        this.imageName = name
        this.imageURL = url
    }

}