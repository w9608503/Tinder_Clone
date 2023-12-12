package com.thecode.tinderclone.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile {
    @JvmField
    @SerializedName("name")
    @Expose
    var name: String? = null

    @JvmField
    @SerializedName("url")
    @Expose
    var imageUrl: String? = null

    @JvmField
    @SerializedName("age")
    @Expose
    var age: Int? = null

    @JvmField
    @SerializedName("location")
    @Expose
    var location: String? = null
}