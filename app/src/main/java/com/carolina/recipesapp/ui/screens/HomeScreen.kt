package com.carolina.recipesapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carolina.recipesapp.R
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.model.RecipesListViewModel
import com.carolina.recipesapp.ui.components.RecipeGridCard
import com.carolina.recipesapp.ui.theme.poppinsFamily

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(onNavigateToDetail: (String) -> Unit, recipes: List<Recipe>, viewModel: RecipesListViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Recipes",
                color = colorResource(id = R.color.eerie_black),
                fontSize = 36.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Divider(startIndent = 8.dp, thickness = 1.dp, color = colorResource(id = R.color.french_gray))

            SearchComponent(viewModel)

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = 200.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(recipes) {
                        RecipeGridCard(it, onNavigateToDetail)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchComponent(viewModel: RecipesListViewModel) {
    var query by remember {
        mutableStateOf("")
    }

    var isFilterSelected by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(5f).padding(0.dp, 0.dp, 8.dp, 0.dp),
            value = query,
            onValueChange = { newText ->
                query = newText
                Log.i("Carolina query", query)
                if (query.isNotEmpty()) {
                    viewModel.searchRecipe(query)
                } else {
                    viewModel.getRecipesData()
                }
            },
            shape = RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search Icon",
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = colorResource(id = R.color.french_gray),
                unfocusedIndicatorColor = colorResource(id = R.color.french_gray),
            ),
            singleLine = true,

        )

        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            onClick = { isFilterSelected = !isFilterSelected },
        ) {
            Image(
                modifier = Modifier.height(50.dp),
                painter = painterResource(id = R.drawable.baseline_filter_list_24),
                contentDescription = "filter vector",

            )
        }
    }
    Filters(isFilterSelected, viewModel)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Filters(isVisible: Boolean = false, viewModel: RecipesListViewModel) {
    val filters = listOf(
        Triple("Tomatoes", "\uD83C\uDF45", colorResource(id = R.color.champagne_pink)),
        Triple("Onion", "\uD83E\uDDC5", colorResource(id = R.color.dutch_white)),
        Triple("Garlic", "\uD83E\uDDC4", colorResource(id = R.color.mint_green)),
        Triple("Carrot", "\uD83E\uDD55", colorResource(id = R.color.platinum)),
        Triple("Salt", "\uD83E\uDDC2", colorResource(id = R.color.columbia_blue)),
    )
    if (isVisible) {
        LazyRow(modifier = Modifier.fillMaxWidth().padding(15.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(filters) { filter ->
                Card(modifier = Modifier.width(60.dp), shape = RoundedCornerShape(50.dp), onClick = {
                    viewModel.searchRecipe(filter.first)
                }) {
                    Text(
                        text = filter.second,
                        modifier = Modifier
                            .background(color = filter.third)
                            .padding(8.dp),
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
}

val recipeExmaple = Recipe(
    id = 1,
    recipeId = "r1",
    name = "Classic Spaghetti Bolognese",
    image = "https://www.unileverfoodsolutions.com.co/dam/global-ufs/mcos/nola/colombia/calcmenu/recipes/CO-recipes/pasta-dishes/pasta-en-salsa-bolognesa/main-header.jpg",
    shortDescription = "A traditional Italian pasta dish with rich meaty Bolognese sauce.",
    ingredients = listOf(
        "Spaghetti",
        "Ground beef",
        "Tomatoes",
        "Onion",
        "Garlic",
        "Carrot",
        "Celery",
        "Tomato paste",
        "Red wine",
        "Olive oil",
    ),
    steps = listOf(
        "Boil spaghetti until al dente.",
        "Sautee onions, garlic, carrot, and celery.",
        "Add ground beef and cook until browned.",
        "Stir in tomatoes, tomato paste, and red wine.",
        "Simmer sauce until thickened. Serve over spaghetti.",
    ),
    longitude = 4.6486251,
    latitude = -74.2478965,

)
