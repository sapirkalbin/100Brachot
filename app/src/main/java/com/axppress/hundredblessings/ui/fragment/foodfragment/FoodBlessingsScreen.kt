package com.axppress.hundredblessings.ui.fragment.foodfragment

import android.content.res.Configuration
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
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.compose.BlessingListItem
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.getEnumByFragmentName

@Composable
fun FoodBlessingsScreen(viewModel: MainViewModel) {
    ScreenContent(viewModel.getCurrentFragment(), onBlessingClicked = {})
}

@Composable
private fun ScreenContent(currentFragment: String, onBlessingClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        ListItems(
            getEnumByFragmentName(currentFragment),
            currentFragment
        ) {
            onBlessingClicked(it)
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
        ScreenContent("food", onBlessingClicked = {})
    }
}
