package com.ahmed.littlelemon

import android.content.Context
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun Profile(navController: NavController){
    val context = LocalContext.current
    val firstName = getStoredValues(context, "firstName", "")
    val lastName = getStoredValues(context, "lastName", "")
    val email = getStoredValues(context, "email", "")

    Column (
        modifier = Modifier
            .background(color = colorResource(R.color.white))
    ){
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()

                .height(50.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo"
        )

        Text(
            text = "Personal information",
            textAlign = Start,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp, top = 40.dp)
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        Text(
            text = "First name",
            textAlign = Start,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        CustomText(firstName)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Text(
            text = "Last name",
            textAlign = Start,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        CustomText(lastName)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Text(
            text = "email",
            textAlign = Start,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        CustomText(email)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        Button(
            onClick = {
                // Clear the navigation stack and return to the Onboarding screen
                val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    clear()
                    apply()
                }
                // navigate to Onboarding screen
                navController.navigate(Onboarding.route)
                {
                    popUpTo(navController.graph.startDestinationId){
                        inclusive = true
                    }
                }
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.yellow),
                contentColor = colorResource(R.color.black),
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, colorResource(R.color.red)),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 20.dp, end = 20.dp)

        ) {
            Text(text = "Logout")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
    }
}

fun getStoredValues(context: Context, key : String, defaultValue : String) : String{
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, defaultValue) ?: defaultValue
}

@Composable
fun CustomText(text : String) {
    AndroidView(
        factory = {
            val textView = TextView(it).apply {
                setText(text)
                background = it.getDrawable(R.drawable.textfieldshape)
                textSize = 16f
                setPadding(20,15,20,10)
            }
            textView
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .height(35.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    val navController = rememberNavController()
    Profile(navController)
}