package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(modifier = Modifier.fillMaxSize()) {
        PrestamoBody(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoBody(viewModel: PrestamoViewModel) {

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            value = viewModel.deudor,
            onValueChange = { viewModel.deudor = it },
            label = { Text(text = "Deudor") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            label = { Text(text = "Concepto") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            label = { Text(text = "Monto") }
        )
        ExtendedFloatingActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = { Text(text = "Guardar") },
            icon = { },
            onClick = {}
        )

    }
}