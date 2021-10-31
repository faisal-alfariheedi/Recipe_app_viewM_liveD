package com.example.recipe_app_viewm_lived.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class recipe {


    var data: List<dat>? = null

    @Entity(tableName="recipe")
    class dat {

        @PrimaryKey
        @ColumnInfo(name="id")
        @SerializedName("pk")
        var id: Int? = null

        @ColumnInfo(name="title")
        @SerializedName("title")
        var title: String? = null

        @ColumnInfo(name="author")
        @SerializedName("author")
        var author: String? = null

        @ColumnInfo(name="ingredients")
        @SerializedName("ingredients")
        var ingredients: String? = null

        @ColumnInfo(name="instructions")
        @SerializedName("instructions")
        var instructions: String? = null

        constructor(title: String?, author: String?, ingredients: String?, instructions: String?) {
            this.title = title
            this.author = author
            this.ingredients = ingredients
            this.instructions = instructions
        }
    }

}