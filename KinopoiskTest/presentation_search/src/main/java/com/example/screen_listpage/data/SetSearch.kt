package com.example.screen_listpage.data

object SetSearch {
   var counterID: Int = 1
   var order: Order = Order.YEAR
   var type: Type = Type.ALL
   var ratingFrom:Int =1
   var ratingTo:Int =10
   var yearFrom:Int =1000
   var yearTo:Int =3000
   var page:Int = 1
   var keyword:String = "матрица"
}

enum class Order {
    RATING, NUM_VOTE, YEAR
}

enum class Type {
    TV_SERIES, FILM, ALL
}
