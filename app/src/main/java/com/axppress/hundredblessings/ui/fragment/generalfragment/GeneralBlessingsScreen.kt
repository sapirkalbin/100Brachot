package com.axppress.hundredblessings.ui.fragment.generalfragment

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.compose.BlessingListItem
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.BLESSING_FRAGMENT
import com.axppress.hundredblessings.utils.FRAGMENT_2
import com.axppress.hundredblessings.utils.FRAGMENT_4
import com.axppress.hundredblessings.utils.FRAGMENT_5
import com.axppress.hundredblessings.utils.FRAGMENT_6
import com.axppress.hundredblessings.utils.getEnumByFragmentName

@Composable
fun GeneralBlessingsScreen(viewModel: MainViewModel, navController: NavHostController) {
    ScreenContent(
        viewModel.getCurrentFragment(), viewModel, navController
    )
}

private fun onBlessingClicked(
    num: Int,
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    viewModel.onBlessingClicked(num)

    navController.navigate(
        "$BLESSING_FRAGMENT?blessing=${viewModel.getCurrentFragment()}?type=${num}?noSubList=${false}?isLong=${
            isLongBlessing(
                viewModel.getCurrentFragment(), num
            )
        }"
    )
}

fun isLongBlessing(name: String, num: Int): Boolean {
    return name == FRAGMENT_4 || name == FRAGMENT_5 || (name == FRAGMENT_2 && num == 7) || (name == FRAGMENT_6 && num == 4)
}

@Composable
private fun ScreenContent(
    currentFragment: String,
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            ListItems(
                getEnumByFragmentName(currentFragment),
                currentFragment
            ) {
                onBlessingClicked(
                    it,
                    viewModel,
                    navController
                )
            }
        }
    }
}


@Composable
fun ColumnScope.ListItems(
    enumCls: Class<out Enum<*>>,
    fragmentId: String,
    onBlessingClicked: (Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(
            count = enumCls.enumConstants.size,
            key = enumCls.enumConstants::get,
            contentType = enumCls.enumConstants::get,
        ) {
            BlessingListItem(fragmentId, it, onBlessingClicked)
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FoodBlessingsScreenPreview() {
    AppTheme {
        ScreenContent("ear", MainViewModel(), rememberNavController())
    }
}
