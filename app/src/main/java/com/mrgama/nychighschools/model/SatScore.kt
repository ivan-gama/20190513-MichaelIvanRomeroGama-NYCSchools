package com.mrgama.nychighschools.model


import com.google.gson.annotations.SerializedName

data class SatScore(
        @SerializedName("dbn")
        val dbn: String, // 01M292
        @SerializedName("num_of_sat_test_takers")
        val numOfSatTestTakers: String, // 29
        @SerializedName("sat_critical_reading_avg_score")
        val satCriticalReadingAvgScore: String, // 355
        @SerializedName("sat_math_avg_score")
        val satMathAvgScore: String, // 404
        @SerializedName("sat_writing_avg_score")
        val satWritingAvgScore: String, // 363
        @SerializedName("school_name")
        val schoolName: String // HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES
)