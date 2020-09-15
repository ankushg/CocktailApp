package com.ankushg.cocktailapp.android.ui.common

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.placeholders.cocktails.margarita
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.android.ui.utils.NetworkImage
import com.ankushg.cocktailapp.shared.local.Cocktail

@Composable
fun CocktailListItem(
    cocktail: Cocktail,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth().preferredHeight(80.dp),
    shape: Shape = RectangleShape,
    elevation: Dp = CocktailAppTheme.elevations.card,
    titleStyle: TextStyle = MaterialTheme.typography.subtitle1,
    iconSize: Dp = 16.dp
) {
    Surface(
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        Row(modifier = Modifier.clickable(onClick = onClick)) {
            NetworkImage(
                url = cocktail.strDrinkThumb,
                modifier = Modifier.aspectRatio(1f)
            )
            Column {
                Text(
                    text = cocktail.strDrink,
                    style = titleStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 4.dp)
                )
                cocktail.strCategory?.strCategory?.let { category ->
                    Text(
                        text = category,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    )

                }
            }
        }
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