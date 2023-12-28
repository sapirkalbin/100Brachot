package com.axppress.hundredblessings.ui.fragment.blessingfragment

import android.content.res.Configuration
import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.compose.getResourcesCompose
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.DefaultText

@Composable
fun BlessingScreenHtml(viewModel: MainViewModel) {
    ScreenContent(
        viewModel.getCurrentFragment(),
        viewModel.getCurrentBlessingNum()
    )
}

@Composable
private fun ScreenContent(
    currentFragment: String,
    blessingNum: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        val text =
            Html.fromHtml(
                stringResource(getResourcesCompose("${currentFragment}_fragment_${blessingNum}_blessing")),
                Html.FROM_HTML_MODE_LEGACY
            )

        DefaultText(
            text.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
            textStyleAndSize = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BlessingScreenHtmlPreview() {
    AppTheme {
        ScreenContent("food", 0)
    }
}
