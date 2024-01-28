package com.jnyman.homeworkapp

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jnyman.homeworkapp.database.Profile
import com.jnyman.homeworkapp.database.ProfileDao
import com.jnyman.homeworkapp.ui.theme.HomeworkAppTheme


@Composable
fun MessageCard(msg: Message, dao: ProfileDao) {

    var profile: Profile

    profile = dao.getProfileByName(msg.author)

    if (msg.ownMessage) {
        profile = dao.getOwnProfile()
        if (profile == null) {
            profile = Profile(
                nickName = "New Profile",
                profilePictureUri = "",
                own = true
            )
        }
    } else if (profile == null) {
        profile = Profile(
            nickName = msg.author,
            profilePictureUri = "",
            own = false
        )
    }

    dao.upsertProfile(profile = profile)

    // Add padding around the message
    Row(modifier = Modifier.padding(all = 8.dp)) {

        AsyncImage(
            model = Uri.parse(profile.profilePictureUri),
            contentDescription = "profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to circle shape
                .clip(CircleShape)
                // Add a colored border around image
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember {
            mutableStateOf(false)
        }

        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = ""
        )

        // We toggle the isExpanded variable when we click on this Column

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

            Text(
                text = profile.nickName,
                // Change text color
                color = MaterialTheme.colorScheme.secondary,
                // Change the typography style
                style = MaterialTheme.typography.titleSmall
            )

            // Add a vertical space between the author and the message
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.small,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    // Add padding around text
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    // Change the typography style
                    style = MaterialTheme.typography.bodyMedium
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
//fun PreviewMessageCard() {
//    HomeworkAppTheme {
//        Surface {
//            MessageCard(
//                msg = Message("Mr. Skull Emoji", "Take a look at Jetpack Compose, it's great!")
//            )
//        }
//    }
//}