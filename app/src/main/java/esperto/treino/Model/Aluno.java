package esperto.treino.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import esperto.treino.Helper.DbHelper;

public class Aluno {

    private Integer id;
    private String nome;
    private String password;
    private String endereco;
    private DbHelper db;

    public Aluno(Integer id, String nome, String password, String endereco, Context context) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.endereco = endereco;
        db = new DbHelper(context);

        database = db.getWritableDatabase();
    }

    public Aluno() {
    }

    public Aluno(Context context) {
        db = new DbHelper(context);
        database = db.getWritableDatabase();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    private SQLiteDatabase database;


    public boolean inserir(){
        ContentValues dados = new ContentValues();
        dados.put("nome",this.nome);
        dados.put("password", this.password);
        dados.put("endereco", this.endereco);

        long ret = database.insert("alunos", null, dados);

        if (ret > 0){
            return true;
        }
        return false;


    }

    public Cursor listar(){
        String sql = "SELECT id as _id, nome From alunos;";
        Cursor c = database.rawQuery(sql,null);

        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor detalhar(String id){
         String nome;

        String sql = "SELECT id as _id, * From alunos where id = ?; ";
        Cursor c = database.rawQuery(sql,new String[]{id+""});
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }







}
