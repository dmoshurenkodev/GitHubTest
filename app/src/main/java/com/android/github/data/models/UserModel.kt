package com.android.github.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("gists_url")
    val gistsUrl: String ?= "",
    @SerializedName("repos_url")
    val reposUrl: String ?= "",
    @SerializedName("following_url")
    val followingUrl: String ?= "",
    @SerializedName("starred_url")
    val starredUrl: String ?= "",
    @SerializedName("login")
    val login: String ?= "",
    @SerializedName("followers_url")
    val followersUrl: String ?= "",
    @SerializedName("type")
    val type: String ?= "",
    @SerializedName("url")
    val url: String ?= "",
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String ?= "",
    @SerializedName("received_events_url")
    val receivedEventsUrl: String ?= "",
    @SerializedName("avatar_url")
    val avatarUrl: String ?= "",
    @SerializedName("events_url")
    val eventsUrl: String ?= "",
    @SerializedName("html_url")
    val htmlUrl: String ?= "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("gravatar_id")
    val gravatarId: String ?= "",
    @SerializedName("node_id")
    val nodeId: String ?= "",
    @SerializedName("organizations_url")
    val organizationsUrl: String ?= ""

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gistsUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(starredUrl)
        parcel.writeString(login)
        parcel.writeString(followersUrl)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(subscriptionsUrl)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(avatarUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(htmlUrl)
        parcel.writeByte(if (siteAdmin) 1 else 0)
        parcel.writeInt(id)
        parcel.writeString(gravatarId)
        parcel.writeString(nodeId)
        parcel.writeString(organizationsUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}