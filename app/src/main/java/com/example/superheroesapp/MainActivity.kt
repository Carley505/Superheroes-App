package com.example.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    HeroList(DataSource().loadHeroes())
}
@Composable
fun HeroList(heroes: List<Hero>){
    LazyColumn(
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
    Card(modifier = modifier.fillMaxWidth().clip(MaterialTheme.shapes.small)) {
        Row(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            CardLeft(title = hero.title, description = hero.description)
            Spacer(modifier = Modifier.weight(1f))
            CardRight(image = hero.image, title = hero.title)
        }
    }
}

@Composable
fun CardLeft(title: Int, description: Int) {
    Column() {
        Text(
            text = stringResource(title)
        )
        Text(
            text = stringResource(description)
        )
    }
}

@Composable
fun CardRight(image: Int, title: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = stringResource(title),
        modifier = Modifier.size(dimensionResource(R.dimen.padding_large)).clip(MaterialTheme.shapes.small)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HeroAppPreview() {
    HeroApp()
}