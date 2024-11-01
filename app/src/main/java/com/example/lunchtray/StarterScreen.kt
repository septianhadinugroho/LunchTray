package com.example.lunchtray

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StarterScreen(
    onClick:()->Unit,
    modifier: Modifier=Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(onClick = onClick,
            modifier = Modifier.width(200.dp)) {
            Text(text = stringResource(id =  R.string.start_order))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun StarterPreview(){
    StarterScreen(onClick = {})
}