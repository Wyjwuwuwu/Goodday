package com.example.goodday.user

class profile {

    lateinit var time: String
    lateinit var content :String

    constructor(){

    }

    constructor(time:String, content: String) {
        this.time = time
        this.content = content
    }
}