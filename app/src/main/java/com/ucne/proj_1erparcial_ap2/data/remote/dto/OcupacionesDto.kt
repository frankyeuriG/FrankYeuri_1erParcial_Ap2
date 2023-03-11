package com.ucne.proj_1erparcial_ap2.data.remote.dto

import com.squareup.moshi.Json

data class OcupacionesDto(
    @Json(name = "ocupacionId")
    val ocupacionId:Int,
    val descripcion:String,
    val sueldo: Double
)
