package com.ahmed.littlelemon

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavGraph.Companion.findStartDestination
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
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 10.dp)
                .height(40.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo"
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
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

        CustomLogOutButton(context, "Log out", onClick = {
            // Clear the navigation stack and return to the Onboarding screen
            navController.navigate("Onboarding")
            {
                popUpTo(navController.graph.findStartDestination().id){
                    inclusive = true
                }
            }
        })

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun CustomLogOutButton(context: Context, text: String, onClick: () -> Unit) {
    AndroidView(
        factory = { ctx ->
            Button(context).apply{
                val drawable : Drawable? = ctx.getDrawable(R.drawable.button)
            background = drawable
                setText(text)
                isAllCaps = false
                setOnClickListener { onClick() }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 10.dp, start = 20.dp, end = 20.dp)
    )
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
                setPadding(20,10,20,10)
            }
            textView
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 5.dp)
            .height(35.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){

    val navController = rememberNavController()
    Profile(navController)
}