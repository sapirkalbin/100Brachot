package com.axppress.hundredblessings.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axppress.hundredblessings.R
import com.axppress.hundredblessings.compose.theme.AppTheme
import com.axppress.hundredblessings.ui.fragment.foodfragment.FoodBlessingsScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainScreen
import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen {
                                playSound()
                            }
                        }

                        composable("food") {
                            val viewModel = koinViewModel<MainViewModel>()
                            FoodBlessingsScreen(viewModel)
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
