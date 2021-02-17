package com.garmin.garminkaptain.data

import java.util.*

data class PointOfInterest(
    val id: Long,
    val mapLocation: MapLocation,
    val name: String,
    val poiType: String,
    val reviewsSummary: ReviewsSummary
)

data class MapLocation(
    val latitude: Double,
    val longitude: Double
)

class ReviewsSummary(val reviews: MutableList<Review>) {
    var averageRating: Double
    var numberOfReviews: Int
    private var reviewsSum: Double

    init {
        reviews.let {
            reviewsSum = if (it.size == 0) 0.0 else reviews.sumByDouble { review -> review.rating }
            this.numberOfReviews = it.size
            this.averageRating = if (it.size == 0) 0.0 else reviewsSum / numberOfReviews.toDouble()
        }
    }

    fun addReview(review: Review) {
        reviews.add(review)
        numberOfReviews++
        reviewsSum += review.rating
        averageRating = reviewsSum / numberOfReviews.toDouble()
    }
}

data class Review(
    val id: Long,
    val title: String,
    val rating: Double,
    val content: String,
    val dateCreated: Date,
    val user: User
)

data class User(
    val userName: String,
    val photo: String
)

val reviewsPointBonita: MutableList<Review> = mutableListOf(
    Review(
        1,
        "Very nice",
        4.9,
        "I went there and it was VERY nice",
        Calendar.getInstance().time,
        User("Jake", "https://picsum.photos/100/100?random=1")
    ),
    Review(
        2,
        "Too much clean air",
        3.0,
        "For some reason the pollution was not there. How are my lungs supposed to work?",
        Date(1273438800000),
        User("Millennial", "https://picsum.photos/100/100?random=2")
    )
)

val reviewsGoldenGateBridge: MutableList<Review> = mutableListOf(
    Review(
        3,
        "It's red ",
        4.5,
        "Nice but too much red for some reason.",
        Date(1516658400000),
        User("Karen", "https://picsum.photos/100/100?random=3")
    ),
    Review(
        4,
        "What is up with the noise?",
        2.5,
        "There are so many cars here, why? Can't people just use their private jet to get places?",
        Date(1609452000000),
        User("Richard Rich", "https://picsum.photos/100/100?random=4")
    )
)


val poiList: List<PointOfInterest> = listOf(
    PointOfInterest(
        46067,
        MapLocation(37.8180564724432, -122.52704143524173),
        "Point Bonita",
        "Anchorage",
        ReviewsSummary(reviewsPointBonita)
    ),
    PointOfInterest(
        12975,
        MapLocation(37.8770892291283, -122.503309249878),
        "Richardson Bay Marina",
        "Marina",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        46085,
        MapLocation(37.82878469060811, -122.47633210712522),
        "Needles",
        "Anchorage",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        19637,
        MapLocation(37.82077, -122.4786),
        "Golden Gate Bridge",
        "Bridge",
        ReviewsSummary(reviewsGoldenGateBridge)
    ),
    PointOfInterest(
        60928,
        MapLocation(37.8325155338083, -122.47500389814363),
        "Horseshoe Cove",
        "Anchorage",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        39252,
        MapLocation(37.833886767314, -122.475371360779),
        "Presidio Yacht Club",
        "Marina",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        25644,
        MapLocation(37.8673327691044, -122.435932159424),
        "Ayala Cove",
        "Anchorage",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        61865,
        MapLocation(37.850002964208095, -122.41632213957898),
        "Tide Rips",
        "Hazard",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        46713,
        MapLocation(37.827799573006274, -122.42648773017541),
        "Dangerous Rock",
        "Hazard",
        ReviewsSummary(mutableListOf())
    ),
    PointOfInterest(
        57109,
        MapLocation(37.87572310328571, -122.50570595169079),
        "Woodrum Marine Boat Repair/Carpentry",
        "Business",
        ReviewsSummary(mutableListOf())
    )
)