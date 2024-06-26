package com.example.sporteventsapp.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String,
                     var icon: ImageVector,
                     var title: String
    ) {
    object Registration: Screens("RegistrationScreen",
        icon = Icons.Default.Person,
        title = "Registration"
        )
    object Event: Screens("EventScreen",
        icon = Icons.Default.Home,
        title = "Мероприятия"
        )
    object Login: Screens("LoginScreen",
        icon = Icons.Default.Person,
        title = "Login"
        )
    object Profile: Screens("ProfileScreen",
        icon = Icons.Default.Person,
        title = "Профиль")

    object EventCreate: Screens("EventCreateScreen",
        icon = Icons.Default.Person,
        title = "EventCreate")

    object AboutEvent:Screens("AboutEventScreen",
        icon = Icons.Default.Person,
        title = "AboutEvent")

    object EventApplication:Screens("EventApplicationScreen",
        icon = Icons.Default.Person,
        title = "EventApplication"
        )

    object Applications:Screens("ApplicationsScreen",
        icon = Icons.Default.Person,
        title = "Applications"
    )

    object Participants:Screens("ParticipantsScreen",
        icon = Icons.Default.Person,
        title = "Participants"
    )
}

