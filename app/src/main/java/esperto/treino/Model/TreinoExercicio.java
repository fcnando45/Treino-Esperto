package esperto.treino.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import esperto.treino.Helper.DbHelper;

public class TreinoExercicio {
    private Integer id;
    private int id_exercicio;
    private int id_treino;

    private SQLiteDatabase database;
    private DbHelper db;

    public TreinoExercicio(Integer id, int id_exercicio, int id_treino, Context context) {
        this.id = id;
        this.id_exercicio = id_exercicio;
        this.id_treino = id_treino;
        this.database = database;
        this.db = db;
        db = new DbHelper(context);

        database = db.getWritableDatabase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_exercicio() {
        return id_exercicio;
    }

    public void setId_exercicio(int id_exercicio) {
        this.id_exercicio = id_exercicio;
    }

    public int getId_treino() {
        return id_treino;
    }

    public void setId_treino(int id_treino) {
        this.id_treino = id_treino;
    }



    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public DbHelper getDb() {
        return db;
    }

    public void setDb(DbHelper db) {
        this.db = db;
    }

    public TreinoExercicio() {}

    public TreinoExercicio(Context context) {
        db = new DbHelper(context);
        database = db.getWritableDatabase();
    }

    public boolean criarTreinoExercicio(){
        ContentValues dados = new ContentValues();
        dados.put("id_exercicio", this.id_exercicio);
        dados.put("id_treino", this.id_treino);
        long ret = database.insert("treino_exercicios", null, dados);

        if (ret > 0){
            return true;
        }
        return false;
    }

    public Cursor listarTreinoExercicio(String id){
        String sql = "SELECT treinos.id as _id, exercicios.nome, exercicios.grupo_muscular FROM exercicios INNER JOIN treino_exercicios ON exercicios.id = treino_exercicios.id_exercicio INNER JOIN treinos ON treino_exercicios.id_treino = treinos.id Where treinos.id = ?;";
        Cursor c = database.rawQuery(sql,new String[]{id+""});
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor listarTreinoExercicioNaoListados(String id){

        String sql = "SELECT treinos.id as _id, exercicios.id, exercicios.nome, exercicios.grupo_muscular FROM exercicios, treino_exercicios, treinos WHERE exercicios.id = treino_exercicios.id_exercicio AND treino_exercicios.id_treino = treinos.id AND treino_exercicios.id_treino = 1 AND treinos.id != ?; ";
        Cursor c = database.rawQuery(sql,new String[]{id+""});
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }
}
