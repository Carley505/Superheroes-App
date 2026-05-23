package com.example.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.data.DataSource
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding()
                ) {
                    HeroApp()
                }
            }
        }
    }
}

@Composable
fun HeroApp() {
    Scaffold(
        topBar = {
            SuperHeroesTopAppBar()
        }
    ) {it->
        HeroList(DataSource().loadHeroes(), contentPadding = it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@Composable
fun HeroList(heroes: List<Hero>, contentPadding: PaddingValues){
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        items(heroes) {hero ->
            HeroCard(hero)
        }
    }
}

@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)).sizeIn(minHeight = 72.dp)
        ) {
            CardLeft(title = hero.title, description = hero.description, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
            CardRight(image = hero.image, title = hero.title)
        }
    }
}

@Composable
fun CardLeft(title: Int, description: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CardRight(image: Int, title: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = stringResource(title),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.size(dimensionResource(R.dimen.image_size)).clip(MaterialTheme.shapes.small)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HeroAppPreview() {
    SuperheroesAppTheme() {
        HeroApp()
    }
}