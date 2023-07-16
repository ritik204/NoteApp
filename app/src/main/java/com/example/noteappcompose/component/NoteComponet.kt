package com.example.noteappcompose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.noteappcompose.model.Note
import com.example.noteappcompose.utils.fromDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteComponent(modifier: Modifier =Modifier,
                  text:String,
                  label:String,
                  maxLine:Int = 1,
                  onTextChange:(String) ->Unit,
                  onImeAction: () ->Unit ={}
                        ){
    val keyboardController =LocalSoftwareKeyboardController.current
    TextField(value = text, onValueChange =onTextChange , colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent),
        maxLines = maxLine,
        label = { Text(text = label)},
        keyboardOptions  = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions { onImeAction()
            keyboardController?.hide()
        },
        modifier = modifier
    )

}

@Composable
fun NoteRow(
    modifier: Modifier,
    note: Note,
    onClick:(Note) ->Unit,
    context:Context
){
    Card(modifier = Modifier
        .fillMaxWidth(),
        shape = RoundedCornerShape(2.dp),
        contentColor = Color.Black,
        backgroundColor = Color.Transparent,
        elevation = 2.dp,
    ) {
        Column(
            modifier
                .clickable {
                    onClick(note)
                    Toast.makeText(context,"Note Deleted Successfully",Toast.LENGTH_SHORT).show()
                }
                .padding(15.dp)
                ,
            horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = note.title,
                style = MaterialTheme.typography.body1, modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = note.description, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = fromDate(note.entryDate.time), style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.width(4.dp))

        }

    }
}