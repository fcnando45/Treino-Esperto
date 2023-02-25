package esperto.treino.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import esperto.treino.Helper.DbHelper;

public class Treino {
    private Integer id;
    private String nome;
    private int id_aluno;
    private String repeticoes;
    private SQLiteDatabase database;
    private DbHelper db;

    public Treino(Integer id, String nome,String repeticoes,  int id_aluno, Context context) {
        this.id = id;
        this.nome = nome;
        this.id_aluno = id_aluno;
        this.database = database;
        this.repeticoes = repeticoes;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
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

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Treino() {}

    public Treino(Context context) {
        db = new DbHelper(context);
        database = db.getWritableDatabase();

    }

    public boolean criarTreino(){
        ContentValues dados = new ContentValues();
        dados.put("nome",this.nome);
        dados.put("repeticoes", this.repeticoes);
        dados.put("id_aluno", this.id_aluno);
        long ret = database.insert("treinos", null, dados);

        if (ret > 0){
            return true;
        }
        return false;


    }

    public Cursor listarTreino(String id){

        String sql = "SELECT id as _id, nome, repeticoes From treinos where id_aluno = ?; ";
        Cursor c = database.rawQuery(sql,new String[]{id+""});
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }
}
