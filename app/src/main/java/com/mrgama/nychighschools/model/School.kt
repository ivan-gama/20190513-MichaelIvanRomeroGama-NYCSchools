package com.mrgama.nychighschools.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class School (
        @SerializedName("dbn")
        val dbn: String,
        @SerializedName("overview_paragraph")
        val overview_paragraph: String,
        @SerializedName("school_name")
        val school_name: String,
        @SerializedName("school_email")
        val school_email: String,
        @SerializedName("school_sports")
        val school_sports: String,
        @SerializedName("website")
        val website: String,
        @SerializedName("start_time")
        val start_time: String,
        @SerializedName("end_time")
        val end_time: String,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("academicopportunities1")
        val academic_opportunities1: String,
        @SerializedName("academicopportunities2")
        val academic_opportunities2: String,
        @SerializedName("academicopportunities3")
        val academic_opportunities3: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("extracurricular_activities")
        val extracurricular_activities: String,
        @SerializedName("phone_number")
        val phone_number: String,
        @SerializedName("program1")
        val program1: String
) :  Parcelable {
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
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(dbn)
                parcel.writeString(overview_paragraph)
                parcel.writeString(school_name)
                parcel.writeString(school_email)
                parcel.writeString(school_sports)
                parcel.writeString(website)
                parcel.writeString(start_time)
                parcel.writeString(end_time)
                parcel.writeString(latitude)
                parcel.writeString(longitude)
                parcel.writeString(location)
                parcel.writeString(academic_opportunities1)
                parcel.writeString(academic_opportunities2)
                parcel.writeString(academic_opportunities3)
                parcel.writeString(city)
                parcel.writeString(extracurricular_activities)
                parcel.writeString(phone_number)
                parcel.writeString(program1)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<School> {
                override fun createFromParcel(parcel: Parcel): School {
                        return School(parcel)
                }

                override fun newArray(size: Int): Array<School?> {
                        return arrayOfNulls(size)
                }
        }
}