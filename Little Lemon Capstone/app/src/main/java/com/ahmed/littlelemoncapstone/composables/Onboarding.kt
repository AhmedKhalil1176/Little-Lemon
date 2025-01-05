package com.ahmed.littlelemoncapstone.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmed.littlelemoncapstone.CustomButton
import com.ahmed.littlelemoncapstone.CustomTextFiled
import com.ahmed.littlelemoncapstone.Home
import com.ahmed.littlelemoncapstone.R
import com.ahmed.littlelemoncapstone.ui.theme.Green
import com.ahmed.littlelemoncapstone.ui.theme.White

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxWidth()
            .background(White),
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(70.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Box (
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
                .background(Green),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Let's get to know you",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif
                )
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Personal information",
            style = TextStyle(
                color = Green,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = FontFamily.Serif
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "First Name",
            style = TextStyle(
                color = Green,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,

                )
        )
        CustomTextFiled(context = context) { firstName = it }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Last Name",
            style = TextStyle(
                color = Green,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,

                )
        )
        CustomTextFiled(context = context) { value -> lastName = value }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Email",
            style = TextStyle(
                color = Green,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        )
        CustomTextFiled(context = context) { value -> email = value }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        )

        CustomButton(
            text = "Register",
            onClick = {
                if(firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                    message = "Registration unsuccessful. Please enter all data."
                }
                else{
                    val sharedPreferences = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isRegistered", true)
                    editor.putString("firstName", firstName)
                    editor.putString("lastName", lastName)
                    editor.putString("email", email)
                    editor.apply()
                    message = "Registration successful!"
                    navController.navigate(Home.route)
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    val navController = rememberNavController()
    OnboardingScreen(navController)
}