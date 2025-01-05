package com.ahmed.littlelemoncapstone.composables

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.ahmed.littlelemoncapstone.CustomTextView
import com.ahmed.littlelemoncapstone.Onboarding
import com.ahmed.littlelemoncapstone.R
import com.ahmed.littlelemoncapstone.ui.theme.Green
import com.ahmed.littlelemoncapstone.ui.theme.White

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val firstName = getStoredValues(context, "firstName", "")
    val lastName = getStoredValues(context, "lastName", "")
    val email = getStoredValues(context, "email", "")

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ){
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .size(40.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
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
                .height(50.dp),
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
        CustomTextView(text = firstName)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
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
        CustomTextView(text = lastName)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = "Email",
            style = TextStyle(
                color = Green,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
        )
        CustomTextView(text = email)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
        )

        CustomButton(
            text = "Log Out",
            onClick = {
                val sharedPreferences = context.getSharedPreferences(("userPrefs"), MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                navController.navigate(Onboarding.route)
            }

        )

    }
}

fun getStoredValues(context: Context, text: String, defaultValue: String): String {
    val sharedPreferences = context.getSharedPreferences("userPrefs", MODE_PRIVATE)
    return sharedPreferences.getString(text, defaultValue)?: defaultValue
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}
