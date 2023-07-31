package com.carolina.recipesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.carolina.recipesapp.R
import com.carolina.recipesapp.model.RecipeDataModel
import com.carolina.recipesapp.ui.theme.poppinsFamily

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeGridCard(recipe: RecipeDataModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },

    ) {
        Card() {
            GlideImage(
                model = recipe.image,
                contentDescription = recipe.name,
            )
        }

        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = recipe.name,
                color = colorResource(id = R.color.eerie_black),
                fontSize = 18.sp,

                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = "more vector",
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    RecipeGridCard(recipe = recipeExmaple)
}

val recipeExmaple = RecipeDataModel(
    id = "r1",
    name = "Classic Spaghetti Bolognese",
    image = "https://www.unileverfoodsolutions.com.co/dam/global-ufs/mcos/nola/colombia/calcmenu/recipes/CO-recipes/pasta-dishes/pasta-en-salsa-bolognesa/main-header.jpg",
    description = "A traditional Italian pasta dish with rich meaty Bolognese sauce.",
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
    location = Pair(4.6486251, -74.2478965),

)
