package esperto.treino.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TreinoEsperto.dv", null, 1);
    }

    public DbHelper(Context context){
        super(context, "TreinoEsperto.dv", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateUser = "create table users (id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR(25), password VARCHAR(10));";
        db.execSQL(sqlCreateUser);
        String sqlCreateAluno = "create table alunos (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), password VARCHAR(10),endereco VARCHAR(50));";
        db.execSQL(sqlCreateAluno);
        String sqlCreateAvaliacao = "create table avaliacoes (id INTEGER PRIMARY KEY AUTOINCREMENT, id_aluno INTEGER, altura REAL,peso REAL, idade INTEGER,ombro INTEGER,peito INTEGER, braco_esquerdo INTEGER,braco_direito INTEGER,cintura INTEGER, quadril INTEGER, coxa_esquerda INTEGER, coxa_direita INTEGER,genero VARCHAR(15), data DATE,FOREIGN KEY(id_aluno) REFERENCES alunos(id));";
        db.execSQL(sqlCreateAvaliacao);
        String sqlCreateExercicios = "create table exercicios (id INTEGER PRIMARY KEY AUTOINCREMENT,nome, grupo_muscular);";
        db.execSQL(sqlCreateExercicios);
        String insertExercicios = "INSERT INTO exercicios (nome, grupo_muscular) VALUES ('Supino reto com barra', 'Peitoral'),('Supino inclinado com halteres', 'Peitoral'),('Flexão de braços (push-up)', 'Peitoral'),('Crucifixo inclinado com halteres', 'Peitoral'),('Fly em máquina', 'Peitoral'),('Pullover com halteres', 'Peitoral'),('Barra fixa (pull-up)', 'Costas'),('Remada com barra', 'Costas'),('Remada com halteres', 'Costas'),('Puxada alta com corda', 'Costas'),('Levantamento terra (deadlift)', 'Costas'),('Hiperextensão', 'Costas'),('Desenvolvimento com barra', 'Ombros'),('Desenvolvimento com halteres', 'Ombros'),('Elevação lateral com halteres', 'Ombros'),('Elevação frontal com halteres', 'Ombros'),('Remada alta com barra', 'Ombros'),('Crucifixo invertido com halteres', 'Ombros'),('Rosca direta com barra', 'Bíceps'),('Rosca direta com halteres', 'Bíceps'),('Rosca alternada com halteres', 'Bíceps'),('Rosca martelo com halteres', 'Bíceps'),('Rosca scott com barra', 'Bíceps'),('Rosca concentrada com halteres', 'Bíceps'),('Paralelas', 'Tríceps'),('Tríceps testa com barra', 'Tríceps'),('Tríceps testa com halteres', 'Tríceps'),('Tríceps coice com halteres', 'Tríceps'),('Tríceps pulley', 'Tríceps'),('Tríceps corda', 'Tríceps'),('Abdominal tradicional', 'Abdômen'),('Prancha abdominal', 'Abdômen'),('Elevação de pernas em suspensão', 'Abdômen'),('Abdominal na bola suíça', 'Abdômen'),('Prancha lateral', 'Abdômen'),('Tesoura abdominal', 'Abdômen'),('Agachamento com barra', 'Pernas'),('Agachamento com halteres', 'Pernas'),('Leg press', 'Pernas'),('Cadeira extensora', 'Pernas'),('Cadeira flexora', 'Pernas'),('Stiff', 'Pernas'),('Cálice (sumô) squat', 'Pernas'),('Avanço (lunge)', 'Pernas');";
        String sqlCreateTreinos = "create table treinos(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(25), repeticoes varchar(20), id_aluno integer , FOREIGN KEY(id_aluno) REFERENCES alunos(id));";
        db.execSQL(sqlCreateTreinos);
        db.execSQL(insertExercicios);
        String sqlCreateTreinoExercicios = "create table treino_exercicios(id INTEGER PRIMARY KEY AUTOINCREMENT,id_exercicio integer , id_treino integer,  FOREIGN KEY(id_exercicio) REFERENCES exercicios(id), FOREIGN KEY(id_treino) REFERENCES treinos(id));";
        db.execSQL(sqlCreateTreinoExercicios);
        String sqlCreateInsertTreinos = "INSERT INTO treinos(nome, repeticoes) VALUES('controle','0');";
        db.execSQL(sqlCreateInsertTreinos);
        String sqlCreateInsertTreinosExercicios = "INSERT INTO treino_exercicios (id_treino, id_exercicio) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43), (1,44);";
        db.execSQL(sqlCreateInsertTreinosExercicios);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists users ;";
        String sql1 = "drop table if exists alunos ;";
        String sql2 = "drop table if exists avaliacoes ;";
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        onCreate(db);

    }

    public boolean login(String user, String password){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users WHERE user=? AND password = ?", new String[] {user, password});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
}

