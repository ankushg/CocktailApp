package com.ankushg.cocktailapp.android.ui.ingredient

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.placeholders.cocktails.strThumbnailUrl
import com.ankushg.cocktailapp.android.placeholders.cocktails.vodka
import com.ankushg.cocktailapp.android.ui.utils.NetworkImage
import com.ankushg.cocktailapp.android.ui.utils.statusBarsPadding
import com.ankushg.cocktailapp.shared.local.Ingredient

@Composable
fun IngredientDescription(
    strIngredient: String,
    upPress: () -> Unit
) {
    val ingredient = vodka


    Surface {
        ScrollableColumn {
            IngredientDescriptionHeader(ingredient, upPress)
            IngredientInformation(ingredient)
            IngredientDescriptionText(ingredient)
        }
    }
}

@Composable
private fun IngredientDescriptionText(ingredient: Ingredient) {
    ingredient.strDescription?.let { description ->
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                )
        )
    }
}

@Composable
private fun IngredientInformation(ingredient: Ingredient) {

    ingredient.strType?.let { type ->
        Text(
            text = type.toUpperCase(),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 36.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
    }

    Text(
        text = ingredient.strIngredient,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    Row {
        ingredient.strAlcohol?.let { alcohol ->
            Text(
                text = "Alcoholic: $alcohol",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 36.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            )
        }

        Spacer(Modifier.weight(1f))

        ingredient.strABV?.let { abv ->
            Text(
                text = "ABV: $abv",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 36.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            )
        }
    }
}

@Composable
private fun IngredientDescriptionHeader(ingredient: Ingredient, upPress: () -> Unit) {
    Stack {
        NetworkImage(
            url = ingredient.strThumbnailUrl,
            modifier = Modifier
                .fillMaxWidth()
                // .scrim(colors = listOf(Color(0x80000000), Color(0x33000000)))
                .aspectRatio(4f / 3f)
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White, // always white as image has dark scrim
            modifier = Modifier.statusBarsPadding()
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    asset = Icons.Rounded.ArrowBack
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview("Ingredient Detail")
@Composable
private fun IngredientDetailPreview() {
    IngredientDescription(
        strIngredient = "Vodka",
        upPress = { }
    )
}