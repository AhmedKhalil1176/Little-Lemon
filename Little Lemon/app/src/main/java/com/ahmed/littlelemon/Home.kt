package com.ahmed.littlelemon

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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavController){
    Column (
        modifier = Modifier.fillMaxWidth()
            .background(color = colorResource(R.color.white))
    ){
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .height(60.dp)
                    .padding(top = 5.dp, bottom = 5.dp)
            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "profile profile",
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(60.dp)
                    .padding(end = 5.dp)
                    .clickable {
                        navController.navigate("Profile")
                    }
            )

        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    val navController = rememberNavController()
    Home(navController)

}