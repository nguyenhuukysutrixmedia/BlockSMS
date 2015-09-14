package culun.app.blocksms.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import culun.app.blocksms.R;
import culun.app.blocksms.model.MyContact;
import culun.app.blocksms.model.MySms;

/**
 * Created by Ky on 9/14/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "block_sms.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 12;

    // the DAO object we use to access the MySms table
    private Dao<MySms, String> mySmsDao = null;
    private RuntimeExceptionDao<MySms, String> mySmsRuntimeDao = null;
    // the DAO object we use to access the MySms table
    private Dao<MyContact, String> myContactDao = null;
    private RuntimeExceptionDao<MyContact, String> myContactRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION,
                R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, MySms.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, MyContact.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

        // here we try inserting data in the on-create as a test
        RuntimeExceptionDao<MySms, String> dao = getMySmsDao();
        long millis = System.currentTimeMillis();
        // create some entries in the onCreate
        MySms simple = new MySms(millis);
        dao.create(simple);
        simple = new MySms(millis + 1);
        dao.create(simple);


        ///
        RuntimeExceptionDao<MyContact, String> myContactDao = getMyContactDao();
        MyContact myContact = new MyContact(1, "090");
        myContactDao.create(myContact);
        myContact = new MyContact(2, "999");
        myContactDao.create(myContact);
        myContact = new MyContact(2, "9224");
        myContactDao.create(myContact);

        Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, MySms.class, true);
            TableUtils.dropTable(connectionSource, MyContact.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }


//    /**
//     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
//     * value.
//     */
//    public Dao<MySms, String> getDao() throws SQLException {
//        if (mySmsDao == null) {
//            mySmsDao = getDao(MySms.class);
//        }
//        return mySmsDao;
//    }

    public RuntimeExceptionDao<MySms, String> getMySmsDao() {
        if (mySmsRuntimeDao == null) {
            mySmsRuntimeDao = getRuntimeExceptionDao(MySms.class);
        }
        return mySmsRuntimeDao;
    }

//    public Dao<MyContact, String> getDao() throws SQLException {
//        if (mySmsDao == null) {
//            mySmsDao = getDao(MySms.class);
//        }
//        return mySmsDao;
//    }

    public RuntimeExceptionDao<MyContact, String> getMyContactDao() {
        if (myContactRuntimeDao == null) {
            myContactRuntimeDao = getRuntimeExceptionDao(MyContact.class);
        }
        return myContactRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        mySmsDao = null;
        mySmsRuntimeDao = null;
        myContactRuntimeDao = null;
    }

}
