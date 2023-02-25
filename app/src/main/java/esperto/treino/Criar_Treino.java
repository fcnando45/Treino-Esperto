package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import esperto.treino.Model.Aluno;
import esperto.treino.Model.Treino;

public class Criar_Treino extends AppCompatActivity {

    EditText campo_nome, campo_repeticao;
    Button cadastrar_treino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_treino);

        Intent it = getIntent();
        String id = it.getStringExtra("id");
        cadastrar_treino = findViewById(R.id.btn_cadastrar_treino);



        cadastrar_treino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                campo_nome = findViewById(R.id.campo_nome_treino);
                campo_repeticao = findViewById(R.id.campo_nome_repeticoes_treino);
                String nome = campo_nome.getText().toString();
                String repeticao = campo_repeticao.getText().toString();

                int id_aluno = Integer.parseInt(id);


                Treino treino = new Treino(
                        null,
                        nome,
                        repeticao,
                        id_aluno,
                        Criar_Treino.this
                );

                if(treino.criarTreino()){
                    Intent main =
                            new Intent(Criar_Treino.this, Aluno_Index.class);
                    startActivity(main);
                }


        }
    });





    }
}