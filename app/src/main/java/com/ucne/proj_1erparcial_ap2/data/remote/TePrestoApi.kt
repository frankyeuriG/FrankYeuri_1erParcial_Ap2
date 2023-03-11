package com.ucne.proj_1erparcial_ap2.data.remote

import com.ucne.proj_1erparcial_ap2.data.remote.dto.OcupacionesDto
import retrofit2.http.GET

interface TePrestoApi {
    @GET("/api/Ocupaciones")
    suspend fun getList(): List<OcupacionesDto>

}