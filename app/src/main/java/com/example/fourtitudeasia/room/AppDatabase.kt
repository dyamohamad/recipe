package com.example.android.roomwordssample

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fourtitudeasia.dao.RecipeDao
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.util.DateUtil.dateId
import com.example.fourtitudeasia.util.TypeConverter
import java.util.concurrent.Executors


/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.  In a real
 * app, consider exporting the schema to help you with migrations.
 */

@Database(entities = arrayOf(RecipeTypeModel::class), version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
internal abstract class AppDatabase : RoomDatabase() {

    internal abstract fun dao(): RecipeDao

    companion object {

        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "my-database"
                        )
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Executors.newSingleThreadScheduledExecutor().execute {
                                        INSTANCE!!.populateData(context)

                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }


    }

    fun populateData(context: Context){

        var arrayAll=ArrayList<RecipeTypeModel>()
        arrayAll= ArrayList()

        var step:ArrayList<String>
        step= ArrayList()
        step.add("Mix 1/2 cup soft blue cheese with 2 tablespoons chopped toasted pecans")
        step.add("Spread on celery sticks or crackers and drizzle with honey.")

        var ingredients:ArrayList<String>
        ingredients= ArrayList()
        ingredients.add("blue cheese")
        ingredients.add("toasted pecans")
        ingredients.add("celery sticks")
        ingredients.add("honey")
        arrayAll.add(RecipeTypeModel(dateId()+"Blue Cheese-Pecan Spread","Appetizer","Blue Cheese-Pecan Spread","https://bit.ly/343ln9j",ingredients,step))


        var step1:ArrayList<String>
        step1= ArrayList()
        step1.add("Spoon dollops of crème fraîche onto cucumber rounds")
        step1.add("Top each with a small spoonful of salmon or trout caviar.")

        var ingredients1:ArrayList<String>
        ingredients1= ArrayList()
        ingredients1.add("crème fraîche")
        ingredients1.add("cucumber")
        ingredients1.add("salmon ")
        arrayAll.add(RecipeTypeModel(dateId()+"Cucumber-Caviar Rounds","Appetizer","Cucumber-Caviar Rounds","https://bit.ly/2O3WRiW",ingredients1,step1))

        var ingredients2:ArrayList<String>
        var step2:ArrayList<String>
        step2= ArrayList()
        step2.add("Place brisket on a rack in a shallow greased roasting pan.")
        step2.add("In a small bowl, combine the remaining ingredients; pour over brisket")
        step2.add("Cover and bake at 325° for 3 hours or until meat is tender.")
        step2.add("To serve, thinly slice across the grain.")

        ingredients2= ArrayList()
        ingredients2.add("1 fresh beef brisket")
        ingredients2.add("tomato sauce")
        ingredients2.add("diced tomatoes and green chilies")
        ingredients2.add("1 envelope onion soup mix")

        arrayAll.add(RecipeTypeModel(dateId()+"Spicy Beef Brisket","Main Dishes","Spicy Beef Brisket","https://bit.ly/2KzDZ9d",ingredients2,step2))


        var step3:ArrayList<String>
        step3= ArrayList()
        step3.add("Preheat oven to 400°. Place potatoes in a greased 15x10x1-in. baking pan; drizzle with olive oil. Sprinkle with 1/4 teaspoon sea salt; stir to combine. Bake 30 minutes. Meanwhile, squeeze 1/3 cup juice from limes, reserving fruit. Combine lime juice, melted butter, chipotle and remaining sea salt.")
        step3.add("Remove sheet pan from oven; stir potatoes. Arrange asparagus, Broccolini, shrimp and reserved limes on top of potatoes. Pour lime juice mixture over vegetables and shrimp.")
        step3.add("Bake until shrimp turn pink and vegetables are tender, about 10 minutes. Sprinkle with cilantro.")


        var ingredients3:ArrayList<String>
        ingredients3= ArrayList()
        ingredients3.add("1-1/2 pounds baby red potatoes, cut into 3/4-inch cubes")
        ingredients3.add("1 tablespoon extra virgin olive oil")
        ingredients3.add("3/4 teaspoon sea salt, divided ")
        ingredients3.add("3 medium limes")
        ingredients3.add("1/4 cup unsalted butter, melted")
        ingredients3.add("1/2 pound fresh asparagus, trimmed")
        ingredients3.add("1 teaspoon ground chipotle pepper")
        ingredients3.add("1/2 pound Broccolini or broccoli, cut into small florets")
        ingredients3.add("1 pound uncooked shrimp (16-20 per pound), peeled and deveined")


        arrayAll.add(RecipeTypeModel(dateId()+"Sheet-Pan Chipotle-Lime Shrimp Bake","Main Dishes","Sheet-Pan Chipotle-Lime Shrimp Bake","https://bit.ly/343lDoN",ingredients3,step1))



//desserrt
        var step4:ArrayList<String>
        step4= ArrayList()
        step4.add("Sandwich 4 scoops of ice cream between 8 chocolate coated cookies and freeze on a parchment-lined baking sheet until firm. ")
        step4.add("elt bittersweet chocolate. Dip half of each sandwich into the chocolate and freeze until set, about 5 minutes.")


        var ingredients4:ArrayList<String>
        ingredients4= ArrayList()
        ingredients4.add("4 scoops ice cream")
        ingredients4.add("8 chocolate coated cookies")
        ingredients4.add("3 oz. bittersweet chocolate (finely chopped)")

        arrayAll.add(RecipeTypeModel(dateId()+"Chocolate-Cherry Ice Cream Sandwiches","Dessert","Chocolate-Cherry Ice Cream Sandwiches","https://bit.ly/2QAbX1a",ingredients4,step4))

        var step5:ArrayList<String>
        step5= ArrayList()
        step5.add("In a bowl, combine yogurt and almond extract. In a blender, puree raspberries until smooth; transfer to a bowl. Clean the blender and puree peaches until smooth. ")
        step5.add("Layer raspberries, peaches, and yogurt in six 2.5-oz ice pop molds; repeat until each mold is full. Insert craft sticks and freeze until set, at least 4 hours or up to 1 week. ")


        var ingredients5:ArrayList<String>
        ingredients5= ArrayList()
        ingredients5.add("1 c. plus 2 tbsp. full-fat vanilla yogurt")
        ingredients5.add("1/8 tsp. pure almond extract")
        ingredients5.add("1 c. raspberries, thawed if frozen ")
        ingredients5.add("1 1/2 c. peeled peach chunks (from about 2 ripe peaches or thawed if frozen)")

        arrayAll.add(RecipeTypeModel(dateId()+"Peach Melba Ice Pops","Dessert","Peach Melba Ice Pops","https://bit.ly/349KHLe",ingredients5,step5))


//        for(i in 0 until arrayAll.size){
            INSTANCE!!.dao().insertAll(arrayAll)
//        }

    }


}
