package com.paulo.seleadclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paulo.seleadclass.ui.theme.SeleadClassTheme
import java.lang.Error

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            val uiState = viewModel.uiState.value
            SeleadClassTheme {
               Box(
                  modifier = Modifier.fillMaxSize(),
                  contentAlignment = Alignment.Center 
               ){
                   LazyColumn(
                       modifier = Modifier.fillMaxSize()
                   ){
                       items(uiState.items.size){
                           Text(
                               text = uiState.items[it].name,
                               modifier = Modifier.padding(15.dp)
                           )
                       }
                   }
                   if(uiState.isLoading){
                       CircularProgressIndicator()
                   }
                   when(uiState.error){
                       UiState.Error.Network ->{
                           ErrorMessage("NetWork Error")
                       }
                       UiState.Error.InputEmpty ->{
                           ErrorMessage("NetWork InputEmpty")
                       }
                       UiState.Error.InputTooShort ->{
                           ErrorMessage("NetWork InputTooShort")
                       }
                   }
               }
            }
        }
    }
}

@Composable
fun ErrorMessage(error: String) {
    Text(text = error)
}