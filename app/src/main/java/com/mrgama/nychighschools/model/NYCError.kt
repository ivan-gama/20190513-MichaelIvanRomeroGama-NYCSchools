package com.mrgama.nychighschools.model;

data class NYCError(
        val httpCode: Int,
        val exception: String,
        val message: String
)
