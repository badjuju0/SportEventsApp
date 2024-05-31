package com.example.sporteventsapp.data

data class Post (
    val email: String,
    val password: String
)

data class PostReg(
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String
)
data class PostNames(
    val firstName: String,
    val secondName: String,
    val token: String
)

data class PostEvents(
    val sportType: String,
    val title: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String,
    val owner: String
)

data class Answer(
    val answer: String
)
data class TokenPost(
    val token: String
)


data class EventTitle(
    val title: String
)

data class EventTitles(
    val titles: List<EventTitle>
)

data class AboutEvent(
    val id: String,
    val sportType: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String,
    val owner: String
)

data class PostApplication(
    val fio: String,
    val age: String,
    val phoneNumber: String,
    val teamName: String? = null,
    val approve: String? = null,
    val eventTitle: String
)

data class EventId(
    val id: String
)

data class ApplicationDTO(
    val id: String,
    val fio: String,
    val age: String,
    val phoneNumber: String,
    val teamName: String? = null,
    val approve: String? = null,
    val eventTitle: String
)

data class ApplicationsList(
    val applications: List<ApplicationDTO>
)