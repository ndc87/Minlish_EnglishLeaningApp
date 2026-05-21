package com.minlish.app.data.util

import android.content.Context
import android.net.Uri
import com.minlish.app.data.local.entity.WordEntity
import java.io.BufferedReader
import java.io.InputStreamReader

object FileImportExportUtil {

    /**
     * Parses a CSV file and converts it into a list of WordEntity.
     * Expected CSV format: id,topicId,word,pronunciation,meaning,descriptionEn,example,collocation,relatedWords,note
     */
    fun importWordsFromCsv(context: Context, uri: Uri): List<WordEntity> {
        val words = mutableListOf<WordEntity>()
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.useLines { lines ->
                lines.drop(1).forEach { line -> // Skip header
                    val tokens = line.split(",")
                    if(tokens.size >= 10) {
                        words.add(WordEntity(
                            id = tokens[0].trim(),
                            topicId = tokens[1].trim(),
                            word = tokens[2].trim(),
                            pronunciation = tokens[3].trim(),
                            meaning = tokens[4].trim(),
                            descriptionEn = tokens[5].trim(),
                            example = tokens[6].trim(),
                            collocation = tokens[7].trim(),
                            relatedWords = tokens[8].trim(),
                            note = tokens[9].trim()
                        ))
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return words
    }
}
