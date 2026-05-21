package com.minlish.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.data.local.dao.CardDao_Impl;
import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.LearningLogDao_Impl;
import com.minlish.app.data.local.dao.ReviewDao;
import com.minlish.app.data.local.dao.ReviewDao_Impl;
import com.minlish.app.data.local.dao.StudyLogDao;
import com.minlish.app.data.local.dao.StudyLogDao_Impl;
import com.minlish.app.data.local.dao.TopicDao;
import com.minlish.app.data.local.dao.TopicDao_Impl;
import com.minlish.app.data.local.dao.UserDao;
import com.minlish.app.data.local.dao.UserDao_Impl;
import com.minlish.app.data.local.dao.UserStatsDao;
import com.minlish.app.data.local.dao.UserStatsDao_Impl;
import com.minlish.app.data.local.dao.WordDao;
import com.minlish.app.data.local.dao.WordDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CardDao _cardDao;

  private volatile ReviewDao _reviewDao;

  private volatile LearningLogDao _learningLogDao;

  private volatile UserStatsDao _userStatsDao;

  private volatile TopicDao _topicDao;

  private volatile WordDao _wordDao;

  private volatile UserDao _userDao;

  private volatile StudyLogDao _studyLogDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `cards` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `word` TEXT NOT NULL, `pos` TEXT NOT NULL, `meaning` TEXT NOT NULL, `example` TEXT NOT NULL, `audioUrl` TEXT, `imageUrl` TEXT, `level` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reviews` (`cardId` INTEGER NOT NULL, `userId` TEXT NOT NULL, `repetitions` INTEGER NOT NULL, `interval` INTEGER NOT NULL, `easeFactor` REAL NOT NULL, `lastDate` INTEGER NOT NULL, `nextDate` INTEGER NOT NULL, PRIMARY KEY(`cardId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `learning_logs` (`logId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `cardId` INTEGER NOT NULL, `qualityRating` INTEGER NOT NULL, `responseTime` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_stats` (`userId` TEXT NOT NULL, `currentStreak` INTEGER NOT NULL, `totalXp` INTEGER NOT NULL, `masteredCount` INTEGER NOT NULL, `dailyGoal` INTEGER NOT NULL, PRIMARY KEY(`userId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `topics` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `tags` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `words` (`id` TEXT NOT NULL, `topicId` TEXT NOT NULL, `word` TEXT NOT NULL, `pronunciation` TEXT NOT NULL, `meaning` TEXT NOT NULL, `descriptionEn` TEXT NOT NULL, `example` TEXT NOT NULL, `collocation` TEXT NOT NULL, `relatedWords` TEXT NOT NULL, `note` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`topicId`) REFERENCES `topics`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_words_topicId` ON `words` (`topicId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `username` TEXT NOT NULL, `email` TEXT NOT NULL, `profileLevel` TEXT NOT NULL, `learningGoal` TEXT NOT NULL, `dailyGoalWords` INTEGER NOT NULL, `currentStreak` INTEGER NOT NULL, `token` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `study_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dateTimestamp` INTEGER NOT NULL, `wordsReviewed` INTEGER NOT NULL, `correctAnswers` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `srs_logs` (`wordId` TEXT NOT NULL, `interval` INTEGER NOT NULL, `easeFactor` REAL NOT NULL, `repetitionCount` INTEGER NOT NULL, `nextReviewDate` INTEGER NOT NULL, PRIMARY KEY(`wordId`), FOREIGN KEY(`wordId`) REFERENCES `words`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_srs_logs_wordId` ON `srs_logs` (`wordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6fe41fa7d84e83965485848a5ab704cf')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `cards`");
        db.execSQL("DROP TABLE IF EXISTS `reviews`");
        db.execSQL("DROP TABLE IF EXISTS `learning_logs`");
        db.execSQL("DROP TABLE IF EXISTS `user_stats`");
        db.execSQL("DROP TABLE IF EXISTS `topics`");
        db.execSQL("DROP TABLE IF EXISTS `words`");
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `study_logs`");
        db.execSQL("DROP TABLE IF EXISTS `srs_logs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCards = new HashMap<String, TableInfo.Column>(8);
        _columnsCards.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("word", new TableInfo.Column("word", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("pos", new TableInfo.Column("pos", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("meaning", new TableInfo.Column("meaning", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("example", new TableInfo.Column("example", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("audioUrl", new TableInfo.Column("audioUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCards.put("level", new TableInfo.Column("level", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCards = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCards = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCards = new TableInfo("cards", _columnsCards, _foreignKeysCards, _indicesCards);
        final TableInfo _existingCards = TableInfo.read(db, "cards");
        if (!_infoCards.equals(_existingCards)) {
          return new RoomOpenHelper.ValidationResult(false, "cards(com.minlish.app.data.local.entity.CardEntity).\n"
                  + " Expected:\n" + _infoCards + "\n"
                  + " Found:\n" + _existingCards);
        }
        final HashMap<String, TableInfo.Column> _columnsReviews = new HashMap<String, TableInfo.Column>(7);
        _columnsReviews.put("cardId", new TableInfo.Column("cardId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReviews.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReviews.put("repetitions", new TableInfo.Column("repetitions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReviews.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReviews.put("easeFactor", new TableInfo.Column("easeFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReviews.put("lastDate", new TableInfo.Column("lastDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReviews.put("nextDate", new TableInfo.Column("nextDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReviews = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReviews = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReviews = new TableInfo("reviews", _columnsReviews, _foreignKeysReviews, _indicesReviews);
        final TableInfo _existingReviews = TableInfo.read(db, "reviews");
        if (!_infoReviews.equals(_existingReviews)) {
          return new RoomOpenHelper.ValidationResult(false, "reviews(com.minlish.app.data.local.entity.ReviewEntity).\n"
                  + " Expected:\n" + _infoReviews + "\n"
                  + " Found:\n" + _existingReviews);
        }
        final HashMap<String, TableInfo.Column> _columnsLearningLogs = new HashMap<String, TableInfo.Column>(6);
        _columnsLearningLogs.put("logId", new TableInfo.Column("logId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningLogs.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningLogs.put("cardId", new TableInfo.Column("cardId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningLogs.put("qualityRating", new TableInfo.Column("qualityRating", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningLogs.put("responseTime", new TableInfo.Column("responseTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLearningLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLearningLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLearningLogs = new TableInfo("learning_logs", _columnsLearningLogs, _foreignKeysLearningLogs, _indicesLearningLogs);
        final TableInfo _existingLearningLogs = TableInfo.read(db, "learning_logs");
        if (!_infoLearningLogs.equals(_existingLearningLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "learning_logs(com.minlish.app.data.local.entity.LearningLogEntity).\n"
                  + " Expected:\n" + _infoLearningLogs + "\n"
                  + " Found:\n" + _existingLearningLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsUserStats = new HashMap<String, TableInfo.Column>(5);
        _columnsUserStats.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStats.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStats.put("totalXp", new TableInfo.Column("totalXp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStats.put("masteredCount", new TableInfo.Column("masteredCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserStats.put("dailyGoal", new TableInfo.Column("dailyGoal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserStats = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserStats = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserStats = new TableInfo("user_stats", _columnsUserStats, _foreignKeysUserStats, _indicesUserStats);
        final TableInfo _existingUserStats = TableInfo.read(db, "user_stats");
        if (!_infoUserStats.equals(_existingUserStats)) {
          return new RoomOpenHelper.ValidationResult(false, "user_stats(com.minlish.app.data.local.entity.UserStatsEntity).\n"
                  + " Expected:\n" + _infoUserStats + "\n"
                  + " Found:\n" + _existingUserStats);
        }
        final HashMap<String, TableInfo.Column> _columnsTopics = new HashMap<String, TableInfo.Column>(4);
        _columnsTopics.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTopics.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTopics.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTopics.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTopics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTopics = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTopics = new TableInfo("topics", _columnsTopics, _foreignKeysTopics, _indicesTopics);
        final TableInfo _existingTopics = TableInfo.read(db, "topics");
        if (!_infoTopics.equals(_existingTopics)) {
          return new RoomOpenHelper.ValidationResult(false, "topics(com.minlish.app.data.local.entity.TopicEntity).\n"
                  + " Expected:\n" + _infoTopics + "\n"
                  + " Found:\n" + _existingTopics);
        }
        final HashMap<String, TableInfo.Column> _columnsWords = new HashMap<String, TableInfo.Column>(10);
        _columnsWords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("topicId", new TableInfo.Column("topicId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("word", new TableInfo.Column("word", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("pronunciation", new TableInfo.Column("pronunciation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("meaning", new TableInfo.Column("meaning", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("descriptionEn", new TableInfo.Column("descriptionEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("example", new TableInfo.Column("example", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("collocation", new TableInfo.Column("collocation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("relatedWords", new TableInfo.Column("relatedWords", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWords.put("note", new TableInfo.Column("note", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWords.add(new TableInfo.ForeignKey("topics", "CASCADE", "NO ACTION", Arrays.asList("topicId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWords = new HashSet<TableInfo.Index>(1);
        _indicesWords.add(new TableInfo.Index("index_words_topicId", false, Arrays.asList("topicId"), Arrays.asList("ASC")));
        final TableInfo _infoWords = new TableInfo("words", _columnsWords, _foreignKeysWords, _indicesWords);
        final TableInfo _existingWords = TableInfo.read(db, "words");
        if (!_infoWords.equals(_existingWords)) {
          return new RoomOpenHelper.ValidationResult(false, "words(com.minlish.app.data.local.entity.WordEntity).\n"
                  + " Expected:\n" + _infoWords + "\n"
                  + " Found:\n" + _existingWords);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(8);
        _columnsUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("profileLevel", new TableInfo.Column("profileLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("learningGoal", new TableInfo.Column("learningGoal", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("dailyGoalWords", new TableInfo.Column("dailyGoalWords", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("token", new TableInfo.Column("token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.minlish.app.data.local.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsStudyLogs = new HashMap<String, TableInfo.Column>(4);
        _columnsStudyLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudyLogs.put("dateTimestamp", new TableInfo.Column("dateTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudyLogs.put("wordsReviewed", new TableInfo.Column("wordsReviewed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudyLogs.put("correctAnswers", new TableInfo.Column("correctAnswers", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudyLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudyLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudyLogs = new TableInfo("study_logs", _columnsStudyLogs, _foreignKeysStudyLogs, _indicesStudyLogs);
        final TableInfo _existingStudyLogs = TableInfo.read(db, "study_logs");
        if (!_infoStudyLogs.equals(_existingStudyLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "study_logs(com.minlish.app.data.local.entity.StudyLogEntity).\n"
                  + " Expected:\n" + _infoStudyLogs + "\n"
                  + " Found:\n" + _existingStudyLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsSrsLogs = new HashMap<String, TableInfo.Column>(5);
        _columnsSrsLogs.put("wordId", new TableInfo.Column("wordId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSrsLogs.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSrsLogs.put("easeFactor", new TableInfo.Column("easeFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSrsLogs.put("repetitionCount", new TableInfo.Column("repetitionCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSrsLogs.put("nextReviewDate", new TableInfo.Column("nextReviewDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSrsLogs = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysSrsLogs.add(new TableInfo.ForeignKey("words", "CASCADE", "NO ACTION", Arrays.asList("wordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesSrsLogs = new HashSet<TableInfo.Index>(1);
        _indicesSrsLogs.add(new TableInfo.Index("index_srs_logs_wordId", false, Arrays.asList("wordId"), Arrays.asList("ASC")));
        final TableInfo _infoSrsLogs = new TableInfo("srs_logs", _columnsSrsLogs, _foreignKeysSrsLogs, _indicesSrsLogs);
        final TableInfo _existingSrsLogs = TableInfo.read(db, "srs_logs");
        if (!_infoSrsLogs.equals(_existingSrsLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "srs_logs(com.minlish.app.data.local.entity.SrsLogEntity).\n"
                  + " Expected:\n" + _infoSrsLogs + "\n"
                  + " Found:\n" + _existingSrsLogs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6fe41fa7d84e83965485848a5ab704cf", "b902f636d89c4a9b40805d3ce03e642f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "cards","reviews","learning_logs","user_stats","topics","words","users","study_logs","srs_logs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `cards`");
      _db.execSQL("DELETE FROM `reviews`");
      _db.execSQL("DELETE FROM `learning_logs`");
      _db.execSQL("DELETE FROM `user_stats`");
      _db.execSQL("DELETE FROM `topics`");
      _db.execSQL("DELETE FROM `words`");
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `study_logs`");
      _db.execSQL("DELETE FROM `srs_logs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CardDao.class, CardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReviewDao.class, ReviewDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LearningLogDao.class, LearningLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserStatsDao.class, UserStatsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TopicDao.class, TopicDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WordDao.class, WordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StudyLogDao.class, StudyLogDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CardDao cardDao() {
    if (_cardDao != null) {
      return _cardDao;
    } else {
      synchronized(this) {
        if(_cardDao == null) {
          _cardDao = new CardDao_Impl(this);
        }
        return _cardDao;
      }
    }
  }

  @Override
  public ReviewDao reviewDao() {
    if (_reviewDao != null) {
      return _reviewDao;
    } else {
      synchronized(this) {
        if(_reviewDao == null) {
          _reviewDao = new ReviewDao_Impl(this);
        }
        return _reviewDao;
      }
    }
  }

  @Override
  public LearningLogDao learningLogDao() {
    if (_learningLogDao != null) {
      return _learningLogDao;
    } else {
      synchronized(this) {
        if(_learningLogDao == null) {
          _learningLogDao = new LearningLogDao_Impl(this);
        }
        return _learningLogDao;
      }
    }
  }

  @Override
  public UserStatsDao userStatsDao() {
    if (_userStatsDao != null) {
      return _userStatsDao;
    } else {
      synchronized(this) {
        if(_userStatsDao == null) {
          _userStatsDao = new UserStatsDao_Impl(this);
        }
        return _userStatsDao;
      }
    }
  }

  @Override
  public TopicDao topicDao() {
    if (_topicDao != null) {
      return _topicDao;
    } else {
      synchronized(this) {
        if(_topicDao == null) {
          _topicDao = new TopicDao_Impl(this);
        }
        return _topicDao;
      }
    }
  }

  @Override
  public WordDao wordDao() {
    if (_wordDao != null) {
      return _wordDao;
    } else {
      synchronized(this) {
        if(_wordDao == null) {
          _wordDao = new WordDao_Impl(this);
        }
        return _wordDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public StudyLogDao studyLogDao() {
    if (_studyLogDao != null) {
      return _studyLogDao;
    } else {
      synchronized(this) {
        if(_studyLogDao == null) {
          _studyLogDao = new StudyLogDao_Impl(this);
        }
        return _studyLogDao;
      }
    }
  }
}
