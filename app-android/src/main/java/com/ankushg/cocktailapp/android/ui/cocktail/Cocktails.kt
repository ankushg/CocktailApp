package com.ankushg.cocktailapp.android.ui.cocktail

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.placeholders.cocktails.cocktailSummaries
import com.ankushg.cocktailapp.android.ui.common.CocktailListItem
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.shared.local.Cocktail

@Composable
fun CocktailList(
    cocktails: List<Cocktail>,
    onClick: (Cocktail) -> Unit
) {
    LazyColumnFor(items = cocktails) { item ->
        CocktailListItem(
            cocktail = item,
            onClick = { onClick(item) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    CocktailAppTheme {
        CocktailList(
            cocktails = cocktailSummaries,
            onClick = { }
        )
    }
}
