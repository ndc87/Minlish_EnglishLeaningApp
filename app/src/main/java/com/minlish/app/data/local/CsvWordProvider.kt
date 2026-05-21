package com.minlish.app.data.local

import android.content.Context
import com.minlish.app.data.local.entity.CardEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CsvWordProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun loadWordsFromCsv(fileName: String): List<CardEntity> {
        val words = mutableListOf<CardEntity>()
        try {
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            // Skip header: english,type,vietnamese,pronounce,explain,example,example_vietnamese,image_url,audio_url,topic,topic_url
            val header = reader.readLine()
            
            var line: String? = reader.readLine()
            while (line != null) {
                if (line.isNotBlank()) {
                    val tokens = parseCsvLine(line)
                    if (tokens.size >= 11) {
                        words.add(
                            CardEntity(
                                word = tokens[0],          // english
                                pos = tokens[1],           // type
                                meaning = tokens[2],       // vietnamese
                                pronunciation = tokens[3], // pronounce
                                descriptionEn = tokens[4], // explain
                                example = tokens[5],       // example
                                note = tokens[6],          // example_vietnamese
                                imageUrl = tokens[7],      // image_url
                                audioUrl = tokens[8],      // audio_url
                                topic = tokens[9],         // topic
                                topicUrl = tokens[10],     // topic_url
                                level = 1
                            )
                        )
                    }
                }
                line = reader.readLine()
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return words
    }

    /**
     * Simple CSV parser that handles quoted fields
     */
    private fun parseCsvLine(line: String): List<String> {
        val result = mutableListOf<String>()
        var curVal = StringBuilder()
        var inQuotes = false
        
        var i = 0
        while (i < line.length) {
            val ch = line[i]
            if (inQuotes) {
                if (ch == '\"') {
                    if (i + 1 < line.length && line[i + 1] == '\"') {
                        curVal.append('\"')
                        i++
                    } else {
                        inQuotes = false
                    }
                } else {
                    curVal.append(ch)
                }
            } else {
                if (ch == '\"') {
                    inQuotes = true
                } else if (ch == ',') {
                    result.add(curVal.toString().trim())
                    curVal = StringBuilder()
                } else {
                    curVal.append(ch)
                }
            }
            i++
        }
        result.add(curVal.toString().trim())
        return result
    }
}
