package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PrestamoViewModel @Inject constructor(
    private val prestamoRepository: PrestamoRepository
) : ViewModel() {
        var deudor by mutableStateOf("")
        var concepto by mutableStateOf("")
        var monto by mutableStateOf("")

}