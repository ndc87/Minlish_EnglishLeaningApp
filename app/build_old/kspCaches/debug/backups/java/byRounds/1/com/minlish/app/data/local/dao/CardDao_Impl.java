package com.minlish.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.minlish.app.data.local.entity.CardEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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
public final class CardDao_Impl implements CardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CardEntity> __insertionAdapterOfCardEntity;

  private final EntityDeletionOrUpdateAdapter<CardEntity> __deletionAdapterOfCardEntity;

  private final EntityDeletionOrUpdateAdapter<CardEntity> __updateAdapterOfCardEntity;

  public CardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCardEntity = new EntityInsertionAdapter<CardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cards` (`id`,`word`,`pos`,`meaning`,`example`,`audioUrl`,`imageUrl`,`level`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CardEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getWord());
        statement.bindString(3, entity.getPos());
        statement.bindString(4, entity.getMeaning());
        statement.bindString(5, entity.getExample());
        if (entity.getAudioUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAudioUrl());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        statement.bindLong(8, entity.getLevel());
      }
    };
    this.__deletionAdapterOfCardEntity = new EntityDeletionOrUpdateAdapter<CardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cards` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CardEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCardEntity = new EntityDeletionOrUpdateAdapter<CardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cards` SET `id` = ?,`word` = ?,`pos` = ?,`meaning` = ?,`example` = ?,`audioUrl` = ?,`imageUrl` = ?,`level` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CardEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getWord());
        statement.bindString(3, entity.getPos());
        statement.bindString(4, entity.getMeaning());
        statement.bindString(5, entity.getExample());
        if (entity.getAudioUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAudioUrl());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        statement.bindLong(8, entity.getLevel());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertCard(final CardEntity card, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCardEntity.insertAndReturnId(card);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCards(final List<CardEntity> cards,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCardEntity.insert(cards);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCard(final CardEntity card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCardEntity.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCard(final CardEntity card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCardEntity.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCardWithReview(final CardEntity card, final ReviewDao reviewDao,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> CardDao.DefaultImpls.insertCardWithReview(CardDao_Impl.this, card, reviewDao, __cont), $completion);
  }

  @Override
  public Flow<List<CardEntity>> getAllCards() {
    final String _sql = "SELECT * FROM cards";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<CardEntity>>() {
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
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
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
            _item = new CardEntity(_tmpId,_tmpWord,_tmpPos,_tmpMeaning,_tmpExample,_tmpAudioUrl,_tmpImageUrl,_tmpLevel);
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
  public Object getCardById(final long id, final Continuation<? super CardEntity> $completion) {
    final String _sql = "SELECT * FROM cards WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CardEntity>() {
      @Override
      @Nullable
      public CardEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPos = CursorUtil.getColumnIndexOrThrow(_cursor, "pos");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final CardEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new CardEntity(_tmpId,_tmpWord,_tmpPos,_tmpMeaning,_tmpExample,_tmpAudioUrl,_tmpImageUrl,_tmpLevel);
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
  public Object getAllCardsList(final Continuation<? super List<CardEntity>> $completion) {
    final String _sql = "SELECT * FROM cards";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CardEntity>>() {
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
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
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
            _item = new CardEntity(_tmpId,_tmpWord,_tmpPos,_tmpMeaning,_tmpExample,_tmpAudioUrl,_tmpImageUrl,_tmpLevel);
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

  @Override
  public Object getRandomCard(final Continuation<? super CardEntity> $completion) {
    final String _sql = "SELECT * FROM cards ORDER BY RANDOM() LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CardEntity>() {
      @Override
      @Nullable
      public CardEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfPos = CursorUtil.getColumnIndexOrThrow(_cursor, "pos");
          final int _cursorIndexOfMeaning = CursorUtil.getColumnIndexOrThrow(_cursor, "meaning");
          final int _cursorIndexOfExample = CursorUtil.getColumnIndexOrThrow(_cursor, "example");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUrl");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "level");
          final CardEntity _result;
          if (_cursor.moveToFirst()) {
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
            _result = new CardEntity(_tmpId,_tmpWord,_tmpPos,_tmpMeaning,_tmpExample,_tmpAudioUrl,_tmpImageUrl,_tmpLevel);
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
