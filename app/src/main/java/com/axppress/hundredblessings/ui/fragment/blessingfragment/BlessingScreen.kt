package com.axppress.hundredblessings.ui.fragment.blessingfragment

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.axppress.hundredblessings.R
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.compose.BlessingButton
import com.axppress.hundredblessings.ui.compose.getResourcesCompose
import com.axppress.hundredblessings.ui.fragment.mainfragment.KeysViewModel
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.BLESSING_FRAGMENT
import com.axppress.hundredblessings.utils.BREAD_1
import com.axppress.hundredblessings.utils.BREAD_2
import com.axppress.hundredblessings.utils.BREAD_3
import com.axppress.hundredblessings.utils.BREAD_4
import com.axppress.hundredblessings.utils.DefaultText
import com.axppress.hundredblessings.utils.Utils
import com.axppress.hundredblessings.utils.noRippleClick
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun BlessingScreen(
    currentFragment: String,
    currentBlessingNum: Int,
    isLong: Boolean,
    listOfButtons: List<String>,
    viewModel: MainViewModel,
    keysViewModel: KeysViewModel,
    viewController: NavHostController,
    openLink: (String) -> Unit,
    addBlessing: (Int) -> Unit,
) {
    ScreenContent(
        currentFragment,
        currentBlessingNum,
        isLong,
        listOfButtons,
        viewModel,
        keysViewModel,
        viewController,
        openLink, addBlessing
    )
}

@Composable
private fun ScreenContent(
    currentFragment: String,
    blessingNum: Int,
    isLong: Boolean,
    blessingListButtons: List<String>,
    viewModel: MainViewModel,
    keysViewModel: KeysViewModel,
    viewController: NavHostController, openLink: (String) -> Unit,
    addBlessing: (Int) -> Unit,
) {
    var volume by remember {
        mutableIntStateOf(keysViewModel.getVolume().value)
    }

    LaunchedEffect(key1 = Unit) {
        launch {
            keysViewModel.getVolume().collectLatest {
                volume = it
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
            )
    ) {
        DefaultText(
            stringResource(
                getResourcesCompose("${currentFragment}_fragment_${blessingNum}_header"),
                ""
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    8.dp
                ),
            textStyleAndSize = MaterialTheme.typography.headlineSmall.merge(
                TextStyle(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = if (isLong) 32.dp else 0.dp),
        contentAlignment = Alignment.Center
    ) {
        if (blessingListButtons.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 32.dp, bottom = 40.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
            ) {
                val explanation =
                    stringResource(getResourcesCompose("${currentFragment}_fragment_${blessingNum}_explanation"))
                if (explanation.isNotEmpty())
                    DefaultText(
                        explanation,
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
                                bottom = if (isLong) 40.dp else 8.dp
                            ),
                        textStyleAndSize = if (!isLong) TextStyle(fontSize = volume.sp) else
                            TextStyle(fontSize = volume.sp),
                    )
                }

            }

            if (Utils.getBlessingNum(currentFragment, blessingNum) > 0) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 16.dp, end =
                                16.dp, start = 16.dp
                            )
                            .background(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .padding(
                                8.dp
                            )
                            .noRippleClick {
                                addBlessing(Utils.getBlessingNum(currentFragment, blessingNum))
                                viewController.navigateUp()
                                viewController.navigateUp()
                            }, horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.blessing),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp),
                        )

                        DefaultText(
                            "בירכתי",
                            modifier = Modifier,
                            textStyleAndSize = MaterialTheme.typography.bodyLarge.merge(
                                TextStyle(
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                        )

                    }
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
                            viewModel, viewController
                        )

                        BREAD_2 -> onBlessingClicked(
                            61,
                            viewModel, viewController
                        )

                        BREAD_3 -> onBlessingClicked(
                            62,
                            viewModel, viewController
                        )

                        BREAD_4 -> onBlessingClicked(
                            63,
                            viewModel, viewController
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
    navController: NavHostController,
) {
    navController.navigate("$BLESSING_FRAGMENT?blessing=${viewModel.getCurrentFragment()}?type=${num}?noSubList=${true}?isLong=${true}")
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BlessingScreenPreview() {
    AppTheme {
        ScreenContent(
            "food",
            0,
            false,
            emptyList(),
            MainViewModel(),
            KeysViewModel(),
            rememberNavController(),
            {}) {

        }
    }
}
