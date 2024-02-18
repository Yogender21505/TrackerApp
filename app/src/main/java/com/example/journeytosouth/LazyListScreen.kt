package com.example.journeytosouth

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.runtime.MutableState

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LazyListScreen(modifier: Modifier.Companion = Modifier) {
    val numberOfSteps = 25
    val currentStep = remember { mutableStateOf(0) }
    val currentUnit = remember { mutableStateOf("km") }
    val distanceCovered = remember { mutableStateOf(calculateDistanceCovered(currentStep.value, currentUnit.value)) }
    val distanceRequired = remember { mutableStateOf(calculateDistanceRequired(currentStep.value, numberOfSteps, currentUnit.value)) }
    val stationName = listOf( "Kanpur Central", "Kanpur Terminal", "Govindpuri",
        "Panki Dham", "Rura", "Phaphund", "Bharthana", "Etawah Junction",
        "Jaswantnagar", "Sikohabad", "Firozabad", "Tundla", "Hathras",
        "Aligarh", "Khurja", "Mathura", "Vrindavan", "Mathura Cantt", "Agra Fort",
        "Idgah Agra", "Agra City", "Agra", "Palwal", "Faridabad", "Delhi")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Fixed elements
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    if (currentStep.value < numberOfSteps-1) {
                        currentStep.value++
                        updateDistances(currentStep.value, numberOfSteps, currentUnit.value, distanceCovered, distanceRequired)
                    } else {
                        Toast.makeText(context, "You reached your destination!", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text(text = "NextStop", color = Color.White)
            }

            Switch(
                checked = currentUnit.value == "Miles",
                onCheckedChange = {
                    currentUnit.value = if (it) "Miles" else "km"
                    updateDistances(currentStep.value, numberOfSteps, currentUnit.value, distanceCovered, distanceRequired)
                },
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp)
        ) {
            progressBar(modifier,currentStep,numberOfSteps)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 1.dp)
            ) {
                item {
                    for (step in 0 until numberOfSteps) {
                        List_of_TextBox(
                            modifier = Modifier.weight(1f),
                            isComplete = step < currentStep.value,
                            isCurrent = step == currentStep.value,
                            stationName[step],
                            distanceRequired.value,
                            distanceCovered.value,
                            currentUnit.value,
                            stationName[numberOfSteps-1],
                            numberOfSteps
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun progressBar(modifier: Modifier, currentStep: MutableState<Int>, numberOfSteps: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        horizontalArrangement = Arrangement.SpaceBetween
    )  {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            progress = currentStep.value.toFloat() / (numberOfSteps-1).toFloat(),
            color = Color.Red
        )
    }
}

private fun calculateDistanceCovered(steps: Int, unit: String): Int {
    val conversionFactor = if (unit == "km") 1.0 else 0.621371
    return (steps * 100 * conversionFactor).toInt()
}

private fun calculateDistanceRequired(steps: Int, totalSteps: Int, unit: String): Int {
    val conversionFactor = if (unit == "km") 1.0 else 0.621371
    return ((totalSteps - steps) * 100 * conversionFactor).toInt()
}

private fun updateDistances(steps: Int, totalSteps: Int, unit: String, distanceCovered: MutableState<Int>, distanceRequired: MutableState<Int>) {
    distanceCovered.value = calculateDistanceCovered(steps, unit)
    distanceRequired.value = calculateDistanceRequired(steps, totalSteps, unit)
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun List_of_TextBox(
    modifier: Modifier = Modifier,
    isComplete: Boolean,
    isCurrent: Boolean,
    currentStation: String,
    distReq: Int,
    distCov: Int,
    unit: String,
    destination: String,
    numberOfSteps: Int
) {
    val color = if (isComplete || isCurrent) Color.Black else Color.LightGray
    val colortxt = if (isComplete || isCurrent) Color.White else Color.Black
    var islast= (destination===currentStation)
    Column(
        modifier = modifier
            .width(350.dp)
    ) {
        repeat(1) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .width(200.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            ) {

                if (isCurrent) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(color)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            // Show details only if it is the current text box
                            if (isCurrent) {
                                Row() {
                                    Text(
                                        text = "from: $currentStation",
                                        color = colortxt,
                                        style = TextStyle(
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Text(
                                        text = "to: Delhi",
                                        color = colortxt,
                                        style = TextStyle(
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                if(islast){
                                    var dist=calculateDistanceRequired(0, numberOfSteps, unit)
                                    Row() {
                                        Text(
                                            text = "Distance Covered: $dist$unit",
                                            color = colortxt,
                                            style = TextStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier
                                                .padding(8.dp)
                                        )
                                        Text(
                                            text = "Distance Remaining: 0$unit",
                                            color = colortxt,
                                            style = TextStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier
                                                .padding(8.dp)
                                        )
                                    }
                                }
                                else{
                                    Row() {
                                        Text(
                                            text = "Distance Covered: $distCov$unit",
                                            color = colortxt,
                                            style = TextStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier
                                                .padding(8.dp)
                                        )
                                        Text(
                                            text = "Distance Remaining: $distReq$unit",
                                            color = colortxt,
                                            style = TextStyle(
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier
                                                .padding(8.dp)
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
                else{
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(Color.LightGray)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {
                            // Show details only if it is the current text box
                            Row() {
                                Text(
                                    text = "from: $currentStation",
                                    color = colortxt,
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "to: $destination",
                                    color = colortxt,
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
private fun NewPagePreview() {
    LazyListScreen(Modifier)
}