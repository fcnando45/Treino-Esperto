package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import esperto.treino.Model.Aluno;
import esperto.treino.Model.Avaliacao;

public class Criar_Avaliacao extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView nome_aluno;
    Button criar_ficha;
    EditText campo_altura, campo_peso, campo_idade, campo_ombro, campo_peito, campo_braco_esquerdo, campo_braco_direito, campo_cintura, campo_quadril, campo_coxa_esquerda, campo_coxa_direita, campo_genero;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_avaliacao);
        Spinner spinner = findViewById(R.id.campo_genero_avaliacao);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Intent it = getIntent();
        String id = it.getStringExtra("id");
        campo_altura = findViewById(R.id.campo_altura_avaliacao);
        Aluno aluno = new Aluno(Criar_Avaliacao.this);
        Cursor cursor =aluno.detalhar(id);
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        nome_aluno = findViewById(R.id.nome_aluno_avaliacao);
        nome_aluno.setText(nome);

        nome_aluno = findViewById(R.id.nome_aluno_avaliacao);
        campo_peso = findViewById(R.id.campo_peso_avaliacao);
        campo_idade = findViewById(R.id.campo_idade_avaliacao);
        campo_ombro = findViewById(R.id.campo_ombro_avaliacao);
        campo_peito = findViewById(R.id.campo_peito_avaliacao);
        campo_braco_esquerdo = findViewById(R.id.campo_braco_esquedo_avaliacao);
        campo_braco_direito = findViewById(R.id.campo_braco_direito_avaliacao);
        campo_cintura = findViewById(R.id.campo_cintura_avaliacao);
        campo_quadril = findViewById(R.id.campo_quadril_avaliacao);
        campo_coxa_esquerda = findViewById(R.id.campo_coxa_esquerda_avaliacao);
        campo_coxa_direita = findViewById(R.id.campo_coxa_direita_avaliacao);
        criar_ficha = findViewById(R.id.btn_criar_avaliacao);



        criar_ficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ação ao clicar no botão
                String altura = campo_altura.getText().toString();
                int alturaInt = Integer.parseInt(altura);

                String peso = campo_peso.getText().toString();
                float pesoFloat = Float.parseFloat(peso);

                String idade = campo_idade.getText().toString();
                int idadeInt = Integer.parseInt(idade);

                String ombro = campo_ombro.getText().toString();
                int ombroInt = Integer.parseInt(ombro);

                String peito = campo_peito.getText().toString();
                int peitoInt = Integer.parseInt(peito);

                String braco_esquerdo = campo_braco_esquerdo.getText().toString();
                int bracoEsquerdoInt = Integer.parseInt(braco_esquerdo);

                String braco_direito = campo_braco_direito.getText().toString();
                int bracoDireitoInt = Integer.parseInt(braco_direito);

                String cintura = campo_cintura.getText().toString();
                int cinturaInt = Integer.parseInt(cintura);

                String quadril = campo_quadril.getText().toString();
                int quadrilInt = Integer.parseInt(quadril);

                String coxa_esquerda = campo_coxa_esquerda.getText().toString();
                int coxaEsquerdaInt = Integer.parseInt(coxa_esquerda);

                String coxa_direita = campo_coxa_direita.getText().toString();
                int coxaDireitaInt = Integer.parseInt(coxa_direita);


                int idInt = Integer.parseInt(id);


                LocalDate hoje = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    hoje = LocalDate.now();
                }
                Date dataHoje = java.sql.Date.valueOf(String.valueOf(hoje));

                Avaliacao avaliacao = new Avaliacao(
                        null,
                        idInt,
                        alturaInt,
                        pesoFloat,
                        idadeInt,
                        ombroInt,
                        peitoInt,
                        bracoEsquerdoInt,
                        bracoDireitoInt,
                        cinturaInt,
                        quadrilInt,
                        coxaEsquerdaInt,
                        coxaDireitaInt,
                        "Masculino",
                        dataHoje,
                        Criar_Avaliacao.this
                        );

                if(avaliacao.criarAvaliacao()){
                    Intent main =
                            new Intent(Criar_Avaliacao.this, Aluno_Index.class);
                    startActivity(main);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

