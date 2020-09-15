package com.ankushg.cocktailapp.android.ui.common

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
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
import com.ankushg.cocktailapp.android.placeholders.cocktails.asIngredientImageUrl
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.android.ui.utils.NetworkImage

@Composable
fun IngredientListItem(
    strIngredient: String,
    onClick: () -> Unit,
    quantityString: String? = null,
    modifier: Modifier = Modifier.fillMaxWidth().preferredHeight(80.dp),
    shape: Shape = RectangleShape,
    elevation: Dp = CocktailAppTheme.elevations.card,
    titleStyle: TextStyle = MaterialTheme.typography.subtitle1,
    iconSize: Dp = 80.dp
) {
    Surface(
        elevation = elevation,
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
        ) {
            NetworkImage(
                url = strIngredient.asIngredientImageUrl(),
                modifier = Modifier.preferredSize(iconSize)
            )
            Column {
                Text(
                    text = strIngredient,
                    style = titleStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 4.dp)
                )
                if (quantityString != null) {
                    Text(
                        text = quantityString,
                        color = MaterialTheme.colors.secondary,
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
            strIngredient = "Tequila",
            quantityString = quantityString,
            onClick = {}
        )
    }
}