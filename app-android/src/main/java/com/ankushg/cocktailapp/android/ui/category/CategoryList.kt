package com.ankushg.cocktailapp.android.ui.category

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.ui.common.CategoryListItem
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.shared.app.ViewState
import com.ankushg.cocktailapp.shared.data.placeholders.allDrinkCategories
import com.ankushg.cocktailapp.shared.domain.enums.DrinkCategory

@Composable
fun CategoryList(
    state: ViewState.CategoryList,
    onCategoryClicked: (DrinkCategory) -> Unit
) {
    val categories = state.categories

    LazyColumnFor(items = categories) { item ->
        CategoryListItem(
            category = item,
            onClick = { onCategoryClicked(item) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryListPreview() {
    val previewState = ViewState.CategoryList(
        categories = allDrinkCategories
    )

    CocktailAppTheme {
        CategoryList(
            previewState,
            onCategoryClicked = { }
        )
    }
}
