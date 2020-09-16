package com.ankushg.cocktailapp.android.ui.category

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.placeholders.cocktails.allDrinkCategories
import com.ankushg.cocktailapp.android.ui.common.CategoryListItem
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory

@Composable
fun CategoryList(
    categories: List<DrinkCategory>,
    onClick: (DrinkCategory) -> Unit
) {
    LazyColumnFor(items = categories) { item ->
        CategoryListItem(
            category = item,
            onClick = { onClick(item) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryListPreview() {
    CocktailAppTheme {
        CategoryList(
            allDrinkCategories,
            onClick = { }
        )
    }
}
