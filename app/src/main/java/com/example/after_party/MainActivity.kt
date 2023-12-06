package com.example.after_party

import android.content.Context
import android.content.Intent
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
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.after_party.data.BottomNavigationItem
import com.example.after_party.data.User
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


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
                    bottomApp()

                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun bottomApp() {

        val items = listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                hasNews = false,
            ),
            BottomNavigationItem(
                title = "Event",
                selectedIcon = Icons.Filled.Favorite,
                unselectedIcon = Icons.Outlined.Favorite,
                hasNews = true,
                badgeCount = 3
            ),
            BottomNavigationItem(
                title = "Search",
                selectedIcon = Icons.Filled.Search,
                unselectedIcon = Icons.Outlined.Search,
                hasNews = false,
            ),
            BottomNavigationItem(
                title = "MyPage",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                hasNews = true,
            ),
        )
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.title) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            })
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "Home",
                Modifier.padding(innerPadding)
            ) {
                //composable 안되는 일반 뷰 파일이면 인텐트 갈길거
                composable("Home") { MainScreen() }
                composable("Event") { }
                composable("Search") {}
                composable("MyPage") {myPage(context = applicationContext)}
            }
        }
    }



}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        //val navController = rememberNavController() //mainscreen 안 이동 담당
        var text by rememberSaveable { mutableStateOf("") }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(colorResource(id = R.color.seed))

        ) {
            TextButton(
                onClick = {
                },
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                IconButton(
                    onClick = {
                    },
                    modifier =
                    Modifier
                        .size(150.dp)
                        .padding(top = 3.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.popular),
                        contentDescription = "인기식당",

                        )
                }
                IconButton(onClick = {
                }, modifier = Modifier.size(150.dp)) {
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
                IconButton(onClick = {
                }, modifier = Modifier.size(150.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.tasty),
                        contentDescription = "안주맛집"
                    )

                }
                IconButton(onClick = {
                }, modifier = Modifier.size(150.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.alcohol),
                        contentDescription = "다양한 주류"
                    )

                }


            }

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                line()
                ShowRestaurant()

            }

        }


    }


@Composable
fun line() {
    Canvas(modifier = Modifier.fillMaxWidth()) {
        val width = size.width
        drawLine(
            color = seed,
            start = Offset(-400f, 0f),
            end = Offset(width, 0f),
            strokeWidth = 3f
        )
    }
}

@Composable
fun ShowRestaurant() {
    Text(
        text = "나와 가까운 식당",
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = 5.dp, top = 15.dp)
    )
    OutlinedCard(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.outlinedCardColors(Color(0xFFF3F8FA))

    ) {
        Row {
            Image(

                painter = painterResource(id = R.drawable.restraunt_01),
                contentDescription = "임시 식당",
                modifier =
                Modifier
                    .padding(top = 15.dp, start = 20.dp)
                    .size(70.dp),

                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 25.dp, top = 15.dp)) {
                Text(text = "남영돈", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text("가브리살 항정살 맛집")
                Text("최대 수용 인원: 50명")
                Spacer(modifier = Modifier.padding(30.dp))

            }
            FilledTonalButton(
                colors = ButtonDefaults.buttonColors(seed),
                contentPadding = ButtonDefaults.ContentPadding,
                modifier =
                Modifier
                    .size(width = 120.dp, height = 45.dp)
                    .padding(7.dp)
                    .wrapContentSize()
                    .align(Alignment.Bottom),
                onClick = {
                }) {
                Text("예약하러 가기", fontWeight = FontWeight.ExtraLight, fontSize = 9.sp)
            }
        }
    }
}
