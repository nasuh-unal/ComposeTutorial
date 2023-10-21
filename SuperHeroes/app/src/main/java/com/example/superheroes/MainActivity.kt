package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.Hero
import com.example.superheroes.data.HeroesRepository
import com.example.superheroes.data.HeroesRepository.heroes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HeroesApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesApp() {
    Scaffold(
        topBar = {
            SuperHeroesTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) {
                HeroesItem(
                    hero = it,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }

}

@Composable
//hero nokta diyip isimleri yerleştiriyoruz.
fun HeroesItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {//modifierı aşağıya gönderdik
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Composable
fun heroIcon(
    heroImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))//Image Boyutunu belirledik
            .padding(dimensionResource(id = R.dimen.padding_small))//Padding verdik
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = heroImage),
        contentDescription = null
    )
}


@Composable
fun heroInformation(
    heroName: Int,
    heroDescription: Int,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            text = stringResource(id = heroName),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = heroDescription),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroesTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun superHeroesPreview() {
    SuperHeroesTheme(darkTheme = false) {
        HeroesApp()
    }
}

@Preview
@Composable
fun superHeroesDarkThemePreview() {
    SuperHeroesTheme(darkTheme = true) {
        HeroesApp()
    }
}