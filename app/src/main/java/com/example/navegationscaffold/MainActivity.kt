package com.example.navegationscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegationscaffold.ui.theme.NavegationScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegationScaffoldTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen( onNavigateToAdd = { navController.navigate("add") }
            )
        }
        composable("add") {
            Add(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToAdd: () -> Unit) {
    var searchText by remember { mutableStateOf("") }
    Scaffold(topBar ={
        TopAppBar(
            title = {
                Column {
                    Text("Hola usuario", style = MaterialTheme.typography.titleLarge)
                    Text("Bienvenido de nuevo", style = MaterialTheme.typography.bodyMedium)
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notificaciones")
                }
            }
        )
    },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { onNavigateToAdd() },
                    icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Profile") },
                    label = { Text("Add") }
                )
            }
        }
    ){innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item{
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Search") },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar productos, servicios...") },
                    leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            shape = RoundedCornerShape(12.dp)
                ) {
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(16.dp),
                       verticalArrangement = Arrangement.Center
                   ){
                       Text("Ofertas de la semana", style = MaterialTheme.typography.titleLarge)
                       Spacer(modifier = Modifier.height(8.dp))
                       Text("Descubre las mejores ofertas", style = MaterialTheme.typography.bodyMedium)
                }
              }
            }
            item {
                Text("Categorías", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOf("Frutas", "Verduras", "Carnes", "Lácteos", "Panadería")) { categoria ->
                        AssistChip(
                            onClick = { /*TODO*/ },
                            label = { Text(categoria) }

                        )
                    }

                }
            }
            item{
                Text("Recomendaciones para ti", style = MaterialTheme.typography.titleLarge)
            }
            items(5){
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ){
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)

                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.primary)

                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Nombre del producto", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Descripción del producto", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(onBackClick: () -> Unit){
    var nombreProducto by remember { mutableStateOf("") }
    var precioProducto by remember { mutableStateOf("") }
    var descripcionProducto by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Producto") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nombreProducto,
                onValueChange = { nombreProducto = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = precioProducto,
                onValueChange = { precioProducto = it },
                label = { Text("Precio del producto") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcionProducto,
                onValueChange = { descripcionProducto = it },
                label = { Text("Descripción del producto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick= { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp)

            ){
                Text("Agregar Producto")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavegationScaffoldTheme {
        AppNavigation()
    }
}