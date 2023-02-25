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

import esperto.treino.Model.TreinoExercicio;

public class Detatalhar_Treino extends AppCompatActivity {
    ListView treinos_list;
    Button btn_add_treino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detatalhar_treino);
        Intent it = getIntent();
        String id = it.getStringExtra("id");
        treinos_list = findViewById(R.id.list_treinos_detalhar);

        TreinoExercicio treinoExercicio = new TreinoExercicio(Detatalhar_Treino.this);
        Cursor cursor = treinoExercicio.listarTreinoExercicio(id);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Detatalhar_Treino.this,
                R.layout.listar_exercicios,
                cursor,
                new String[]{"nome", "grupo_muscular"},
                new int[]{R.id.nome_treino_detalhar_list, R.id.treino_repeticao},0);

        treinos_list.setAdapter(adapter);

        treinos_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

        btn_add_treino = findViewById(R.id.add_treinos_detalhar);

        btn_add_treino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detatalhar_Treino.this, Add_Exercicio.class);
                startActivity(intent);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }
}