package com.example.mvvmtask.Model

import com.google.gson.annotations.SerializedName

data class CoinListResModel(
    @SerializedName("result") var result: Int,
    @SerializedName("msg") var msg: String,
    @SerializedName("data") var data: Data
)

data class Data(

    @SerializedName("totalItems") var totalItems: Int,
    @SerializedName("startIndex") var startIndex: Int,
    @SerializedName("itemsPerPage") var itemsPerPage: Int,
    @SerializedName("list") var list: ArrayList<List1>

)


data class List1(

    @SerializedName("_id") var Id: String,
    @SerializedName("price") var price: Int,
    @SerializedName("pictures" ) var pictures : Pictures,
    @SerializedName("age") var age: Int,
    @SerializedName("isGraded") var isGraded: Boolean,
    @SerializedName("isSold") var isSold: Boolean,
    @SerializedName("isCoin") var isCoin: Boolean,
    @SerializedName("tags") var tags: ArrayList<String>,
    @SerializedName("name") var name: String,
    @SerializedName("history") var history: String,
    @SerializedName("year") var year: Int

)

data class Pictures (

    @SerializedName("front" ) var front : Front,
    @SerializedName("back"  ) var back  : Back

)

data class Front (

    @SerializedName("key"            ) var key            : String,
    @SerializedName("url"            ) var url            : String,
    @SerializedName("sizeInMegaByte" ) var sizeInMegaByte : Double

)

data class Back (

    @SerializedName("key"            ) var key            : String,
    @SerializedName("url"            ) var url            : String,
    @SerializedName("sizeInMegaByte" ) var sizeInMegaByte : Double

)