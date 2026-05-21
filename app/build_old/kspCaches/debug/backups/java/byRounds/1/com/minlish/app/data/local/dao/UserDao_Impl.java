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
import com.minlish.app.data.local.entity.UserEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStreak;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`username`,`email`,`profileLevel`,`learningGoal`,`dailyGoalWords`,`currentStreak`,`token`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getEmail());
        statement.bindString(4, entity.getProfileLevel());
        statement.bindString(5, entity.getLearningGoal());
        statement.bindLong(6, entity.getDailyGoalWords());
        statement.bindLong(7, entity.getCurrentStreak());
        if (entity.getToken() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getToken());
        }
      }
    };
    this.__preparedStmtOfUpdateStreak = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET currentStreak = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertUser(final UserEntity user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStreak(final int streak, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStreak.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, streak);
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
          __preparedStmtOfUpdateStreak.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserEntity> getUser() {
    final String _sql = "SELECT * FROM users LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfProfileLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "profileLevel");
          final int _cursorIndexOfLearningGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "learningGoal");
          final int _cursorIndexOfDailyGoalWords = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyGoalWords");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(_cursor, "token");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpProfileLevel;
            _tmpProfileLevel = _cursor.getString(_cursorIndexOfProfileLevel);
            final String _tmpLearningGoal;
            _tmpLearningGoal = _cursor.getString(_cursorIndexOfLearningGoal);
            final int _tmpDailyGoalWords;
            _tmpDailyGoalWords = _cursor.getInt(_cursorIndexOfDailyGoalWords);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final String _tmpToken;
            if (_cursor.isNull(_cursorIndexOfToken)) {
              _tmpToken = null;
            } else {
              _tmpToken = _cursor.getString(_cursorIndexOfToken);
            }
            _result = new UserEntity(_tmpId,_tmpUsername,_tmpEmail,_tmpProfileLevel,_tmpLearningGoal,_tmpDailyGoalWords,_tmpCurrentStreak,_tmpToken);
          } else {
            _result = null;
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
  public Object getUserById(final String id, final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfProfileLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "profileLevel");
          final int _cursorIndexOfLearningGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "learningGoal");
          final int _cursorIndexOfDailyGoalWords = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyGoalWords");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfToken = CursorUtil.getColumnIndexOrThrow(_cursor, "token");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpProfileLevel;
            _tmpProfileLevel = _cursor.getString(_cursorIndexOfProfileLevel);
            final String _tmpLearningGoal;
            _tmpLearningGoal = _cursor.getString(_cursorIndexOfLearningGoal);
            final int _tmpDailyGoalWords;
            _tmpDailyGoalWords = _cursor.getInt(_cursorIndexOfDailyGoalWords);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final String _tmpToken;
            if (_cursor.isNull(_cursorIndexOfToken)) {
              _tmpToken = null;
            } else {
              _tmpToken = _cursor.getString(_cursorIndexOfToken);
            }
            _result = new UserEntity(_tmpId,_tmpUsername,_tmpEmail,_tmpProfileLevel,_tmpLearningGoal,_tmpDailyGoalWords,_tmpCurrentStreak,_tmpToken);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
