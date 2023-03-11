package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity
import com.ucne.proj_1erparcial_ap2.data.remote.TePrestoApi
import com.ucne.proj_1erparcial_ap2.data.remote.dto.OcupacionesDto
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class prestamoUiState(
    val prestamoList: List<PrestamoEntity> = emptyList(),
    val ocupacionesList: List<OcupacionesDto> = emptyList()
)

@HiltViewModel
class PrestamoViewModel @Inject constructor(
    private val prestamoRepository: PrestamoRepository,
    private val tePrestoApi: TePrestoApi
) : ViewModel() {

    var deudor by mutableStateOf("")
    var concepto by mutableStateOf("")
    var monto by mutableStateOf("")


    var uiState = MutableStateFlow(prestamoUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            getOcupacionesFromApi()
            refrescarPrestamos()
        }
    }

    private  suspend fun getOcupacionesFromApi(){
        val ocupaciones = tePrestoApi.getList()
        uiState.update {
            it.copy(ocupacionesList = ocupaciones)
        }
    }

    private suspend fun refrescarPrestamos() {
        prestamoRepository.getlist().collect() { lista ->
            uiState.update {
                it.copy(prestamoList = lista)
            }
        }
    }

    fun insert() {
        val prestamo = PrestamoEntity(
            deudor = deudor,
            concepto = concepto,
            monto = monto.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.insert(prestamo)
            Limpiar()

        }
    }

    private fun Limpiar() {
        deudor = " "
        concepto = " "
        monto = " "
    }
}



