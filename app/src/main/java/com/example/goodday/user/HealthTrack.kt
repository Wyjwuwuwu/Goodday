package com.example.goodday.user

class HealthTrack {

    lateinit var uid :String
    var healthScore : Float? = null
    var fGlucose : Float? = null
    var tGlucose : Float? = null
    var hemoglobin  : Float? = null
    var urine  : Float? = null

    constructor(){

    }

    constructor(
        uid:String,
        healthScore: Float,
        fGlucose: Float,
        tGlucose: Float,
        hemoglobin: Float,
        urine: Float,
    ) {

        this.uid = uid
        this.healthScore = healthScore
        this.fGlucose = fGlucose
        this.tGlucose = tGlucose
        this.hemoglobin = hemoglobin
        this.urine = urine
    }
}