package com.example.goodday.user

class User {

    lateinit var fullName: String
    lateinit var email:String
    lateinit var uid :String
    lateinit var healthScore :Number

    constructor(){

    }

    constructor(uid:String, fullName: String, email: String) {
        this.uid = uid
        this.fullName = fullName
        this.email = email
    }

}
