package com.axppress.hundredblessings.ui.fragment.blessingfragment

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.compose.BlessingButton
import com.axppress.hundredblessings.ui.compose.getResourcesCompose
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.HTML_FRAGMENT
import com.axppress.hundredblessings.utils.BREAD_1
import com.axppress.hundredblessings.utils.BREAD_2
import com.axppress.hundredblessings.utils.BREAD_3
import com.axppress.hundredblessings.utils.BREAD_4
import com.axppress.hundredblessings.utils.DefaultText


@Composable
fun BlessingScreen(
    viewModel: MainViewModel,
    viewController: NavHostController,
    openLink: (String) -> Unit,
) {
    ScreenContent(
        viewModel.getCurrentFragment(),
        viewModel.getCurrentBlessingNum(),
        viewModel.getBlessingListButtons(),
        viewModel,
        viewController,
        openLink
    )
}

@Composable
private fun ScreenContent(
    currentFragment: String,
    blessingNum: Int,
    blessingListButtons: List<String>,
    viewModel: MainViewModel,
    viewController: NavHostController, openLink: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        if (blessingListButtons.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 32.dp, bottom = 32.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
            ) {
                DefaultText(
                    stringResource(getResourcesCompose("${currentFragment}_fragment_${blessingNum}_header"), ""),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                        ),
                    textStyleAndSize = MaterialTheme.typography.headlineSmall.merge(
                        TextStyle(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                )
                DefaultText(
                    stringResource(getResourcesCompose("${currentFragment}_fragment_${blessingNum}_explanation")),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        ),
                    textStyleAndSize = MaterialTheme.typography.bodyLarge,
                )


                val string =
                    stringResource(getResourcesCompose("${currentFragment}_fragment_${blessingNum}_blessing"))
                val annotatedString = buildAnnotatedString {
                    pushStringAnnotation(tag = "dough", annotation = "https://google.com/policy")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append("קישור להורדת סדר הפרשת חלה")
                    }
                }

                if (string.contains("https")) {
                    ClickableText(
                        text = annotatedString,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 8.dp,
                                end = 8.dp,
                                bottom = 8.dp
                            ),
                        style = MaterialTheme.typography.headlineSmall.merge(
                            textAlign =
                            TextAlign.Center
                        ),
                        onClick = { offset ->
                            annotatedString.getStringAnnotations(
                                tag = "dough",
                                start = offset,
                                end = offset
                            ).firstOrNull()?.let {
                                openLink(string)
                            }
                        })
                } else {
                    DefaultText(
                        string,
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
        } else {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
            ) {
                ListItems(
                    blessingListButtons,
                    currentFragment
                ) {
                    when (blessingListButtons[it]) {
                        BREAD_1 -> onBlessingClicked(
                            60,
                            viewModel, viewController, BREAD_1
                        )

                        BREAD_2 -> onBlessingClicked(
                            61,
                            viewModel, viewController, BREAD_2
                        )

                        BREAD_3 -> onBlessingClicked(
                            62,
                            viewModel, viewController, BREAD_3
                        )

                        BREAD_4 -> onBlessingClicked(
                            63,
                            viewModel, viewController, BREAD_4
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ColumnScope.ListItems(
    buttons: List<String>,
    fragmentId: String,
    onBlessingClicked: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(
            count = buttons.size,
            key = buttons::get,
            contentType = buttons::get,
        ) {
            BlessingButton(buttons[it], it) { onBlessingClicked(it) }
        }
    }
}

private fun onBlessingClicked(
    num: Int,
    viewModel: MainViewModel,
    navController: NavHostController, header: String,
) {
    viewModel.onBlessingClicked(num)
    navController.navigate(route = "$HTML_FRAGMENT/$header")
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BlessingScreenPreview() {
    AppTheme {
        ScreenContent("food", 0, emptyList(), MainViewModel(), rememberNavController()) {

        }
    }
}
