package com.example.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.SharedKeys
import com.example.core.sharedUi.AppButton
import com.example.core.sharedUi.WeatherAppbarWithThemeButton


@Composable

fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel = hiltViewModel()) {
    val query by searchViewModel.query.collectAsState()
    Scaffold(topBar = {
        WeatherAppbarWithThemeButton(stringResource(R.string.search)) { navController.popBackStack() }
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { newValue ->
                        searchViewModel.onQueryChanged(newValue)
                    },
                    label = { Text(stringResource(R.string.search_city)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                AppButton(text = stringResource(R.string.fetch_weather)) {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(SharedKeys.CITY_KEY, searchViewModel.query.value)
                    navController.popBackStack()
                }

            }
        }
    }

}

