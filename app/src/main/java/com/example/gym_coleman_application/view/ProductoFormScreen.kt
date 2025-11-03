package com.example.gym_coleman_application.view

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gym_coleman_application.data.model.Producto
import com.example.gym_coleman_application.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoFormScreen(
    navController: NavController,
    nombre: String,
    precio: String
) {
    var cantidad by remember { mutableStateOf(TextFieldValue("")) }
    var direccion by remember { mutableStateOf(TextFieldValue("")) }
    var efectivo by remember { mutableStateOf(false) }
    var debito by remember { mutableStateOf(false) }

    val viewModel: ProductoViewModel = viewModel()
    val productos: List<Producto> by viewModel.productos.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface, // Color de fondo general
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
                Text(
                    text = "Formulario de Pedido",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    ) { innerPadding ->
        // Usamos LazyColumn para toda la pantalla para que sea scrollable si el contenido no cabe
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp) // Padding horizontal para todo
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // -- SECCIÓN DE DETALLES DEL PRODUCTO --
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Imagen Producto",
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = nombre, style = MaterialTheme.typography.headlineMedium)
                Text(text = "Precio: $precio", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(24.dp))
            }

            // -- SECCIÓN DE FORMULARIO --
            item {
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección de Entrega") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = efectivo,
                        onCheckedChange = { efectivo = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                    )
                    Text("Pagar con Efectivo")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = debito,
                        onCheckedChange = { debito = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                    )
                    Text("Pagar con Débito")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        val producto = Producto(
                            nombre = nombre,
                            precio = precio,
                            cantidad = cantidad.text,
                            direccion = direccion.text
                        )
                        viewModel.guardarProducto(producto)
                    },
                    enabled = cantidad.text.isNotBlank() && direccion.text.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar Pedido")
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // -- SECCIÓN DE PEDIDOS REALIZADOS --
            item {
                Text("Pedidos Realizados", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (productos.isNotEmpty()) {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "${producto.nombre} - ${producto.precio}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Cantidad: ${producto.cantidad}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Dirección: ${producto.direccion}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            } else {
                item {
                    Box(modifier = Modifier.padding(vertical = 32.dp)) {
                        Text(
                            text = "Aún no hay pedidos realizados.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductoFormScreen() {
    // Para que la Preview se vea bien, envuélvela en tu tema
    // Asumiendo que tu tema se llama 'GymColemanApplicationTheme'
    // GymColemanApplicationTheme {
    ProductoFormScreen(
        navController = rememberNavController(),
        nombre = "Creatina Super Coleman",
        precio = "$5000"
    )
    // }
}
