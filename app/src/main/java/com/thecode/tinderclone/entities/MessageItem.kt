package com.thecode.tinderclone.entities

class MessageItem {
    var id = 0
    var name: String? = null
    var content: String? = null
    var count = 0
    var picture = 0
        set(id) {
            this.id = id
        }

    constructor()
    constructor(id: Int, name: String?, content: String?, count: Int, picture: Int) {
        this.id = id
        this.name = name
        this.content = content
        this.count = count
        this.picture = picture
    }
}