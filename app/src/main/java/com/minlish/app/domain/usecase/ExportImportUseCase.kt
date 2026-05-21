package com.minlish.app.domain.usecase

import android.content.Context
import android.net.Uri
import com.minlish.app.data.local.dao.CardDao
import com.minlish.app.data.local.entity.CardEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import javax.inject.Inject

class ExportImportUseCase @Inject constructor(
    private val cardDao: CardDao,
    @ApplicationContext private val context: Context
) {
    suspend fun exportToCsv(uri: Uri): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val cards = cardDao.getAllCardsList()
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write("word,pos,meaning,example,audioUrl,imageUrl,level\n")
                    cards.forEach { card ->
                        writer.write("${card.word},${card.pos},${card.meaning},${card.example},${card.audioUrl},${card.imageUrl},${card.level}\n")
                    }
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun importFromCsv(uri: Uri): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val cards = mutableListOf<CardEntity>()
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var line = reader.readLine() // Skip header
                    line = reader.readLine()
                    while (line != null) {
                        val parts = line.split(",")
                        if (parts.size >= 7) {
                            cards.add(
                                CardEntity(
                                    word = parts[0],
                                    pos = parts[1],
                                    meaning = parts[2],
                                    example = parts[3],
                                    audioUrl = parts[4].takeIf { it.isNotBlank() },
                                    imageUrl = parts[5].takeIf { it.isNotBlank() },
                                    level = parts[6].toIntOrNull() ?: 1
                                )
                            )
                        }
                        line = reader.readLine()
                    }
                }
            }
            if (cards.isNotEmpty()) {
                cardDao.insertCards(cards)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
