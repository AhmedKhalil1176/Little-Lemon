package com.ahmed.littlelemoncapstone.composables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ahmed.littlelemoncapstone.MenuItemRoom
import com.ahmed.littlelemoncapstone.MenuViewModel
import com.ahmed.littlelemoncapstone.Profile
import com.ahmed.littlelemoncapstone.R
import com.ahmed.littlelemoncapstone.ui.theme.Cloud
import com.ahmed.littlelemoncapstone.ui.theme.Grey
import com.ahmed.littlelemoncapstone.ui.theme.LightGrey
import com.ahmed.littlelemoncapstone.ui.theme.PrimaryGreen
import com.ahmed.littlelemoncapstone.ui.theme.SecondaryGreen
import com.ahmed.littlelemoncapstone.ui.theme.SecondaryYellow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage



@Composable
fun HomeScreen(navController: NavHostController) {
    HomePage(navController, menuViewModel = viewModel())
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = NavHostController(LocalContext.current)
    HomeScreen(navController)
}

@Composable
fun HomePage(navController: NavHostController, menuViewModel: MenuViewModel) {
    val context = LocalContext.current
    val viewModel : MenuViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val searchPhrase = remember { mutableStateOf("") }
    val menuItems by menuViewModel.getAllDatabaseMenuItems().observeAsState(emptyList())
    val categories = menuItems.map { it.category }.distinct()

    LaunchedEffect ( Unit ){
        viewModel.fetchMenuDataIfNeeded()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {
        // Header
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .padding(start = 110.dp, top = 10.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    modifier = Modifier
                        .height(70.dp)
                        .padding(start = 40.dp, top = 10.dp, end = 10.dp)
                        .clickable {
                            navController.navigate(Profile.route)
                        }
                )

            }
        }
        item{ Spacer(modifier = Modifier.height(16.dp)) }

        // //HeroSection
        item{ HeroPanel(searchPhrase.value, onSearchPhraseChanged = { searchPhrase.value = it }) }
        item{ Spacer(modifier = Modifier.height(16.dp)) }

//        item{ Spacer(modifier = Modifier.width(16.dp)) }
        // Filtered MenuItems
        val filteredMenuItems = if (searchPhrase.value.isBlank()) {
            databaseMenuItems
        } else {
            databaseMenuItems.filter { menuItem ->
                menuItem.title.contains(searchPhrase.value, ignoreCase = true)
            }
        }

        // MenuBreakdown Section
        item { MenuBreakdown(
            categories = categories,
            selectedCategory = "",
            onCategorySelected = {}) }

        // MenuItems Section
        item{ MenuItems(menuItems = filteredMenuItems, context) }


    }

}

@Composable
fun MenuItems(menuItems: List<MenuItemRoom>, context: Context) {
    Column {
        for (menuItem in menuItems) {
            MenuItem(item = menuItem, context)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom, context: Context) {

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            text = item.title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Black
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, end = 12.dp)

        ) {
            Column {
                Text(
                    text = item.description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.75f)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Text(
                    text = "$ ${item.price}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                )
            }
            GlideImage(
                model = item.image,
                contentDescription = item.title,
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    .size(80.dp),
                contentScale = ContentScale.Crop,
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(end = 12.dp),
            thickness = 1.dp,
            color = LightGrey
        )
    }
}

@Composable
fun HeroPanel(searchPhrase: String, onSearchPhraseChanged: (String) -> Unit){
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(PrimaryGreen)
    ) {
        Text(
            text = "Little Lemon",
            style = TextStyle(
                fontSize = 58.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryYellow,
                fontFamily = FontFamily(Font(R.font.markazi_regular))
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 12.dp, bottom = 25.dp)
        ) {
            Column {
                Text(
                    text = "Chicago",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Cloud,
                        fontFamily = FontFamily(Font(R.font.karla_regular))
                    ),

                    )
                Text(
                    text = stringResource(id = R.string.description),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.karla_regular))
                    ),
                    color = Cloud,
                    modifier = Modifier
                        .padding(top = 16.dp, end = 20.dp)
                        .fillMaxWidth(0.55f)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "hero image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    .size(150.dp)
            )
        }

        OutlinedTextField(
            value = searchPhrase,
            onValueChange = onSearchPhraseChanged,
            placeholder = { Text("Enter Search Phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 20.dp)
                .height(50.dp)
                .background(Cloud)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

    }
}

@Composable
fun MenuBreakdown(categories: List<String>, selectedCategory: String?, onCategorySelected: (String) -> Unit){
    Column (
        modifier = Modifier
            .padding(start = 12.dp, top = 12.dp, bottom = 24.dp)
    ){
        Text(
            text = "ORDER FOR DELIVERY!",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                color = com.ahmed.littlelemoncapstone.ui.theme.Black
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, top = 8.dp),
            horizontalArrangement = Arrangement.Start
        ){
            categories.forEach {  category ->
                Button(
                    onClick = {
                        onCategorySelected(if(selectedCategory == category) "" else category)
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = if(selectedCategory == category) PrimaryGreen else SecondaryGreen,
                        contentColor = if(selectedCategory == category) SecondaryGreen else PrimaryGreen,
                    ),
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Text(
                        text = category,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }
        }

    }
    HorizontalDivider(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
        thickness = 1.dp,
        color = Grey
    )

}


