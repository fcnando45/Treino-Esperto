package esperto.treino.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import esperto.treino.Helper.DbHelper;

public class Avaliacao {
    private Integer id;
    private int id_aluno;
    private int altura;
    private float peso;
    private int idade;
    private int ombro;
    private int peito;
    private int braco_esquerdo;
    private int braco_direito;
    private int cintura;
    private int quadril;
    private int coxa_esquerda;
    private int coxa_direita;
    private String genero;
    private Date data;
    private DbHelper db;

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    private SQLiteDatabase database;

    public Avaliacao(Integer id, Integer id_aluno, int altura, float peso, int idade, int ombro,
                     int peito, int braco_esquerdo, int braco_direito, int cintura, int quadril,
                     int coxa_esquerda, int coxa_direita, String genero, Date data, Context context) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.altura = altura;
        this.peso = peso;
        this.idade = idade;
        this.ombro = ombro;
        this.peito = peito;
        this.braco_esquerdo = braco_esquerdo;
        this.braco_direito = braco_direito;
        this.cintura = cintura;
        this.quadril = quadril;
        this.coxa_esquerda = coxa_esquerda;
        this.coxa_direita = coxa_direita;
        this.genero = genero;
        this.data = data;
        db = new DbHelper(context);
        database = db.getWritableDatabase();
    }

    public Avaliacao(Context context) {
        db = new DbHelper(context);
        database = db.getWritableDatabase();

    }

    public Avaliacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getOmbro() {
        return ombro;
    }

    public void setOmbro(int ombro) {
        this.ombro = ombro;
    }

    public int getPeito() {
        return peito;
    }

    public void setPeito(int peito) {
        this.peito = peito;
    }

    public int getBraco_esquerdo() {
        return braco_esquerdo;
    }

    public void setBraco_esquerdo(int braco_esquerdo) {
        this.braco_esquerdo = braco_esquerdo;
    }

    public int getBraco_direito() {
        return braco_direito;
    }

    public void setBraco_direito(int braco_direito) {
        this.braco_direito = braco_direito;
    }

    public int getCintura() {
        return cintura;
    }

    public void setCintura(int cintura) {
        this.cintura = cintura;
    }

    public int getQuadril() {
        return quadril;
    }

    public void setQuadril(int quadril) {
        this.quadril = quadril;
    }

    public int getCoxa_esquerda() {
        return coxa_esquerda;
    }

    public void setCoxa_esquerda(int coxa_esquerda) {
        this.coxa_esquerda = coxa_esquerda;
    }

    public int getCoxa_direita() {
        return coxa_direita;
    }

    public void setCoxa_direita(int coxa_direita) {
        this.coxa_direita = coxa_direita;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean criarAvaliacao() {
        ContentValues valores = new ContentValues();
        valores.put("id_aluno", id_aluno);
        valores.put("altura", altura);
        valores.put("peso", peso);
        valores.put("idade", idade);
        valores.put("ombro", ombro);
        valores.put("peito", peito);
        valores.put("braco_esquerdo", braco_esquerdo);
        valores.put("braco_direito", braco_direito);
        valores.put("cintura", cintura);
        valores.put("quadril", quadril);
        valores.put("coxa_esquerda", coxa_esquerda);
        valores.put("coxa_direita", coxa_direita);
        valores.put("genero", genero);
        valores.put("data", String.valueOf(data));


        long ret = database.insert("avaliacoes", null, valores);

        if (ret > 0) {
            return true;
        }
        return false;

    }



    public Cursor listarAvaliacao(String id){

        String sql = "SELECT id as _id, data, peso, idade From avaliacoes where id_aluno = ?; ";
        Cursor c = database.rawQuery(sql,new String[]{id+""});
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    public Cursor detalharAvaliacao(String id){

        String sql = "SELECT id as _id, * From avaliacoes where id = ?; ";
        Cursor c = database.rawQuery(sql,new String[]{id+""});
        if(c != null){
            c.moveToFirst();
        }
        return c;
    }

    public String imc(int altura, Float peso){
        double altura1 = (double) altura;
        double alturaImc = altura1/100;

        double imc = peso / (alturaImc*alturaImc);

        String classificacao;
        String risco = "";

        if (imc < 16) {
            classificacao = "Magreza Grau III";
        } else if (imc >= 16 && imc < 16.9) {
            classificacao = "Magreza Grau II";
        } else if (imc >= 17 && imc < 18.4) {
            classificacao = "Magreza Grau I";
        } else if (imc >= 18.5 && imc < 25) {
            classificacao = "Eutrofia";
        } else if (imc >= 25 && imc < 30) {
            classificacao = "Sobrepeso";
            risco = "Seu Risco é aumentado, cuide da alimentação e faça mais exercicios";
        } else if (imc >= 30 && imc < 35) {
            classificacao = "Obesidade Grau I";
            risco = "Seu Risco é moderado, cuide da alimentação e faça mais exercicios";
        } else if (imc >= 35 && imc < 40) {
            classificacao = "Obesidade Grau II, procure um médico imendiatamente, sua saúde está em risco";
            risco = "Grave";
        } else {
            classificacao = "Obesidade Grau III, procure um médico imendiatamente, sua saúde está em risco";
            risco = "Muito";
        }

        double imcArredondado = Math.round(imc);
        String imcString = String.valueOf(imcArredondado);

        return imcString + " \n" + classificacao + "\n " + "\n" + risco;

    }
}
