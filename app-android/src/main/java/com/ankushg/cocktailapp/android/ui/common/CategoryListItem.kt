package com.ankushg.cocktailapp.android.ui.common

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalDrink
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory

@Composable
fun CategoryListItem(
    category: DrinkCategory,
    onClick: () -> Unit
) {
    Card {
        ListItem(
            modifier = Modifier.clickable(onClick = onClick),
            icon = {
                Icon(
                    asset = Icons.Rounded.LocalDrink,
                    tint = CocktailAppTheme.colors.primary
                )
            },
            text = { Text(category.strCategory) },
        )
    }
}

@Preview(name = "category list item")
@Composable
private fun CocktailListItemPreviewLight() {
    CocktailListItemPreview(darkTheme = false)
}

@Preview(name = "category list item – Dark")
@Composable
private fun CocktailListItemPreviewDark() {
    CocktailListItemPreview(darkTheme = true)
}

@Composable
private fun CocktailListItemPreview(darkTheme: Boolean) {
    CocktailAppTheme(darkTheme) {
        CategoryListItem(
            category = DrinkCategory.ORDINARY_DRINK,
            onClick = {}
        )
    }
}