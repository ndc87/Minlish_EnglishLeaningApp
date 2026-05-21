package com.minlish.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.minlish.app.data.local.entity.StudyLogEntity;
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
public final class StudyLogDao_Impl implements StudyLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StudyLogEntity> __insertionAdapterOfStudyLogEntity;

  public StudyLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudyLogEntity = new EntityInsertionAdapter<StudyLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `study_logs` (`id`,`dateTimestamp`,`wordsReviewed`,`correctAnswers`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudyLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDateTimestamp());
        statement.bindLong(3, entity.getWordsReviewed());
        statement.bindLong(4, entity.getCorrectAnswers());
      }
    };
  }

  @Override
  public Object insertLog(final StudyLogEntity log, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStudyLogEntity.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StudyLogEntity>> getAllLogs() {
    final String _sql = "SELECT * FROM study_logs ORDER BY dateTimestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"study_logs"}, new Callable<List<StudyLogEntity>>() {
      @Override
      @NonNull
      public List<StudyLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTimestamp");
          final int _cursorIndexOfWordsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsReviewed");
          final int _cursorIndexOfCorrectAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "correctAnswers");
          final List<StudyLogEntity> _result = new ArrayList<StudyLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StudyLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDateTimestamp;
            _tmpDateTimestamp = _cursor.getLong(_cursorIndexOfDateTimestamp);
            final int _tmpWordsReviewed;
            _tmpWordsReviewed = _cursor.getInt(_cursorIndexOfWordsReviewed);
            final int _tmpCorrectAnswers;
            _tmpCorrectAnswers = _cursor.getInt(_cursorIndexOfCorrectAnswers);
            _item = new StudyLogEntity(_tmpId,_tmpDateTimestamp,_tmpWordsReviewed,_tmpCorrectAnswers);
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
  public Object getLogsForDay(final long startOfDay, final long endOfDay,
      final Continuation<? super List<StudyLogEntity>> $completion) {
    final String _sql = "SELECT * FROM study_logs WHERE dateTimestamp >= ? AND dateTimestamp < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startOfDay);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endOfDay);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StudyLogEntity>>() {
      @Override
      @NonNull
      public List<StudyLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDateTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTimestamp");
          final int _cursorIndexOfWordsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsReviewed");
          final int _cursorIndexOfCorrectAnswers = CursorUtil.getColumnIndexOrThrow(_cursor, "correctAnswers");
          final List<StudyLogEntity> _result = new ArrayList<StudyLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StudyLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDateTimestamp;
            _tmpDateTimestamp = _cursor.getLong(_cursorIndexOfDateTimestamp);
            final int _tmpWordsReviewed;
            _tmpWordsReviewed = _cursor.getInt(_cursorIndexOfWordsReviewed);
            final int _tmpCorrectAnswers;
            _tmpCorrectAnswers = _cursor.getInt(_cursorIndexOfCorrectAnswers);
            _item = new StudyLogEntity(_tmpId,_tmpDateTimestamp,_tmpWordsReviewed,_tmpCorrectAnswers);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
