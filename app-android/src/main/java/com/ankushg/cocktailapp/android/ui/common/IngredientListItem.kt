package com.ankushg.cocktailapp.android.ui.common

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.placeholders.cocktails.RecipeIngredient
import com.ankushg.cocktailapp.android.placeholders.cocktails.strSmallImageUrl
import com.ankushg.cocktailapp.android.placeholders.cocktails.vodka
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.android.ui.utils.NetworkImage
import com.ankushg.cocktailapp.shared.local.Ingredient

@Composable
fun RecipeIngredientListItem(
    recipeIngredient: RecipeIngredient,
    onClick: () -> Unit,
) {
    IngredientListItem(
        strThumbnailUrl = recipeIngredient.strThumbnailUrl,
        strIngredient = recipeIngredient.strIngredient,
        onClick = onClick,
        quantityString = recipeIngredient.strMeasure
    )
}

@Composable
fun IngredientListItem(
    ingredient: Ingredient,
    onClick: () -> Unit
) {
    IngredientListItem(
        strThumbnailUrl = ingredient.strSmallImageUrl,
        strIngredient = ingredient.strIngredient,
        onClick = onClick
    )
}

@Composable
private fun IngredientListItem(
    strThumbnailUrl: String,
    strIngredient: String,
    onClick: () -> Unit,
    quantityString: String? = null
) {
    Card {
        ListItem(
            modifier = Modifier.clickable(onClick = onClick),
            icon = strThumbnailUrl?.let { thumbnailUrl ->
                {
                    NetworkImage(url = thumbnailUrl)
                }
            },
            text = { Text(strIngredient) },
            secondaryText = quantityString?.let { { Text(it) } }
        )
    }
}

@Preview(name = "Ingredient list item")
@Composable
private fun IngredientListItemPreviewLight() {
    IngredientListItemPreview(darkTheme = false)
}

@Preview(name = "Ingredient list item")
@Composable
private fun IngredientListItemWithoutsQuantityPreviewLight() {
    IngredientListItemPreview(darkTheme = false, withQuantity = false)
}

@Preview(name = "Ingredient list item – Dark")
@Composable
private fun IngredientListItemPreviewDark() {
    IngredientListItemPreview(darkTheme = true)
}

@Composable
private fun IngredientListItemPreview(darkTheme: Boolean, withQuantity: Boolean = true) {
    val quantityString = if (withQuantity) "2 oz" else null

    CocktailAppTheme(darkTheme) {
        IngredientListItem(
            ingredient = vodka,
            onClick = {}
        )
    }
}