package com.minlish.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.minlish.app.data.local.entity.ReviewEntity;
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
public final class ReviewDao_Impl implements ReviewDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReviewEntity> __insertionAdapterOfReviewEntity;

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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
