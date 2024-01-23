package com.example.rickandmortymindblower.characterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmortymindblower.MainViewModel
import com.example.rickandmortymindblower.R


@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    Surface(
        color = colorResource(id = R.color.background),
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            viewModel.characters.forEach {
                item {
                    CharacterBox(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        image = it.image
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterBox(
    id: String,
    name: String,
    status: String,
    species: String,
    image: String,
) {
    val textColor = colorResource(id = R.color.white)

    Box(
        modifier = Modifier
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.purple),
                        colorResource(id = R.color.lightpurple),
                    )
                )
            ),
    ) {
        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = "#$id",
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp)),
                model = image,
                contentDescription = null
            )
            Column(
                modifier = Modifier.padding(start = 20.dp),
            ) {
                Row() {
                    Text(text = "Name: ", color = textColor, fontWeight = FontWeight.Bold, fontSize = 12.sp, modifier = Modifier.padding(bottom = 25.dp))
                    Text(text = name, color = textColor, fontSize = 12.sp)
                }
                Text(
                    modifier = Modifier.padding(bottom = 25.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                fontSize = 12.sp
                            )
                        ) {
                            append("Status: ")
                        }
                        withStyle(style = SpanStyle(color = textColor, fontSize = 12.sp)) {
                            append(status)
                        }
                    }
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                fontSize = 12.sp
                            )
                        ) {
                            append("Species: ")
                        }
                        withStyle(style = SpanStyle(color = textColor, fontSize = 12.sp)) {
                            append(species)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCharacterBox() {
    CharacterBox(
        id = "0",
        name = "Tom",
        status = "Alive",
        species = "Human",
        image = ""
    )
}
