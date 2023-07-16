package com.example.noteappcompose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteappcompose.model.Note


@Composable
fun SimpleAlertDialog(onAddNote:(Note) -> Unit,context:Context,
                      onDismiss: () -> Unit,
                      onConfirm: () -> Unit) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()){
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context,"Note Added", Toast.LENGTH_SHORT).show()
                        onConfirm.invoke()
                    }
                })
                { Text(text = "OK") }
            },
            dismissButton = {
                TextButton(onClick = {onDismiss.invoke()})
                { Text(text = "Cancel") }
            },
            title = { Text(text = "Add Note") },
            text = { Column() {
                NoteComponent(text = title, label = "Title", onTextChange = {
                    if (it.all { char->
                            char.isLetter() || char.isWhitespace()
                        })  title = it
                }, modifier = Modifier.padding(
                    top = 9.dp, bottom = 8.dp
                ))

                NoteComponent(text = description, label = "Add a note", onTextChange = {
                    if (it.all { char->
                            char.isLetter() || char.isWhitespace()
                        })  description = it
                }, modifier = Modifier.padding(
                    top = 9.dp, bottom = 8.dp
                ))

            }
            }
        )

}
