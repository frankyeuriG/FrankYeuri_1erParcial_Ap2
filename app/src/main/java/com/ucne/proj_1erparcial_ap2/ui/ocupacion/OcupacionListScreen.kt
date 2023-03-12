package com.ucne.proj_1erparcial_ap2.ui.ocupacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.remote.dto.OcupacionesDto
import com.ucne.proj_1erparcial_ap2.ui.prestamo.PrestamoViewModel


@Composable
fun OcupacionListScreen() {
    val viewModel: PrestamoViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(10.dp))
        TituloListOcupacion()
        Spacer(modifier = Modifier.padding(10.dp))
        OcupacionListBody(uiState.ocupacionesList)
    }
}

@Composable
fun TituloListOcupacion() {
    Text(
        text = "Lista de Ocupaciones",
        fontSize = 35.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
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
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(8.dp)
    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
        )
        {
            Text(
                text = ocupacionesDto.descripcion,
                fontSize = 35.sp,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                String.format("%.2f", ocupacionesDto.sueldo),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                fontSize = 20.sp
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}