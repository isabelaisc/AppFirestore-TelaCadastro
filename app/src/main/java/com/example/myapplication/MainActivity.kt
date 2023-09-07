package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextOverflow
import com.example.myapplication.ui.theme.orangeOfc
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Fundo branco
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar()
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldWithPlaceholder()
        Spacer(modifier = Modifier.height(13.dp))
        SimpleTextFieldSample()
        Spacer(modifier = Modifier.height(13.dp))
        ExposedDropdownMenuSample()
        Spacer(modifier = Modifier.height(13.dp))
        DatePickerSample()
        Spacer(modifier = Modifier.height(13.dp))
        TextAreaSample()
        Spacer(modifier = Modifier.height(13.dp))
        CadButton()
        Spacer(modifier = Modifier.height(15.dp))
        CancelButton()
        Spacer(modifier = Modifier.height(20.dp))

    }
}


// Barra Superior "AppFirestore - Cadastro"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = { Text("AppFirestore - Cadastro") },
    )
}

// Nome - TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithPlaceholder() {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), // Espaçamento horizontal reduzido
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Nome Completo") },
            singleLine = true
        )
    }
}

// Telefone - TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextFieldSample() {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), // Espaçamento horizontal reduzido
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Telefone") },
            singleLine = true
        )
    }
}

// Origem - ComboBox
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuSample() {
    val options = listOf("", "", "", "", "")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), // Espaçamento horizontal reduzido
        horizontalAlignment = Alignment.Start
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text("Origem") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
    }
}

// Data de Contato - Date
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // Pre-select a date for January 4, 2020
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
        DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
    }
}

// Observação - TextArea
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextAreaSample() {
    var observacao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp), // Espaçamento horizontal reduzido
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = observacao,
            onValueChange = { newValue ->
                observacao = newValue
            },
            label = { Text("Observação") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp) // Definindo uma altura mínima para a TextArea
        )
    }
}

// Botão Cadastrar
@Composable
fun CadButton() {
    ExtendedFloatingActionButton(
        onClick = { /* do something */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) // Altura reduzida
            .padding(horizontal = 30.dp), // Espaçamento horizontal reduzido
        containerColor = Color.Magenta, // Usando a cor laranja personalizada
        contentColor = Color.White,
        shape = MaterialTheme.shapes.small.copy(CornerSize(10.dp)) // Bordas menos arredondadas
    )

    {
        Text(
            text = "Cadastrar",
            style = TextStyle(
                fontSize = 25.sp,
                color = Color.White,
            ),
        )
    }
}

// Botão - Cancelar
@Composable
fun CancelButton() {
    ExtendedFloatingActionButton(
        onClick = { /* do something */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) // Altura reduzida
            .padding(horizontal = 30.dp), // Espaçamento horizontal reduzido
        containerColor = Color.Magenta, // Usando a cor laranja personalizada
        contentColor = Color.White,
        shape = MaterialTheme.shapes.small.copy(CornerSize(10.dp)) // Bordas menos arredondadas
    )

    {
        Text(
            text = "Cancelar",
            style = TextStyle(
                fontSize = 25.sp,
                color = Color.White,
            ),
        )
    }
}


@Preview
@Composable
fun MyAppPreview() {
    MyApp()
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}

@Preview
@Composable
fun TextFieldWithPlaceholderPreview() {
    TextFieldWithPlaceholder()
}

@Preview
@Composable
fun SimpleTextFieldSamplePreview() {
    SimpleTextFieldSample()
}

@Preview
@Composable
fun ExposedDropdownMenuSamplePreview() {
    ExposedDropdownMenuSample()
}

@Preview
@Composable
fun DatePickerSamplePreview() {
    DatePickerSample()
}

@Preview
@Composable
fun TextAreaSamplePreview() {
    TextAreaSample()
}


@Preview
@Composable
fun CadButtonPreview() {
    CadButton()
}

@Preview
@Composable
fun CancelButtonPreview() {
    CancelButton()
}
