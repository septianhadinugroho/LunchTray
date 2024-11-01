package com.example.lunchtray

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import com.example.lunchtray.model.OrderItem
import com.example.lunchtray.ui.theme.LunchTrayTheme


@Composable
fun SelectedItemListTile(
    text:String,
    price:String,
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text
        )
        Text(
            text = price
        )
    }
}


@Composable
fun OrderSummaryScreen(
    onCancel:()->Unit,
    onSubmit:()->Unit,
     entree: OrderItem?,
     sideDish: OrderItem?,
    accompaniment: OrderItem?,
     itemTotalPrice: Double,
     orderTax: Double ,
     orderTotalPrice: Double ,
    modifier: Modifier=Modifier
){
    var isSubmit by rememberSaveable {
        mutableStateOf(false)
    }
    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ){
        Text(
            text = stringResource(id = R.string.order_summary),
            style = MaterialTheme.typography.labelLarge
        )
        SelectedItemListTile(text = entree?.title ?: "", price = "\$${entree?.price ?: 0}")
        SelectedItemListTile(text = sideDish?.title ?: "", price = "\$${sideDish?.price ?: 0}")
        SelectedItemListTile(text = accompaniment?.title ?: "", price = "\$${accompaniment?.price ?: 0}")
        HorizontalDivider()
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.subtotal))
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "\$${itemTotalPrice}")
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.tax))
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "\$${orderTax}")
        }
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(id = R.string.total), style =MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "\$${orderTotalPrice}", style = MaterialTheme.typography.titleMedium)
        }

        Row(
            modifier= Modifier.padding(top = 16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancel) {
                Text(text = stringResource(id = R.string.cancel))
            }

            Spacer(modifier=Modifier.padding(8.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    isSubmit = true
                    onSubmit()
                }) {
                Text(text= stringResource(id = R.string.submit))
            }
        }
        if(isSubmit)
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                Button(onClick =  {
                    isSubmit = false
                    onCancel()
                } ) {
                    Text(text = "OK")
                }
            },
            title = {
                Text(text = "Order Success")
            },
            text = {
                Text(text = "Your order is successfully submitted.")
            }

        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun OrderSummaryPreview(){
    LunchTrayTheme {
        OrderSummaryScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            accompaniment = OrderItem(title = "Hello", description = "", price = 4.5),
            sideDish = OrderItem(title = "Hello", description = "", price = 4.5),
            entree = OrderItem(title = "Hello", description = "", price = 4.5),
            orderTax = 4.0,
            orderTotalPrice = 4.3,
            itemTotalPrice = 4.9,
            onCancel = {}, onSubmit = {})
    }
}