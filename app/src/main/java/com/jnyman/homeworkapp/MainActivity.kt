package com.jnyman.homeworkapp

import SampleData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.jnyman.homeworkapp.database.ProfileDatabase
import com.jnyman.homeworkapp.settings.ProfileSetting
import com.jnyman.homeworkapp.settings.Settings
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ProfileDatabase::class.java, "profile-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeworkAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home-screen") {
                    composable("home-screen") {
                        HomeScreen(
                            conversations = SampleData.conversationSamples,
                            onNavigateToConversation = { conversationName -> navController.navigate(conversationName) },
                            onNavigateToSettings = { navController.navigate("settings") }
                        )
                    }
                    SampleData.conversationSamples.forEach { conversation ->
                        composable(conversation.name) {
                            ConversationCard(
                                conversation = conversation,
                                onNavigateToHomeScreen = {
                                    navController.navigate("home-screen") {
                                        popUpTo("home-screen") {
                                            inclusive = true
                                        }
                                    }
                                },
                                dao = db.profileDao()
                            )
                        }
                    }
                    composable("settings") {
                        Settings(
                            onNavigateToSetting = { settingName -> navController.navigate(settingName) }
                        )
                    }
                    composable("profile-setting") {
                        ProfileSetting(
                            onNavigateToSettings = { navController.navigate("settings") {
                                popUpTo("settings") {
                                    inclusive = true
                                }
                            } },
                            dao = db.profileDao()
                        )
                    }
                }
            }
        }
    }
}
