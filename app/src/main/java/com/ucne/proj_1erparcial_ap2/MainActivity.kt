package com.ucne.proj_1erparcial_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Engineering
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.List
import androidx.compose.material.icons.twotone.ListAlt
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.proj_1erparcial_ap2.ui.ocupacion.OcupacionListScreen
import com.ucne.proj_1erparcial_ap2.ui.prestamo.PrestamoScreen
import com.ucne.proj_1erparcial_ap2.ui.prestamo.PrestamosListScreen
import com.ucne.proj_1erparcial_ap2.ui.theme.Proj_1erParcial_Ap2Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proj_1erParcial_Ap2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                    startDestination = Screen.Start.route
                    ) {
                    composable(Screen.Start.route) {
                        DrawerMenu(navController)
                    }
                    composable(Screen.Prestamo.route) {
                        PrestamoScreen()
                    }
                    composable(Screen.PrestamoList.route) {
                        PrestamosListScreen()
                    }
                    composable(Screen.OcupacionesList.route){
                        OcupacionListScreen()
                    }
                }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    navController: NavController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val ic  =Icons.TwoTone.Favorite

    val items = listOf(Screen.Start, Screen.Prestamo, Screen.PrestamoList, Screen.OcupacionesList)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                            navController.navigate(item.route)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                Spacer(Modifier.height(20.dp))
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text("Click to open")
                }
            }
        }
    )
}
sealed class Screen(val route: String,val title: String, val icon: ImageVector) {
    object Start : Screen("start","Inicio", Icons.TwoTone.Favorite)
    object Prestamo : Screen("prestamo", "Prestamo", Icons.TwoTone.Engineering)
    object PrestamoList : Screen("prestamo_list","Lista de prestamos", Icons.TwoTone.List)
    object OcupacionesList : Screen("ocupaciones_list", "Lista de Ocupaciones", Icons.TwoTone.ListAlt)
}
