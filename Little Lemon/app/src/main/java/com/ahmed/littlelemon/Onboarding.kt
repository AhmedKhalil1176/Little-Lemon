package com.ahmed.littlelemon

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun Onboarding(navController: NavController){
    val context = LocalContext.current
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

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
                .height(10.dp)

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(colorResource(R.color.TextBackgroundColor)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color.White,
                textAlign = Center
            )
        }

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
        CustomTextField(context, ""){ value -> firstName = value}
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Text(
            text = "Last Name",
            textAlign = Start,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        CustomTextField(context, "") { value -> lastName = value }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Text(
            text = "Email",
            textAlign = Start,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        CustomTextField(context, "") { value -> email = value }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )

        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    message = "Registration unsuccessful. Please enter all data."
                } else {
                    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE )
                    with(sharedPreferences.edit()) {
                        putString("firstName", firstName)
                        putString("lastName", lastName)
                        putString("email", email)
                        putBoolean("isRegistered", true)
                        apply()
                    }
                    message = "Registration successful!"
                    navController.navigate(Home.route)
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
            Text(text = "Register")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

    }
}

@Composable
fun CustomTextField( context: Context, initialValue : String, onValueChange: (String) -> Unit ) {
    var text by remember { mutableStateOf("") }
    AndroidView(
        factory = {
            val editText = EditText(context).apply {
                background = context.getDrawable(R.drawable.textfieldshape)
                setText(text)
                textSize = 16f
                setPadding(20,10,20,10)

                addTextChangedListener {editable ->
                    text = editable?.toString() ?: ""
                    onValueChange(text)
                }
            }
            editText
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 5.dp)
            .height(35.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    val navController = rememberNavController()
    Onboarding( navController)
}

