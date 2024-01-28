package com.jnyman.homeworkapp

import SampleData
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jnyman.homeworkapp.database.ConversationDao
import com.jnyman.homeworkapp.database.Conversation
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme

@Composable
fun HomeScreen(conversationDao: ConversationDao, onNavigateToConversation: (conversationName: String) -> Unit, onNavigateToSettings: () -> Unit) {

    val conversations = conversationDao.getConversationsOrderedByName()

    Column() {
        Row() {
            Text(
                text = "Awesome Messaging App",
                // Change text color
                color = MaterialTheme.colorScheme.primary,
                // Change the typography style
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 6.dp),
                fontWeight = FontWeight.Bold,
                // Set text font size
                fontSize = 26.sp
            )
            Button(
                onClick = { onNavigateToSettings() },
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 2.dp)
            ) {
                Text(
                    text = "Settings",
                    fontSize = 12.sp
                )
            }
        }
        LazyColumn {
            items(conversations) { conversation ->
                ConversationPreview(conversation = conversation, onNavigateToConversation)
            }
        }
    }
}

@Composable
fun ConversationPreview(conversation: Conversation, onNavigateToConversation: (conversationName: String) -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 4.dp,
        border = BorderStroke(1.6.dp, MaterialTheme.colorScheme.secondary),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 10.dp)
            .clickable { onNavigateToConversation(conversation.name) }
    ) {
        // Add padding around the message
        Row(modifier = Modifier.padding(all = 6.dp)) {

            Image(
                painter = painterResource(R.drawable.skull),
                contentDescription = "skull emoji",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(45.dp)
                    // Clip image to circle shape
                    .clip(CircleShape)
                    // Add a colored border around image
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column() {

                Text(
                    text = conversation.name,
                    // Change text color
                    color = MaterialTheme.colorScheme.secondary,
                    // Change the typography style
                    style = MaterialTheme.typography.titleSmall
                )

                // Add a vertical space between the author and the message
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = String.format(
                        "%s: %s",
                        conversation.messages.last().author,
                        conversation.messages.last().body
                    ),
                    // Add padding around text
                    modifier = Modifier.padding(vertical = 2.dp),
                    // Set text font size
                    fontSize = 14.sp,
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = 1,
                    // Change the typography style
                    style = MaterialTheme.typography.bodyMedium,
                    // Indicate overflow of text with an ellipsis
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }
}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    name = "Light Mode"
//)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewHomeScreen() {
//    HomeworkAppTheme {
//        HomeScreen(
//            conversations = SampleData.conversationSamples,
//            onNavigateToConversation = { conversationName ->
//                println("Navigating to conversation: $conversationName")
//            },
//            onNavigateToSettings = { println("Navigating to settings")
//            }
//        )
//    }
//}
//
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    name = "Light Mode"
//)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "Dark Mode"
//)
//@Composable
//fun PreviewConversationPreview() {
//    HomeworkAppTheme {
//        Surface {
//            ConversationPreview(
//                conversation = Conversation("Sample Conversation", SampleData.conversationSample),
//                onNavigateToConversation = { conversationName ->
//                    println("Navigating to conversation: $conversationName")
//                }
//            )
//        }
//    }
//}