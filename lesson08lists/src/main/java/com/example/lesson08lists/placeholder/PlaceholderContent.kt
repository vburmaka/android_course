package com.example.lesson08lists.placeholder

import android.media.Image
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 0..COUNT) {
            if (i == 0) {
                addItem(createCheckBoxItem(i))
            } else if (i == 1){
                addItem(createRemoveItem(i))
            } else if (i == 2){
                addItem(createAddItem(i))
            } else {
                addItem(createPlaceholderItem(i))
            }
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return SimpleItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun createCheckBoxItem(position: Int): PlaceholderItem {
        return CheckBoxItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun createRemoveItem(position: Int): PlaceholderItem {
        return RemoveItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun createAddItem(position: Int): PlaceholderItem {
        return AddItem(position.toString(), "Item " + position, makeDetails(position))
    }
    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    open class PlaceholderItem(open val id: String, open val content: String, open val details: String) {
        override fun toString(): String = content
    }

     class SimpleItem(override val id: String, override val content: String, override val details: String): PlaceholderItem(id, content, details)
     class CheckBoxItem(override val id: String, override val content: String, override val details: String): PlaceholderItem(id, content, details)
     class RemoveItem(override val id: String, override val content: String, override val details: String): PlaceholderItem(id, content, details)
     class AddItem(override val id: String, override val content: String, override val details: String): PlaceholderItem(id, content, details)



}