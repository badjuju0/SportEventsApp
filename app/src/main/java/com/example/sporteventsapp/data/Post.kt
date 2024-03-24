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
    val secondName: String
)

data class PostEvents(
    val sportType: String,
    val title: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String
)

data class Answer(
    val answer: String
)

data class EventTitles(
    val title: List<EventTitles>
)