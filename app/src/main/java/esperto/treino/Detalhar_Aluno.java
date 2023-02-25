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
import android.widget.TextView;
import android.widget.Toast;

import esperto.treino.Model.Aluno;
import esperto.treino.Model.Avaliacao;
import esperto.treino.Model.Treino;

public class Detalhar_Aluno extends AppCompatActivity {

    Button criar_ficha, criar_treino;
    TextView nome_aluno;
    ListView list_avaliacao;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_aluno);
        Intent it = getIntent();
        String id = it.getStringExtra("id");

        Aluno aluno = new Aluno(Detalhar_Aluno.this);
        Cursor cursor =aluno.detalhar(id);
        criar_ficha = findViewById(R.id.btn_add_avaliacao_aluno);
        nome_aluno = findViewById(R.id.nome_aluno_detalhar);
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        nome_aluno.setText(nome);


        list_avaliacao = findViewById(R.id.avaliacoes_list);
        Avaliacao avaliacao = new Avaliacao(Detalhar_Aluno.this);
        Cursor cursor1 = avaliacao.listarAvaliacao(id);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Detalhar_Aluno.this,
                R.layout.listar_avaliacao,
                cursor1,
                new String[]{"data", "peso", "idade"},
                new int[]{R.id.data, R.id.peso, R.id.idade},0);

        list_avaliacao.setAdapter(adapter);

        list_avaliacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Detalhar_Aluno.this, id+"", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(Detalhar_Aluno.this, Detalhar_Ficha.class);
                it.putExtra("id", id+"");
                startActivity(it);

            }
        });

        ListView list_treino = findViewById(R.id.treinos_list);
        Treino treino = new Treino(Detalhar_Aluno.this);
        Cursor cursor2 = treino.listarTreino(id);

        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(
                Detalhar_Aluno.this,
                R.layout.listar_treino,
                cursor2,
                new String[]{"nome", "repeticoes"},
                new int[]{R.id.nome_treino_detalhar_list, R.id.treino_repeticao},
                0);

        list_treino.setAdapter(adapter2);

        list_treino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Detalhar_Aluno.this, id+"", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(Detalhar_Aluno.this, Detatalhar_Treino.class);
                it.putExtra("id", id+"");
                startActivity(it);
            }
        });



        criar_treino = findViewById(R.id.add_treino_aluno);

        criar_ficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detalhar_Aluno.this, Criar_Avaliacao.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        criar_treino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detalhar_Aluno.this, Criar_Treino.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


    }
}