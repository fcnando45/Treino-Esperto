package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import esperto.treino.Model.Aluno;

public class Aluno_Index extends AppCompatActivity {
    Button cadastro_alunos, criar_treino;
    ListView list_alunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_index);

        cadastro_alunos = findViewById(R.id.add_alunos);

        list_alunos = findViewById(R.id.list_alunos);
        Aluno aluno = new Aluno(Aluno_Index.this);
        Cursor cursor =aluno.listar();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Aluno_Index.this,
                R.layout.listar_aluno,
                cursor,
                new String[]{"_id", "nome"},
                new int[]{R.id.data, R.id.peso},0);

        list_alunos.setAdapter(adapter);

        list_alunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Aluno_Index.this, id+"", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(Aluno_Index.this, Detalhar_Aluno.class);
                it.putExtra("id", id+"");
                startActivity(it);

            }
        });


        cadastro_alunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aluno_Index.this, Cadastrar_Alunos.class);
                startActivity(intent);
            }
        });


    }


}