package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import esperto.treino.Model.Treino;
import esperto.treino.Model.TreinoExercicio;

public class Add_Exercicio extends AppCompatActivity {
    ListView treinos_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercicio);
        Intent it = getIntent();
        String id_treino = it.getStringExtra("id");
        treinos_list = findViewById(R.id.list_treinos_detalhar1);
        TreinoExercicio treinoExercicio = new TreinoExercicio(Add_Exercicio.this);
        Cursor cursor = treinoExercicio.listarTreinoExercicioNaoListados(id_treino);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Add_Exercicio.this,
                R.layout.listar_exercicios,
                cursor,
                new String[]{"nome", "grupo_muscular" },
                new int[]{R.id.nome_treino_detalhar_list, R.id.treino_repeticao},0);
        treinos_list.setAdapter(adapter);

        treinos_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Add_Exercicio.this, id+"", Toast.LENGTH_SHORT).show();
                int posicaoSelecionada = position;
                int id_treino_int = Integer.parseInt(id_treino);
                TreinoExercicio treinoExercicio = new TreinoExercicio(
                        null,
                        posicaoSelecionada + 1,
                        id_treino_int,
                        Add_Exercicio.this
                );
                if(treinoExercicio.criarTreinoExercicio()){
                    Intent main =
                            new Intent(Add_Exercicio.this, Aluno_Index.class);
                    startActivity(main);
                }
            }
        });
    }
}