package com.example.roomdatabase;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UsersDao_Impl implements UsersDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Users> __insertionAdapterOfUsers;

  private final EntityDeletionOrUpdateAdapter<Users> __deletionAdapterOfUsers;

  private final EntityDeletionOrUpdateAdapter<Users> __updateAdapterOfUsers;

  public UsersDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsers = new EntityInsertionAdapter<Users>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `users` (`id`,`username`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Users value) {
        stmt.bindLong(1, value.getId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
      }
    };
    this.__deletionAdapterOfUsers = new EntityDeletionOrUpdateAdapter<Users>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `users` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Users value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfUsers = new EntityDeletionOrUpdateAdapter<Users>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`username` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Users value) {
        stmt.bindLong(1, value.getId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        stmt.bindLong(3, value.getId());
      }
    };
  }

  @Override
  public void insertUsers(final Users users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsers.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUser(final Users users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUsers.handle(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateUser(final Users users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUsers.handle(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Users>> getAllUsers() {
    final String _sql = "SELECT * from users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"users"}, false, new Callable<List<Users>>() {
      @Override
      public List<Users> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final List<Users> _result = new ArrayList<Users>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Users _item;
            _item = new Users();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            _item.setUsername(_tmpUsername);
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
}
