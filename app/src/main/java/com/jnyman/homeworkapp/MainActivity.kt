package com.jnyman.homeworkapp

import SampleData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.jnyman.homeworkapp.database.Conversation
import com.jnyman.homeworkapp.database.ConversationDao
import com.jnyman.homeworkapp.database.ConversationDatabase
import com.jnyman.homeworkapp.database.Profile
import com.jnyman.homeworkapp.database.ProfileDatabase
import com.jnyman.homeworkapp.settings.ProfileSetting
import com.jnyman.homeworkapp.settings.Settings
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme

class MainActivity : ComponentActivity() {

    private val profileDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            ProfileDatabase::class.java, "profile-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    private val conversationDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            ConversationDatabase::class.java, "conversation-database"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeworkAppTheme {

                val profileDao = profileDb.profileDao()
                val conversationDao = conversationDb.conversationDao()

                conversationDao.upsertConversation(SampleData.conversationSamples[0])

                var conversations = conversationDao.getConversationsOrderedByName()

                val ownProfile = Profile(
                    nickName = "New Profile",
                    profilePictureUri = "",
                    own = true,
                    id = 1
                )

                profileDao.upsertProfile(ownProfile)

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home-screen") {
                    composable("home-screen") {
                        HomeScreen(
                            conversationDao = conversationDao,
                            onNavigateToConversation = { conversationName -> navController.navigate(conversationName) },
                            onNavigateToSettings = { navController.navigate("settings") }
                        )
                    }
                    conversations.forEach { conversation ->
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
                                dao = profileDao
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
                            dao = profileDao
                        )
                    }
                }
            }
        }
    }

}
