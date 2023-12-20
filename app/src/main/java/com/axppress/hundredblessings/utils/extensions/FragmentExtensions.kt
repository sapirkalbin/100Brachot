package com.axppress.hundredblessings.utils.extensions

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.axppress.hundredblessings.compose.theme.AppTheme

fun Fragment.compose(content: @Composable () -> Unit): View {
    return ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                content()
            }
        }
    }
}
