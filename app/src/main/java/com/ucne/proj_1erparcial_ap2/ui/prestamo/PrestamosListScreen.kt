package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity


@Composable
fun PrestamosListScreen() {
    val viewModel: PrestamoViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    PrestamoListBody(uiState.prestamoList)
}


@Composable
private fun PrestamoListBody(prestamoList: List<PrestamoEntity>) {
    LazyColumn {
        items(prestamoList) { prestamo ->
            PrestamoRow(prestamo)
        }
    }
}

@Composable
private fun PrestamoRow(prestamo: PrestamoEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = prestamo.deudor,
                fontSize = 35.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = prestamo.concepto, style = MaterialTheme.typography.titleLarge
            )
            Text(
                String.format("%.2f", prestamo.monto),
                fontSize = 35.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}