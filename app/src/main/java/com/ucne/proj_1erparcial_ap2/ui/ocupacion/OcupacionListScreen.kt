package com.ucne.proj_1erparcial_ap2.ui.ocupacion

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.remote.dto.OcupacionesDto
import com.ucne.proj_1erparcial_ap2.ui.prestamo.PrestamoViewModel

@Composable
fun OcupacionListScreen(){
     val viewModel: PrestamoViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    OcupacionListBody(uiState.ocupacionesList)
}

@Composable
private fun OcupacionListBody(ocupaciones: List<OcupacionesDto>) {
    LazyColumn() {
        items(ocupaciones) { art ->
            OcupacionesRow(art)
        }
    }
}

@Composable
private fun OcupacionesRow(ocupacionesDto: OcupacionesDto) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = ocupacionesDto.descripcion, style = MaterialTheme.typography.titleLarge)
        Text(text = ocupacionesDto.sueldo.toString())
    }
}