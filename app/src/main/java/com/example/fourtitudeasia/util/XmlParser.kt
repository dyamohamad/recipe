package com.example.fourtitudeasia.util

import android.content.Context
import org.xmlpull.v1.XmlPullParser


object XmlParser {

     fun getRecipeType(context: Context):ArrayList<String> {
        val items = ArrayList<String>()
        val xpp = context.getResources().getXml(com.example.fourtitudeasia.R.xml.recipetype)

        while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                if (xpp.getName() == "type") {
                    items.add(xpp.getAttributeValue(0))
                }
            }

            xpp.next()
        }
        return items
    }
}