package com.carolina.recipesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.carolina.recipesapp.R
import com.carolina.recipesapp.data.Recipe
import com.carolina.recipesapp.ui.theme.poppinsFamily

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeGridCard(recipe: Recipe) {
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

        Row {
            Text(
                text = recipe.name,
                color = colorResource(id = R.color.eerie_black),
                fontSize = 18.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
            )
            Image(
                modifier = Modifier.height(10.dp),
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = "more vector",
            )
        }
    }
}
