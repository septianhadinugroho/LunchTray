package com.example.lunchtray

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.common.RadioListTileItem
import com.example.lunchtray.data.DataSource
import com.example.lunchtray.model.OrderItem


@Composable
fun ChooseScreen(
    list: List<OrderItem>,
    onCancel: ()->Unit,
    isEnabledNextButton:Boolean,
    onNext:()->Unit,
    onSelected: (OrderItem) -> Unit,
    currentSelectedItem: OrderItem?,
    modifier: Modifier=Modifier,
){
    Column(
        modifier = modifier
    ) {
        ItemList(list = list,onSelected,currentSelectedItem)
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                onClick = onNext,
                enabled = isEnabledNextButton,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }

}

@Composable
fun ItemList(
    list: List<OrderItem>,
    onSelected:(OrderItem)->Unit,
    currentSelectedItem: OrderItem?,
    modifier:Modifier=Modifier,
){
    var selectedTitle by rememberSaveable {
        mutableStateOf(currentSelectedItem?.title)
    }

    LazyColumn(modifier = modifier) {
        items(list){
            RadioListTileItem(
                orderItem = it,
                onClick = {
                    selectedTitle = it.title
                    onSelected(it)
                },
                isSelected = selectedTitle == it.title
            )
        }

    }
}



@Preview(showSystemUi = true)
@Composable
fun ChooseScreenPreview(){
    ChooseScreen(
        onNext ={},
        onCancel = {},
        list = DataSource.entree,
        onSelected = {},
        isEnabledNextButton = false,
        currentSelectedItem = null,
        modifier = Modifier.fillMaxHeight().padding(16.dp),


    )
}