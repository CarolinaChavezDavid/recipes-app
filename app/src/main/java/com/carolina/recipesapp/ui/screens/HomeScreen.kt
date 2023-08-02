package com.carolina.recipesapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carolina.recipesapp.R
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.ui.components.RecipeGridCard
import com.carolina.recipesapp.ui.theme.poppinsFamily

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(onNavigateToDetail: (String) -> Unit, recipes: List<Recipe>) {
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

            Row(modifier = Modifier.fillMaxWidth().padding(12.dp, 8.dp)) {
                OutlinedTextField(
                    modifier = Modifier.height(30.dp),
                    value = "",
                    onValueChange = {},
                    singleLine = true,
                )

                Card(
                    modifier = Modifier.width(30.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Image(
                        modifier = Modifier.height(30.dp),
                        painter = painterResource(id = R.drawable.baseline_filter_list_24),
                        contentDescription = "filter vector",

                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
            ) {
                items(recipes) {
                    RecipeGridCard(it, onNavigateToDetail)
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
}
