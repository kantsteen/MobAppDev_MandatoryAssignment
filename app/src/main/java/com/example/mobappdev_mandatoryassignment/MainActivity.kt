package com.example.mobappdev_mandatoryassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mobappdev_mandatoryassignment.model.SalesItem
import com.example.mobappdev_mandatoryassignment.model.SalesItemsViewModel
import com.example.mobappdev_mandatoryassignment.ui.theme.MobAppDev_MandatoryAssignmentTheme
import kotlin.String
import kotlin.collections.List

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobAppDev_MandatoryAssignmentTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                SalesItem("BMW", 100000)
//                SalesItems()


            }
        }
    }
}


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: SalesItemsViewModel = SalesItemsViewModel()
    ) {
val navController = rememberNavController()
val salesItems = viewModel.salesItems.value
val errorMessage = viewModel.errorMessage.value
}


//@Composable
//fun SalesItem(description: String, price: Int, modifier: Modifier = Modifier) {
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
//    ) {
//        Column(modifier) {
//
//            val image = painterResource(R.drawable.bmw_blue_sample)
//            Image(
//                painter = image,
//                contentDescription = null
//            )
//            Text(
//                "$description \n$price DKK",
//                modifier = modifier
//            )
//        }
//    }
//}
//
//@Composable
//fun SalesItems(
//    modifier: Modifier = Modifier,
//    descriptions: List<String> = List(50) { "$it" }
//) {
//    LazyColumn(Modifier.padding(vertical = 4.dp)) {
//        items(items = descriptions) { description ->
//            Row(
//                modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.Top){
//                SalesItem(description = "BMW", 1000)
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun SalesItemsPreview() {
//    MobAppDev_MandatoryAssignmentTheme {
//        SalesItems()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SalesItemPreview() {
//    Column(modifier = Modifier) {// can maybe be deleted
//        Row(modifier = Modifier) {
//            SalesItem("BMW", 100000)
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobAppDev_MandatoryAssignmentTheme {
//        Greeting("Android")
//    }
//}