package com.example.lunchtray

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.data.DataSource
import com.example.lunchtray.data.ScreenName
import com.example.lunchtray.ui.theme.LunchTrayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LunchTrayTheme {
                App(
                    viewModel = OrderViewModel()
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    canNavigateBack:Boolean,
    navigateUp:()->Unit,
    modifier: Modifier=Modifier
){
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back))
                }
            }
        }

    )
}

private fun onCancelClick(navController: NavHostController,viewModel: OrderViewModel){
    viewModel.reset()
    navController.popBackStack(route = ScreenName.Start.name, inclusive = false)

}

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    viewModel: OrderViewModel
) {

    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ScreenName.valueOf(
        backstackEntry?.destination?.route ?: ScreenName.Start.name
    )
    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },

                )
        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ScreenName.Start.name,
        ) {
            composable(ScreenName.Start.name){
                StarterScreen(
                    onClick = {
                        navController.navigate(ScreenName.EntreeMenu.name)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize())
            }

            composable(ScreenName.EntreeMenu.name){
                ChooseScreen(
                    list = DataSource.entree,
                    onCancel = {
                        onCancelClick(navController, viewModel)
                        },
                    currentSelectedItem = uiState.entree,
                    isEnabledNextButton = uiState.entree != null,
                    onSelected = {
                        viewModel.updateEntree(it)
                    },
                    onNext = {
                        navController.navigate(ScreenName.SideDishMenu.name)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                )
            }

            composable(ScreenName.SideDishMenu.name){
                ChooseScreen(
                    list = DataSource.sideDish,
                    onCancel = {
                        onCancelClick(navController, viewModel)
                    },
                    currentSelectedItem = uiState.sideDish,
                    onSelected = {
                        viewModel.updateSideDish(it)
                    },
                    isEnabledNextButton = uiState.sideDish != null,
                    onNext = {
                        navController.navigate(ScreenName.AccompanimentMenu.name)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                )
            }

            composable(ScreenName.AccompanimentMenu.name){
                ChooseScreen(
                    list = DataSource.accompaniments,
                    onCancel = {
                        onCancelClick(navController, viewModel)
                    },
                    currentSelectedItem = uiState.accompaniment,
                    onSelected = {
                        viewModel.updateAccompaniment(it)
                    },
                    isEnabledNextButton = uiState.accompaniment != null,
                    onNext = {
                        navController.navigate(ScreenName.Checkout.name)
                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                )
            }

            composable(ScreenName.Checkout.name){
                OrderSummaryScreen(
                    entree = uiState.entree,
                    sideDish = uiState.sideDish,
                    accompaniment = uiState.accompaniment,
                    itemTotalPrice = uiState.itemTotalPrice,
                    orderTax = uiState.orderTax,
                    orderTotalPrice = uiState.orderTotalPrice,
                    onCancel = {
                        onCancelClick(navController, viewModel)
                    },
                    onSubmit = {

                    },
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                )
            }
        }


    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
   App(viewModel = OrderViewModel())

}