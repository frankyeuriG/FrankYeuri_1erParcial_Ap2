package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity

@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(modifier = Modifier.fillMaxSize()) {
        PrestamoBody(viewModel)
        Spacer(modifier = Modifier.padding(8.dp))
        TituloLista()
        Spacer(modifier = Modifier.padding(8.dp))
        val uiState by viewModel.uiState.collectAsState()
        PrestamoListScreen(uiState.prestamoList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoBody(viewModel: PrestamoViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Text(
            text = "Deudor",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = viewModel.deudor,
            onValueChange = { viewModel.deudor = it },
            label = { Text(text = "Deudor") }
        )
        Text(
            text = "Concepto",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            label = { Text(text = "Concepto") }
        )
        Text(
            text = "Monto",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            label = { Text(text = "Monto") }
        )
        ExtendedFloatingActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = { Text(text = "Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
            onClick = { viewModel.insert() }
        )
    }
}

@Composable
private fun PrestamoListScreen(prestamoList: List<PrestamoEntity>) {
    LazyColumn {
        items(prestamoList) { prestamo ->
            PrestamoRow(prestamo)
        }
    }
}

@Composable
fun TituloLista() {
    Text(
        text = "Lista de Prestamos",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun PrestamoRow(prestamo: PrestamoEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = prestamo.deudor,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(2f)
            )
            Text(
                text = prestamo.concepto,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(2f)
            )
            Text(
                String.format("%.2f", prestamo.monto),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}