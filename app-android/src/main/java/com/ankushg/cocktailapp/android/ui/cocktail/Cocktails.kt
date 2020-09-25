package com.ankushg.cocktailapp.android.ui.cocktail

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.ui.common.CocktailListItem
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.shared.app.ViewState
import com.ankushg.cocktailapp.shared.data.placeholders.cocktailSummaries

@Composable
fun CocktailList(
    state: ViewState.CocktailList,
    onDrinkClicked: (Long) -> Unit
) {
    val cocktails = state.cocktails

    LazyColumnFor(items = cocktails) { item ->
        CocktailListItem(
            cocktail = item,
            onClick = { onDrinkClicked(item.idDrink) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SummaryPreview() {
    val state = ViewState.CocktailList(cocktailSummaries)

    CocktailAppTheme {
        CocktailList(
            state,
            onDrinkClicked = { }
        )
    }
}
