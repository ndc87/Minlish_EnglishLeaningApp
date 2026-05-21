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
import com.minlish.app.data.local.entity.CardEntity;
import com.minlish.app.data.local.entity.ReviewEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class ReviewDao_Impl implements ReviewDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReviewEntity> __insertionAdapterOfReviewEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ReviewDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReviewEntity = new EntityInsertionAdapter<ReviewEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `reviews` (`cardId`,`userId`,`repetitions`,`interval`,`easeFactor`,`lastDate`,`nextDate`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReviewEntity entity) {
        statement.bindLong(1, entity.getCardId());
        statement.bindString(2, entity.getUserId());
        statement.bindLong(3, entity.getRepetitions());
        statement.bindLong(4, entity.getInterval());
        statement.bindDouble(5, entity.getEaseFactor());
        statement.bindLong(6, entity.getLastDate());
        statement.bindLong(7, entity.getNextDate());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM reviews";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrUpdateReview(final ReviewEntity review,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReviewEntity.insert(review);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getReviewByCardId(final long cardId,
      final Continuation<? super ReviewEntity> $completion) {
    final String _sql = "SELECT * FROM reviews WHERE cardId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cardId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ReviewEntity>() {
      @Override
      @Nullable
      public ReviewEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfLastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDate");
          final int _cursorIndexOfNextDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDate");
          final ReviewEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpCardId;
            _tmpCardId = _cursor.getLong(_cursorIndexOfCardId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final long _tmpLastDate;
            _tmpLastDate = _cursor.getLong(_cursorIndexOfLastDate);
            final long _tmpNextDate;
            _tmpNextDate = _cursor.getLong(_cursorIndexOfNextDate);
            _result = new ReviewEntity(_tmpCardId,_tmpUserId,_tmpRepetitions,_tmpInterval,_tmpEaseFactor,_tmpLastDate,_tmpNextDate);
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
  public Flow<List<ReviewEntity>> getDueReviews(final long currentTime) {
    final String _sql = "SELECT * FROM reviews WHERE nextDate <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reviews"}, new Callable<List<ReviewEntity>>() {
      @Override
      @NonNull
      public List<ReviewEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfLastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDate");
          final int _cursorIndexOfNextDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDate");
          final List<ReviewEntity> _result = new ArrayList<ReviewEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReviewEntity _item;
            final long _tmpCardId;
            _tmpCardId = _cursor.getLong(_cursorIndexOfCardId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final long _tmpLastDate;
            _tmpLastDate = _cursor.getLong(_cursorIndexOfLastDate);
            final long _tmpNextDate;
            _tmpNextDate = _cursor.getLong(_cursorIndexOfNextDate);
            _item = new ReviewEntity(_tmpCardId,_tmpUserId,_tmpRepetitions,_tmpInterval,_tmpEaseFactor,_tmpLastDate,_tmpNextDate);
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
  public Flow<List<ReviewEntity>> getAllReviews() {
    final String _sql = "SELECT * FROM reviews";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reviews"}, new Callable<List<ReviewEntity>>() {
      @Override
      @NonNull
      public List<ReviewEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfLastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDate");
          final int _cursorIndexOfNextDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDate");
          final List<ReviewEntity> _result = new ArrayList<ReviewEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReviewEntity _item;
            final long _tmpCardId;
            _tmpCardId = _cursor.getLong(_cursorIndexOfCardId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final long _tmpLastDate;
            _tmpLastDate = _cursor.getLong(_cursorIndexOfLastDate);
            final long _tmpNextDate;
            _tmpNextDate = _cursor.getLong(_cursorIndexOfNextDate);
            _item = new ReviewEntity(_tmpCardId,_tmpUserId,_tmpRepetitions,_tmpInterval,_tmpEaseFactor,_tmpLastDate,_tmpNextDate);
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
  public Flow<List<ReviewEntity>> getDueReviewsByTopic(final String topic, final long currentTime) {
    final String _sql = "\n"
            + "        SELECT reviews.* FROM reviews \n"
            + "        INNER JOIN cards ON reviews.cardId = cards.id \n"
            + "        WHERE cards.topic = ? AND reviews.nextDate <= ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, topic);
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentTime);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reviews",
        "cards"}, new Callable<List<ReviewEntity>>() {
      @Override
      @NonNull
      public List<ReviewEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCardId = CursorUtil.getColumnIndexOrThrow(_cursor, "cardId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfLastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastDate");
          final int _cursorIndexOfNextDate = CursorUtil.getColumnIndexOrThrow(_cursor, "nextDate");
          final List<ReviewEntity> _result = new ArrayList<ReviewEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReviewEntity _item;
            final long _tmpCardId;
            _tmpCardId = _cursor.getLong(_cursorIndexOfCardId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final long _tmpLastDate;
            _tmpLastDate = _cursor.getLong(_cursorIndexOfLastDate);
            final long _tmpNextDate;
            _tmpNextDate = _cursor.getLong(_cursorIndexOfNextDate);
            _item = new ReviewEntity(_tmpCardId,_tmpUserId,_tmpRepetitions,_tmpInterval,_tmpEaseFactor,_tmpLastDate,_tmpNextDate);
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
  public Object getRetainedCount(final long currentTime,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM reviews WHERE nextDate > ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getTotalCardCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM reviews";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getLearnedCardCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM reviews WHERE repetitions > 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getDueCardCount(final long currentTime,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM reviews WHERE nextDate <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<List<CardEntity>> getLearnedCards() {
    final String _sql = "\n"
            + "        SELECT cards.* FROM cards \n"
            + "        INNER JOIN reviews ON cards.id = reviews.cardId \n"
            + "        WHERE reviews.repetitions > 0\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards",
        "reviews"}, new Callable<List<CardEntity>>() {
      @Override
      @NonNull
      public List<CardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPos = CursorUtil.getColumnIndexOrThrow(_cursor, "pos");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfPronunciation = CursorUtil.getColumnIndexOrThrow(_cursor, "pronunciation");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfCollocation = CursorUtil.getColumnIndexOrThrow(_cursor, "collocation");
          final int _cursorIndexOfRelatedWords = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedWords");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final int _cursorIndexOfTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "topic");
          final int _cursorIndexOfTopicUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "topicUrl");
          final List<CardEntity> _result = new ArrayList<CardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CardEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            _tmpWord = _cursor.getString(_cursorIndexOfWord);
            final String _tmpPos;
            _tmpPos = _cursor.getString(_cursorIndexOfPos);
            final String _tmpMeaning;
            _tmpMeaning = _cursor.getString(_cursorIndexOfMeaning);
            final String _tmpExample;
            _tmpExample = _cursor.getString(_cursorIndexOfExample);
            final String _tmpPronunciation;
            if (_cursor.isNull(_cursorIndexOfPronunciation)) {
              _tmpPronunciation = null;
            } else {
              _tmpPronunciation = _cursor.getString(_cursorIndexOfPronunciation);
            }
            final String _tmpDescriptionEn;
            if (_cursor.isNull(_cursorIndexOfDescriptionEn)) {
              _tmpDescriptionEn = null;
            } else {
              _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            }
            final String _tmpCollocation;
            if (_cursor.isNull(_cursorIndexOfCollocation)) {
              _tmpCollocation = null;
            } else {
              _tmpCollocation = _cursor.getString(_cursorIndexOfCollocation);
            }
            final String _tmpRelatedWords;
            if (_cursor.isNull(_cursorIndexOfRelatedWords)) {
              _tmpRelatedWords = null;
            } else {
              _tmpRelatedWords = _cursor.getString(_cursorIndexOfRelatedWords);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final String _tmpAudioUrl;
            if (_cursor.isNull(_cursorIndexOfAudioUrl)) {
              _tmpAudioUrl = null;
            } else {
              _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final int _tmpLevel;
            _tmpLevel = _cursor.getInt(_cursorIndexOfLevel);
            final String _tmpTopic;
            _tmpTopic = _cursor.getString(_cursorIndexOfTopic);
            final String _tmpTopicUrl;
            if (_cursor.isNull(_cursorIndexOfTopicUrl)) {
              _tmpTopicUrl = null;
            } else {
              _tmpTopicUrl = _cursor.getString(_cursorIndexOfTopicUrl);
            }
            _item = new CardEntity(_tmpId,_tmpWord,_tmpPos,_tmpMeaning,_tmpExample,_tmpPronunciation,_tmpDescriptionEn,_tmpCollocation,_tmpRelatedWords,_tmpNote,_tmpAudioUrl,_tmpImageUrl,_tmpLevel,_tmpTopic,_tmpTopicUrl);
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
