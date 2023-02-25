package esperto.treino.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import esperto.treino.Helper.DbHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class User {
    private String id;
    private String user;

    private String password;
    private DbHelper db;
    private SQLiteDatabase database;

    public User() {

    }

    public User(Context context) {
        db = new DbHelper(context);
        database = db.getWritableDatabase();

    }

    public User(String id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;

    }

    public User(String id, String user, String password,
                      Context context) {
        this.id = id;
        this.user = user;
        this.password = password;
        db = new DbHelper(context);
        database = db.getWritableDatabase();


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DbHelper getDb() {
        return db;
    }

    public void setDb(DbHelper db) {
        this.db = db;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }






    public boolean inserir(){
        ContentValues dados = new ContentValues();
        dados.put("user",this.user);
        dados.put("password", this.password);

        long ret = database.insert("users", null, dados);

        if (ret > 0){
            return true;
        }
        return false;


        }







}
