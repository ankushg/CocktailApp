package com.ankushg.cocktailapp.android.ui.common

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.android.ui.utils.NetworkImage
import com.ankushg.cocktailapp.shared.data.placeholders.margarita
import com.ankushg.cocktailapp.shared.domain.entities.DomainCocktail
import com.ankushg.cocktailapp.shared.domain.entities.strSmallUrl

@Composable
fun CocktailListItem(
    cocktail: DomainCocktail,
    onClick: () -> Unit
) {
    val drinkName = cocktail.strDrink
    val categoryString = cocktail.strCategory?.strCategory
    val imageUrl = cocktail.strSmallUrl

    Card {
        ListItem(
            modifier = Modifier
                .clickable(onClick = onClick),
            icon = {
                NetworkImage(
                    url = imageUrl
                )
            },
            text = { Text(drinkName) },
            secondaryText = categoryString?.let { category ->
                {
                    Text(
                        text = category,
                        color = CocktailAppTheme.colors.primary
                    )
                }
            }
        )
    }
}

@Preview(name = "Cocktail list item")
@Composable
private fun CocktailListItemPreviewLight() {
    CocktailListItemPreview(darkTheme = false)
}

@Preview(name = "Cocktail list item – Dark")
@Composable
private fun CocktailListItemPreviewDark() {
    CocktailListItemPreview(darkTheme = true)
}

@Composable
private fun CocktailListItemPreview(darkTheme: Boolean) {
    CocktailAppTheme(darkTheme) {
        CocktailListItem(
            cocktail = margarita,
            onClick = {}
        )
    }
}