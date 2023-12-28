package com.axppress.hundredblessings.ui.fragment.generalfragment

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
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
    navController.navigate(BLESSING_FRAGMENT)
}


@Composable
private fun ScreenContent(
    currentFragment: String,
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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


@Composable
fun ColumnScope.ListItems(
    enumCls: Class<out Enum<*>>,
    fragmentId: String,
    onBlessingClicked: (Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.weight(1f),
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
        ScreenContent("food", MainViewModel(), rememberNavController())
    }
}
