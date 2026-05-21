package com.minlish.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.minlish.app.data.local.entity.UserStatsEntity;
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
public final class UserStatsDao_Impl implements UserStatsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserStatsEntity> __insertionAdapterOfUserStatsEntity;

  public UserStatsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserStatsEntity = new EntityInsertionAdapter<UserStatsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_stats` (`userId`,`currentStreak`,`totalXp`,`masteredCount`,`dailyGoal`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserStatsEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindLong(2, entity.getCurrentStreak());
        statement.bindLong(3, entity.getTotalXp());
        statement.bindLong(4, entity.getMasteredCount());
        statement.bindLong(5, entity.getDailyGoal());
      }
    };
  }

  @Override
  public Object insertOrUpdateStats(final UserStatsEntity stats,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserStatsEntity.insert(stats);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserStatsEntity> getUserStats(final String userId) {
    final String _sql = "SELECT * FROM user_stats WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_stats"}, new Callable<UserStatsEntity>() {
      @Override
      @Nullable
      public UserStatsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfTotalXp = CursorUtil.getColumnIndexOrThrow(_cursor, "totalXp");
          final int _cursorIndexOfMasteredCount = CursorUtil.getColumnIndexOrThrow(_cursor, "masteredCount");
          final int _cursorIndexOfDailyGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyGoal");
          final UserStatsEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final int _tmpTotalXp;
            _tmpTotalXp = _cursor.getInt(_cursorIndexOfTotalXp);
            final int _tmpMasteredCount;
            _tmpMasteredCount = _cursor.getInt(_cursorIndexOfMasteredCount);
            final int _tmpDailyGoal;
            _tmpDailyGoal = _cursor.getInt(_cursorIndexOfDailyGoal);
            _result = new UserStatsEntity(_tmpUserId,_tmpCurrentStreak,_tmpTotalXp,_tmpMasteredCount,_tmpDailyGoal);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
