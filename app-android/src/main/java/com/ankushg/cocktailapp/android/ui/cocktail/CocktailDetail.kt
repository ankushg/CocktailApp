import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocalDrink
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ankushg.cocktailapp.android.placeholders.cocktails.margarita
import com.ankushg.cocktailapp.android.placeholders.cocktails.recipeIngredients
import com.ankushg.cocktailapp.android.ui.common.RecipeIngredientListItem
import com.ankushg.cocktailapp.android.ui.theme.CocktailAppTheme
import com.ankushg.cocktailapp.android.ui.utils.NetworkImage
import com.ankushg.cocktailapp.android.ui.utils.statusBarsPadding
import com.ankushg.cocktailapp.shared.local.Cocktail

@Composable
fun CocktailDescription(
    idDrink: Long,
    selectIngredient: (String) -> Unit,
    upPress: () -> Unit
) {
    // TODO: get viewModel, init with idDrink
    val cocktail = margarita

    CocktailDescription(
        cocktail = cocktail,
        selectIngredient = selectIngredient,
        upPress = upPress
    )
}

@Composable
fun CocktailDescription(
    cocktail: Cocktail,
    selectIngredient: (String) -> Unit,
    upPress: () -> Unit
) {
    val cocktail = margarita

    Surface {
        ScrollableColumn {
            CocktailDescriptionHeader(cocktail, upPress)
            CocktailInformation(cocktail)
            CocktailRecipeBody(cocktail)
            CocktailIngredients(cocktail, selectIngredient)
            CocktailFooter(cocktail)
        }
    }
}

@Composable
private fun CocktailDescriptionHeader(
    cocktail: Cocktail,
    upPress: () -> Unit
) {
    Stack {
        NetworkImage(
            url = cocktail.strDrinkThumb,
            modifier = Modifier
                .fillMaxWidth()
                // .scrim(colors = listOf(Color(0x80000000), Color(0x33000000)))
                .aspectRatio(4f / 3f)
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White, // always white as image has dark scrim
            modifier = Modifier.statusBarsPadding()
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    asset = Icons.Rounded.ArrowBack
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Icon(
            asset = Icons.Rounded.LocalDrink,
            modifier = Modifier
                .preferredSize(40.dp)
                .gravity(Alignment.BottomCenter)
                .offset(y = 20.dp) // overlap bottom of image
        )
    }
}

@Composable
private fun CocktailInformation(cocktail: Cocktail) {
    Text(
        text = cocktail.strCategory!!.strCategory.toUpperCase(),
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 36.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    )

    Text(
        text = cocktail.strDrink,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
private fun CocktailRecipeBody(cocktail: Cocktail) {
    if (cocktail.strInstructions != null) {
        Text(
            text = cocktail.strInstructions!!,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
private fun CocktailIngredients(
    cocktail: Cocktail,
    selectIngredient: (String) -> Unit
) {
    val ingredients = cocktail.recipeIngredients

    ingredients.forEach { ingredient ->
        RecipeIngredientListItem(
            recipeIngredient = ingredient,
            onClick = {
                selectIngredient(ingredient.strIngredient)
            }
        )
    }
}

@Composable
private fun CocktailFooter(cocktail: Cocktail) {
    cocktail.strGlass?.strGlass?.let { glass ->
        Text(text = "Serve: $glass")
    }
}

@Preview(name = "Cocktail Details")
@Composable
private fun CocktailDetailsPreview() {
    CocktailAppTheme {
        CocktailDescription(
            cocktail = margarita,
            selectIngredient = { },
            upPress = { }
        )
    }
}