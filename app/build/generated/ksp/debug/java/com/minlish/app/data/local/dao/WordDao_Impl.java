package com.minlish.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.minlish.app.data.local.entity.SrsLogEntity;
import com.minlish.app.data.local.entity.WordEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WordDao_Impl implements WordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WordEntity> __insertionAdapterOfWordEntity;

  private final EntityInsertionAdapter<SrsLogEntity> __insertionAdapterOfSrsLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAllWords;

  public WordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWordEntity = new EntityInsertionAdapter<WordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `words` (`id`,`topicId`,`word`,`pronunciation`,`meaning`,`descriptionEn`,`example`,`collocation`,`relatedWords`,`note`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WordEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTopicId());
        statement.bindString(3, entity.getWord());
        statement.bindString(4, entity.getPronunciation());
        statement.bindString(5, entity.getMeaning());
        statement.bindString(6, entity.getDescriptionEn());
        statement.bindString(7, entity.getExample());
        statement.bindString(8, entity.getCollocation());
        statement.bindString(9, entity.getRelatedWords());
        statement.bindString(10, entity.getNote());
      }
    };
    this.__insertionAdapterOfSrsLogEntity = new EntityInsertionAdapter<SrsLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `srs_logs` (`wordId`,`interval`,`easeFactor`,`repetitionCount`,`nextReviewDate`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SrsLogEntity entity) {
        statement.bindString(1, entity.getWordId());
        statement.bindLong(2, entity.getInterval());
        statement.bindDouble(3, entity.getEaseFactor());
        statement.bindLong(4, entity.getRepetitionCount());
        statement.bindLong(5, entity.getNextReviewDate());
      }
    };
    this.__preparedStmtOfClearAllWords = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM words";
        return _query;
      }
    };
  }

  @Override
  public Object insertWords(final List<WordEntity> words,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWordEntity.insert(words);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSrsLog(final SrsLogEntity srsLog,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSrsLogEntity.insert(srsLog);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllWords(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllWords.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearAllWords.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WordEntity>> getWordsByTopic(final String topicId) {
    final String _sql = "SELECT * FROM words WHERE topicId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, topicId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words"}, new Callable<List<WordEntity>>() {
      @Override
      @NonNull
      public List<WordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPronunciation = CursorUtil.getColumnIndexOrThrow(_cursor, "pronunciation");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCollocation = CursorUtil.getColumnIndexOrThrow(_cursor, "collocation");
          final int _cursorIndexOfRelatedWords = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedWords");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<WordEntity> _result = new ArrayList<WordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WordEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final String _tmpWord;
            _tmpWord = _cursor.getString(_cursorIndexOfWord);
            final String _tmpPronunciation;
            _tmpPronunciation = _cursor.getString(_cursorIndexOfPronunciation);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpCollocation;
            _tmpCollocation = _cursor.getString(_cursorIndexOfCollocation);
            final String _tmpRelatedWords;
            _tmpRelatedWords = _cursor.getString(_cursorIndexOfRelatedWords);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            _item = new WordEntity(_tmpId,_tmpTopicId,_tmpWord,_tmpPronunciation,_tmpMeaning,_tmpDescriptionEn,_tmpExample,_tmpCollocation,_tmpRelatedWords,_tmpNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWordById(final String wordId,
      final Continuation<? super WordEntity> $completion) {
    final String _sql = "SELECT * FROM words WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, wordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WordEntity>() {
      @Override
      @Nullable
      public WordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPronunciation = CursorUtil.getColumnIndexOrThrow(_cursor, "pronunciation");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCollocation = CursorUtil.getColumnIndexOrThrow(_cursor, "collocation");
          final int _cursorIndexOfRelatedWords = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedWords");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final WordEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final String _tmpWord;
            _tmpWord = _cursor.getString(_cursorIndexOfWord);
            final String _tmpPronunciation;
            _tmpPronunciation = _cursor.getString(_cursorIndexOfPronunciation);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpCollocation;
            _tmpCollocation = _cursor.getString(_cursorIndexOfCollocation);
            final String _tmpRelatedWords;
            _tmpRelatedWords = _cursor.getString(_cursorIndexOfRelatedWords);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            _result = new WordEntity(_tmpId,_tmpTopicId,_tmpWord,_tmpPronunciation,_tmpMeaning,_tmpDescriptionEn,_tmpExample,_tmpCollocation,_tmpRelatedWords,_tmpNote);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSrsLog(final String wordId,
      final Continuation<? super SrsLogEntity> $completion) {
    final String _sql = "SELECT * FROM srs_logs WHERE wordId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, wordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SrsLogEntity>() {
      @Override
      @Nullable
      public SrsLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfWordId = CursorUtil.getColumnIndexOrThrow(_cursor, "wordId");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfRepetitionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitionCount");
          final int _cursorIndexOfNextReviewDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewDate");
          final SrsLogEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpWordId;
            _tmpWordId = _cursor.getString(_cursorIndexOfWordId);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final int _tmpRepetitionCount;
            _tmpRepetitionCount = _cursor.getInt(_cursorIndexOfRepetitionCount);
            final long _tmpNextReviewDate;
            _tmpNextReviewDate = _cursor.getLong(_cursorIndexOfNextReviewDate);
            _result = new SrsLogEntity(_tmpWordId,_tmpInterval,_tmpEaseFactor,_tmpRepetitionCount,_tmpNextReviewDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WordEntity>> getDueWords(final long currentTimestamp) {
    final String _sql = "SELECT words.* FROM words INNER JOIN srs_logs ON words.id = srs_logs.wordId WHERE srs_logs.nextReviewDate <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTimestamp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words",
        "srs_logs"}, new Callable<List<WordEntity>>() {
      @Override
      @NonNull
      public List<WordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPronunciation = CursorUtil.getColumnIndexOrThrow(_cursor, "pronunciation");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCollocation = CursorUtil.getColumnIndexOrThrow(_cursor, "collocation");
          final int _cursorIndexOfRelatedWords = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedWords");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<WordEntity> _result = new ArrayList<WordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WordEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final String _tmpWord;
            _tmpWord = _cursor.getString(_cursorIndexOfWord);
            final String _tmpPronunciation;
            _tmpPronunciation = _cursor.getString(_cursorIndexOfPronunciation);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpCollocation;
            _tmpCollocation = _cursor.getString(_cursorIndexOfCollocation);
            final String _tmpRelatedWords;
            _tmpRelatedWords = _cursor.getString(_cursorIndexOfRelatedWords);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            _item = new WordEntity(_tmpId,_tmpTopicId,_tmpWord,_tmpPronunciation,_tmpMeaning,_tmpDescriptionEn,_tmpExample,_tmpCollocation,_tmpRelatedWords,_tmpNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<WordEntity>> getNewWords() {
    final String _sql = "SELECT words.* FROM words WHERE id NOT IN (SELECT wordId FROM srs_logs)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"words",
        "srs_logs"}, new Callable<List<WordEntity>>() {
      @Override
      @NonNull
      public List<WordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPronunciation = CursorUtil.getColumnIndexOrThrow(_cursor, "pronunciation");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfCollocation = CursorUtil.getColumnIndexOrThrow(_cursor, "collocation");
          final int _cursorIndexOfRelatedWords = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedWords");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final List<WordEntity> _result = new ArrayList<WordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WordEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final String _tmpWord;
            _tmpWord = _cursor.getString(_cursorIndexOfWord);
            final String _tmpPronunciation;
            _tmpPronunciation = _cursor.getString(_cursorIndexOfPronunciation);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpCollocation;
            _tmpCollocation = _cursor.getString(_cursorIndexOfCollocation);
            final String _tmpRelatedWords;
            _tmpRelatedWords = _cursor.getString(_cursorIndexOfRelatedWords);
            final String _tmpNote;
            _tmpNote = _cursor.getString(_cursorIndexOfNote);
            _item = new WordEntity(_tmpId,_tmpTopicId,_tmpWord,_tmpPronunciation,_tmpMeaning,_tmpDescriptionEn,_tmpExample,_tmpCollocation,_tmpRelatedWords,_tmpNote);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
