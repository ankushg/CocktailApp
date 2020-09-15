package com.ankushg.cocktailapp.android.ui.common

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.shared.data.enums.DrinkCategory

@Composable
fun CategoryListItem(
    category: DrinkCategory,
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
            Column {
                Text(
                    text = category.strCategory,
                    style = titleStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 4.dp)
                )
            }
        }
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