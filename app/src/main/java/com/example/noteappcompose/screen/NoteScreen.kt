package com.example.noteappcompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteappcompose.component.NoteRow
import com.example.noteappcompose.component.SimpleAlertDialog
import com.example.noteappcompose.model.Note
import com.example.noteappcompose.ui.theme.NoteAppComposeTheme

@Composable
fun NoteScreen(note: List<Note>,
               onAddNote:(Note) ->Unit,
               onRemoveNote:(Note) ->Unit){

    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {Text(text = "Note App ")},
        actions = {Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
        }, backgroundColor = MaterialTheme.colors.primary, elevation = 6.dp)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                top = 24.dp,
                bottom = 24.dp
            )){
            items(note){note1 ->
                NoteRow(Modifier,note1,onRemoveNote,context)

            }
        }
        Box(modifier=Modifier.weight(1F).fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                onClick = {
                    showDialog.value = true
                },
                modifier= Modifier.align(alignment = Alignment.BottomEnd),
                shape = RoundedCornerShape(16.dp),

                ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        }


        if(showDialog.value){
            SimpleAlertDialog(onAddNote, context,{showDialog.value = false},{showDialog.value = false})

        }

    }


}

@Preview
@Composable
fun Preview(){
    NoteAppComposeTheme {
        NoteScreen(listOf(Note(title = "hello", description = "saving"),Note(title = "Just", description = "try to improve")),{},{})
    }
}