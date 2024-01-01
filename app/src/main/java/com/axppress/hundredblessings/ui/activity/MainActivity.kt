package com.axppress.hundredblessings.ui.activity

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.axppress.hundredblessings.R
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.fragment.blessingfragment.BlessingScreen
import com.axppress.hundredblessings.ui.fragment.blessinghtmlfragment.BlessingScreenHtml
import com.axppress.hundredblessings.ui.fragment.generalfragment.GeneralBlessingsScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import com.axppress.hundredblessings.utils.BLESSING_FRAGMENT
import com.axppress.hundredblessings.utils.HTML_FRAGMENT
import com.axppress.hundredblessings.utils.GENERAL_FRAGMENT
import com.axppress.hundredblessings.utils.MAIN_FRAGMENT
import com.axppress.hundredblessings.utils.addBlessing
import org.koin.androidx.compose.koinViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()
                val viewModel = koinViewModel<MainViewModel>()

                Surface {
                    NavHost(navController = navController, startDestination = MAIN_FRAGMENT) {
                        composable(MAIN_FRAGMENT) {
                            MainScreen(navController, viewModel) {
                                playSound()
                                addBlessing()
                            }
                        }

                        composable(GENERAL_FRAGMENT) {
                            GeneralBlessingsScreen(viewModel, navController)
                        }

                        composable(
                            route = "$HTML_FRAGMENT/{header}",
                            arguments = listOf(
                                navArgument("header") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val header = it.arguments?.getString("header", "") ?: ""
                            BlessingScreenHtml(viewModel, header)
                        }

                        composable(BLESSING_FRAGMENT) {
                            BlessingScreen(viewModel, navController) {
                                val browserIntent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                startActivity(browserIntent)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun playSound() {
        val mp = MediaPlayer.create(this, R.raw.harp_sound)
        mp.start()
    }
}
