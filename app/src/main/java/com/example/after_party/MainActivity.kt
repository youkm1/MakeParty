package com.example.after_party

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.os.Bundle
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            After_PartyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(colorResource(id = R.color.seed))

    ) {

        TextButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .background(colorResource(id = R.color.seed))
        )
        {

            Text(text = "숙명여자대학교 중앙도서관")
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "주소창")

        }
        //when click this field, 1. go to main002 page 2. animation click(main002)
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("가게 이름을 검색해주세요.") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(Color.Transparent),
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .background(colorResource(id = R.color.seed))

        )


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(colorResource(id = R.color.md_theme_light_surface))
                .fillMaxWidth()

        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
            Text(
                "함께 즐기는 뒷풀이,\n단 한 번의 터치로 예약하세요 \n\nAFTER PARTY가 함께합니다!",
                fontWeight = FontWeight.Bold
            )

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .size(100.dp)
            .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {

            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.popular),
                    contentDescription = "인기식당",

                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.recentreserved),
                    contentDescription = "최근 예약장소"
                )

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.tasty),
                    contentDescription = "안주맛집"
                )

            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.alcohol),
                    contentDescription = "다양한 주류"
                )

            }
        }
        BottomAppBar(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .wrapContentSize()
                .padding(top = 270.dp)

        ) {
            Spacer(modifier = Modifier.size(10.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Home,contentDescription = null)
            }
            Spacer(modifier = Modifier.size(50.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Favorite,contentDescription = null)
            }
            Spacer(modifier = Modifier.size(50.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Search,contentDescription = null)
            }
            Spacer(modifier = Modifier.size(50.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Person,contentDescription = null)
            }
        }
    }

}


@Composable
fun Grid() {
    val itemsList = (0..3).toList()

    val itemModifier = Modifier
        .border(1.dp, Color.Blue)

        .wrapContentSize()

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.background(Color.White)
    ) {
        items(itemsList) {
            Text("Item is $it", itemModifier)
        }

    }
}


@Preview
@Composable
fun MainScreenpre() {
    MainScreen()

}