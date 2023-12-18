package com.example.after_party

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PopularListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            After_PartyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PopularListScreen()
                }
            }
        }
    }
}

data class PopularItem(
    val image: Int,
    val restaurantName: String,
    val about: String,
    val numPeople: String
)

@Composable
fun PopularListScreen() {
    val popularItemsList = listOf(
        PopularItem(R.drawable.restauraunt_01, "남영돈", "가브리살 항정살 맛있는 고기", "최대수용인원: 5명"),
        PopularItem(R.drawable.restauraunt_02, "상록수", "부드러운 삼겹살", "최대수용인원: 8명"),
        PopularItem(R.drawable.restauraunt_04, "이웃집 영준이", "생선튀김 맛있는 집", "최대수용인원: 10명"),
        PopularItem(R.drawable.restauraunt_05, "토담", "꿀동동주와 안주 원탑", "최대수용인원: 8명"),
    )

    val adapter = PopularListAdapter(popularItemsList)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { context ->
                RecyclerView(context).apply {
                    this.layoutManager = LinearLayoutManager(context)
                    this.adapter = adapter
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview
@Composable
fun PreviewPopularListScreen() {
    After_PartyTheme {
        PopularListScreen()
    }
}
