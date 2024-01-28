import com.jnyman.homeworkapp.database.Conversation
import com.jnyman.homeworkapp.Message
import com.jnyman.homeworkapp.database.Profile
import com.jnyman.homeworkapp.database.ProfileDao

/**
 * SampleData for Jetpack Compose Tutorial 
 */
object SampleData {
    // Sample conversation data

    private val conversationSample = listOf(
        Message(
            "Colleague", false,
            "Test...Test...Test..."
        ),
        Message(
            "Colleague", false,
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "",
            true,
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Colleague", false,
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Colleague", false,
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Colleague", false,
            "It's available from API 21+ :)"
        ),
        Message(
            "Colleague", false,
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Colleague", false,
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Colleague", false,
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Colleague", false,
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Colleague", false,
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Colleague", false,
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Colleague", false,
            "Have you tried writing build.gradle with KTS?"
        ),
    )

    private val conversationSample2 = listOf(
        Message(
            "Colleague_2", false,
            "Anyone else excited about the recent breakthroughs in quantum computing?"
        ),
        Message(
            "Colleague_2", false,
            """Did you know there are different types of quantum computers:
        |1. Quantum Annealers
        |2. Gate-based Quantum Computers""".trim()
        ),
        Message(
            "Colleague_2", false,
            """I've been exploring functional programming languages lately.
        |Haskell and Lisp are quite fascinating!""".trim()
        ),
        Message(
            "Colleague_2", false,
            "Looking for the best algorithms to solve the traveling salesman problem efficiently."
        ),
        Message(
            "Colleague_2", false,
            """Functional programming languages promote immutability and pure functions.
        |Have you ever tried writing a purely functional application?""".trim()
        ),
        Message(
            "Colleague_2", false,
            "The advancements in AI and machine learning are reshaping industries rapidly."
        ),
        Message(
            "Colleague_2", false,
            "Have you heard about the latest breakthroughs in natural language processing?"
        ),
        Message(
            "Colleague_2", false,
            "I'm currently exploring decentralized finance (DeFi) platforms and blockchain technology."
        ),
        Message(
            "Colleague_2", false,
            "Smart contracts have the potential to revolutionize the way we conduct business transactions."
        ),
        Message(
            "Colleague_2", false,
            "The intersection of technology and sustainability is a key focus for innovation."
        ),
        Message(
            "Colleague_2", false,
            "Have you ever tried developing software using a serverless architecture?"
        ),
        Message(
            "Colleague_2", false,
            "Quantum machine learning is an emerging field with promising potential."
        ),
        Message(
            "Colleague_2", false,
            "Functional reactive programming brings a new paradigm to building responsive and scalable systems."
        )
    )

    private val conversationSample3 = listOf(
        Message(
            "Colleague_2", false,
            "Anyone else excited about the recent breakthroughs in quantum computing?"
        ),
        Message(
            "Colleague_2", false,
            """Did you know there are different types of quantum computers:
        |1. Quantum Annealers
        |2. Gate-based Quantum Computers""".trim()
        ),
        Message(
            "Colleague_2", false,
            """I've been exploring functional programming languages lately.
        |Haskell and Lisp are quite fascinating!""".trim()
        ),
        Message(
            "Colleague_2", false,
            "Looking for the best algorithms to solve the traveling salesman problem efficiently."
        ),
        Message(
            "Colleague_2", false,
            """Functional programming languages promote immutability and pure functions.
        |Have you ever tried writing a purely functional application?""".trim()
        ),
        Message(
            "Colleague_2", false,
            "The advancements in AI and machine learning are reshaping industries rapidly."
        ),
        Message(
            "Colleague_2", false,
            "Have you heard about the latest breakthroughs in natural language processing?"
        ),
        Message(
            "Colleague_2", false,
            "I'm currently exploring decentralized finance (DeFi) platforms and blockchain technology."
        ),
        Message(
            "Colleague_2", false,
            "Smart contracts have the potential to revolutionize the way we conduct business transactions."
        ),
        Message(
            "Colleague_2", false,
            "The intersection of technology and sustainability is a key focus for innovation."
        ),
        Message(
            "Colleague_2", false,
            "Have you ever tried developing software using a serverless architecture?"
        ),
        Message(
            "Colleague_2", false,
            "Quantum machine learning is an emerging field with promising potential."
        ),
        Message(
            "Colleague_2", false,
            "Functional reactive programming brings a new paradigm to building responsive and scalable systems."
        )
    )

    val conversationSamples = listOf(
        Conversation(name = "Sample Conversation 1", messages = conversationSample, conversationPictureUri = ""),
        Conversation(name = "Sample Conversation 2", messages = conversationSample2, conversationPictureUri = ""),
        Conversation(name = "Sample Conversation 3", messages = conversationSample3, conversationPictureUri = "")
    )

}
