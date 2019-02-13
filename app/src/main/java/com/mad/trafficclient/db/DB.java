package com.mad.trafficclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mad.trafficclient.BaseApplication;
import com.mad.trafficclient.bean.F1_History;
import com.mad.trafficclient.bean.F5_Sense;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Db.
 */
public class DB extends OrmLiteSqliteOpenHelper {
    private static String databaseName = "traffic.db";
    private static int databaseVersion = 1;
    private static DB instance;
    private Map<String, Dao> map = new HashMap<>();

    /**
     * 构造函数.
     *
     * @param context the context
     */
    public DB(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {//创建表
            TableUtils.createTableIfNotExists(connectionSource, F1_History.class);
            TableUtils.createTableIfNotExists(connectionSource, F5_Sense.class);
            /*TableUtils.createTableIfNotExists(connectionSource, F9_History.class);
            TableUtils.createTableIfNotExists(connectionSource, F12.class);
            TableUtils.createTableIfNotExists(connectionSource, F31_Opinion.class);
            TableUtils.createTableIfNotExists(connectionSource, F34.class);
            TableUtils.createTableIfNotExists(connectionSource, F38_Order.class);
            TableUtils.createTableIfNotExists(connectionSource, F45_History.class);*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {//更新表
            TableUtils.dropTable(connectionSource, F1_History.class, true);
            TableUtils.dropTable(connectionSource, F5_Sense.class, true);
            /*TableUtils.dropTable(connectionSource, F9_History.class, true);
            TableUtils.dropTable(connectionSource, F12.class, true);
            TableUtils.dropTable(connectionSource, F31_Opinion.class, true);
            TableUtils.dropTable(connectionSource, F34.class, true);
            TableUtils.dropTable(connectionSource, F38_Order.class, true);
            TableUtils.dropTable(connectionSource, F45_History.class, true);*/
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取实例.
     *
     * @return the instance
     */
    public synchronized static DB getInstance() {
        if (instance == null) {
            synchronized (DB.class) {
                if (instance == null) {
                    instance = new DB(BaseApplication.getInstance(
                    ));
                }
            }
        }
        return instance;
    }

    /**
     * 获取dao.
     *
     * @return Dao
     */
    public synchronized Dao getDao(Class clazz) {
        Dao dao = null;
//获取类名
        String name = clazz.getSimpleName();
//如果已存在则从map中取出
        if (map.containsKey(name)) {
            dao = map.get(name);
        }
//如果dao为空，则创建dao
        if (dao == null) {
            try {
//创建dao并存入map中
                dao = super.getDao(clazz);
                map.put(name, dao);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}