package com.axppress.hundredblessings.ui.fragment.specialblessingsfragment

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.fragment.foodfragment.FoodBlessingsScreen

@Composable
fun SpecialBlessingsScreen() {

}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SpecialBlessingsScreenPreview() {
    AppTheme {
        SpecialBlessingsScreen()
    }
}
