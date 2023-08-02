package com.carolina.recipesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.carolina.recipesapp.R
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.model.IngredientsList
import com.carolina.recipesapp.model.RecipesListViewModel
import com.carolina.recipesapp.ui.theme.poppinsFamily
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DetailScreen(recipeId: String, navController: NavController, recipes: List<Recipe>) {

    val recipe = recipes.find { item -> item.recipeId == recipeId } ?: recipeExmaple
    val finalList = getEmojiesList(recipe.ingredients)
    val steps = recipe.steps
    val location = Pair(recipe.latitude, recipe.longitude)
    Surface(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            item { DetailHeader(recipe, navController) }

            item {
                Text(
                    modifier = Modifier.padding(12.dp, 10.dp),
                    text = "Description",
                    color = colorResource(id = R.color.eerie_black),
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(12.dp, 10.dp),
                    text = recipe.shortDescription,
                    color = colorResource(id = R.color.onyx),
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(12.dp, 10.dp),
                    text = "Ingredients",
                    color = colorResource(id = R.color.eerie_black),
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            }

            item {
                LazyRow(modifier = Modifier.padding(12.dp, 0.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(finalList) { item ->
                        IngredientComponent(item)
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(10.dp, 10.dp),
                    text = "Cooking instructions",
                    color = colorResource(id = R.color.eerie_black),
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            }

            itemsIndexed(steps) { index, item ->
                CookingSteps(index + 1, item)
            }

            item {
                Text(
                    modifier = Modifier.padding(12.dp, 10.dp),
                    text = "Location",
                    color = colorResource(id = R.color.eerie_black),
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
            }

            item {
                RecipeLocation(location)
            }
        }
    }
}

fun getEmojiesList(ingredients: List<String>): List<Pair<String, String>> {
    val result = mutableListOf<Pair<String, String>>()
    val pairs = IngredientsList

    for (item in ingredients) {
        val pair = pairs.find { it.first == item }
        if (pair != null) {
            result.add(pair)
        } else {
            result.add(Pair(item, "\uD83E\uDDC2"))
        }
    }
    return result
}

@Composable
fun IngredientComponent(ingredient: Pair<String, String>) {
    Column(
        modifier = Modifier.width(60.dp),
    ) {
        Card(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .background(
                    color = colorResource(id = R.color.pale_dogwood),
                    shape = RoundedCornerShape(18.dp),
                ),
        ) {
            Text(
                text = ingredient.second,
                modifier = Modifier
                    .background(color = colorResource(id = R.color.pale_dogwood))
                    .padding(8.dp),
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            text = ingredient.first,
            fontSize = 10.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(0.dp, 8.dp),
        )
    }
}

@Composable
fun OverlappingBoxes(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measure, constraints ->
        val largeBox = measure[0]
        val smallBox = measure[1]
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        val largePlaceable = largeBox.measure(looseConstraints)
        val smallPlaceable = smallBox.measure(looseConstraints)
        layout(
            width = constraints.maxWidth,
            height = largePlaceable.height + smallPlaceable.height / 2,
        ) {
            largePlaceable.placeRelative(
                x = 0,
                y = 0,
            )
            smallPlaceable.placeRelative(
                x = (constraints.maxWidth - smallPlaceable.width) / 2,
                y = largePlaceable.height - smallPlaceable.height / 2,
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailHeader(
    recipe: Recipe,
    navController: NavController,
) {
    OverlappingBoxes(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp),
        ) {
            GlideImage(
                model = recipe.image,
                contentScale = ContentScale.Crop,
                contentDescription = recipe.name,
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.width(50.dp).height(50.dp).clickable {
                        navController.popBackStack()
                    },
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_left_24),
                    contentDescription = "back icon",

                )
            }
        }

        RecipeTitle(recipe)
    }
}

@Composable
fun RecipeTitle(recipe: Recipe) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = recipe.name,
                color = colorResource(id = R.color.eerie_black),
                fontSize = 20.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Text(
                text = "${recipe.ingredients.size} ingredients",
                color = colorResource(id = R.color.french_gray),
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun CookingSteps(index: Int, step: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 4.dp),
        backgroundColor = colorResource(id = R.color.cream),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,

    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Step: $index",
                fontSize = 16.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.mikado_yellow),
            )
            Text(
                text = step,
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.onyx),

            )
        }
    }
}

@Composable
fun RecipeLocation(location: Pair<Double, Double>) {
    val recipeLocation = LatLng(location.first, location.second)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipeLocation, 10f)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 8.dp)
            .height(200.dp),
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = recipeLocation),
                title = "Singapore",
                snippet = "Marker in Singapore",
            )
        }
    }
}
