package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import esperto.treino.Model.Aluno;

public class Cadastrar_Alunos extends AppCompatActivity {
    EditText campo_nome, campo_senha, campo_endereco;
    Button btn_cadastrar_alunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_alunos);
        campo_nome =  findViewById(R.id.campo_nome_treino);
        campo_senha = findViewById(R.id.campo_nome_repeticoes_treino);
        campo_endereco = findViewById(R.id.campo_cadastar_aluno_endereco);
        btn_cadastrar_alunos = findViewById(R.id.btn_cadastrar_treino);

        btn_cadastrar_alunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campo_nome.getText().toString().equals("")){
                    campo_nome.setError("Campos obrigatório");
                }
                if(campo_senha.getText().toString().equals("")){
                    campo_senha.setError("Campos obrigatório");
                }
                if(campo_endereco.getText().toString().equals("")){
                    campo_endereco.setError("Campos obrigatório");
                }
                Aluno aluno = new Aluno(
                        null,
                        campo_nome.getText().toString(),
                        campo_senha.getText().toString(),
                        campo_endereco.getText().toString(),
                        Cadastrar_Alunos.this
                );
                if(aluno.inserir()){
                    Intent main =
                            new Intent(Cadastrar_Alunos.this, Aluno_Index.class);
                    startActivity(main);
                }


            }


        });
    }
}
