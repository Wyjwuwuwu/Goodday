package com.example.goodday.user

class Logbook {

    lateinit var weather: String
    lateinit var airT: String
    lateinit var wirT:String
    lateinit var feeling:String
    lateinit var breakfast:String
    lateinit var lunch:String
    lateinit var dinner:String
    lateinit var tempB:String
    lateinit var presB:String
    lateinit var glucB:String
    lateinit var pulse:String
    lateinit var sleep:String
    lateinit var execise:String
    lateinit var funny:String
    lateinit var walk:String

    constructor(){

    }

    constructor(weather: String,
                airT: String,
                wirT:String,
                feeling:String,
                breakfast:String,
                lunch:String,
                dinner:String,
                tempB:String,
                presB:String,
                glucB:String,
                pulse:String,
                sleep:String,
                execise:String,
                funny:String,
                walk:String
    ) {
        this.weather = weather
        this.airT = airT
        this.wirT = wirT
        this.feeling = feeling
        this.breakfast = breakfast
        this.lunch = lunch
        this.dinner = dinner
        this.tempB = tempB
        this.glucB = glucB
        this.presB = presB
        this.pulse = pulse
        this.sleep = sleep
        this.execise = execise
        this.funny = funny
        this.walk = walk
    }
}