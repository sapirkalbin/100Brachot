package com.axppress.hundredblessings.ui.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.utils.DefaultText
import com.axppress.hundredblessings.utils.noRippleClick


@Composable
fun BlessingListItem(fragmentId: String, num: Int, onBlessingClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
            )
            .padding(8.dp)
            .noRippleClick {
                onBlessingClicked(num)
            }
    ) {
        DefaultText(
            stringResource(getResourcesCompose("${fragmentId}_fragment_${num}_header")),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                ),
            textStyleAndSize = MaterialTheme.typography.bodyLarge.merge(TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold))
        )
        DefaultText(
            stringResource(getResourcesCompose("${fragmentId}_fragment_${num}_explanation")),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
                .noRippleClick {
                    onBlessingClicked(num)
                },
            textStyleAndSize = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun getResourcesCompose(name: String) =
    LocalContext.current.resources.getIdentifier(name, "string", LocalContext.current.packageName)


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BlessingListItemPreview() {
    AppTheme {
        BlessingListItem("food", 1) {}
    }
}
