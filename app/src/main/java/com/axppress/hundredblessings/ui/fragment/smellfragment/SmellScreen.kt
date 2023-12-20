package com.axppress.hundredblessings.ui.fragment.smellfragment

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.axppress.hundredblessings.compose.theme.AppTheme

@Composable
fun SmellScreen() {

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SmellScreenPreview() {
    AppTheme {
        SmellScreen()
    }
}
