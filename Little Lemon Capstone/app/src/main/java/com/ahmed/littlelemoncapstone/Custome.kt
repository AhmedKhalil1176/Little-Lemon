package com.ahmed.littlelemoncapstone

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.ahmed.littlelemoncapstone.ui.theme.Orange
import com.ahmed.littlelemoncapstone.ui.theme.Yellow

@Composable
fun CustomTextFiled(context: Context, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    AndroidView(
        factory = {
            val editText = EditText(context).apply {
                setText(text)
                textSize = 16f
                setPadding(30,10,20,10)
                background = context.getDrawable(R.drawable.textfieldshape)
                addTextChangedListener { editable ->
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

@Composable
fun CustomButton(
    text : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Yellow,
    textColor: Color = Color.Black,
    borderStroke: BorderStroke = BorderStroke(1.dp, Orange)
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        border = borderStroke

    ){
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun CustomTextView(text : String){
    AndroidView(
        factory = {
            val textView = TextView(it).apply {
                setText(text)
                setPadding(30,20,20,10)
                background = it.getDrawable(R.drawable.textfieldshape)
                textSize = 16f
            }
            textView
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 5.dp)
            .height(35.dp)
    )
}
