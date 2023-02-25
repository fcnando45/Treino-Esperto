package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import esperto.treino.Model.Avaliacao;

public class Detalhar_Ficha extends AppCompatActivity {

    TextView peso_t, altura_t, idade_t, ombro_t, peito_t, braco_e_t,braco_d_t, cintura_t, quadril_t, coxa_e_t, coxa_d_t, data_t, imc_t ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_ficha);
        Intent it = getIntent();
        String id = it.getStringExtra("id");
        int idInt = Integer.parseInt(id);
        Avaliacao avaliacao = new Avaliacao(Detalhar_Ficha.this);
        Cursor cursor = avaliacao.detalharAvaliacao(id);
        peso_t = findViewById(R.id.peso_avaliacao_detalhar);
        altura_t = findViewById(R.id.altura_peso_detalhar);
        idade_t = findViewById(R.id.idade_detalhar);
        ombro_t = findViewById(R.id.ombro_detalhar);
        peito_t = findViewById(R.id.peito_detalhar);
        braco_e_t = findViewById(R.id.braco_esquerdo_detalhar);
        braco_d_t = findViewById(R.id.braco_direito_detalhar);
        cintura_t = findViewById(R.id.cintura_detalhar);
        quadril_t = findViewById(R.id.quadril_detalhar);
        coxa_e_t = findViewById(R.id.coxa_esquerda_detalhar);
        coxa_d_t = findViewById(R.id.coxa_direita_detalhar);
        data_t = findViewById(R.id.nome_detalhar_ficha);
        imc_t = findViewById(R.id.imc_detalhar);

        String peso = cursor.getString(cursor.getColumnIndex("peso"));
        String altura = cursor.getString(cursor.getColumnIndex("altura"));
        String idade = cursor.getString(cursor.getColumnIndex("idade"));
        String ombro = cursor.getString(cursor.getColumnIndex("ombro"));
        String peito = cursor.getString(cursor.getColumnIndex("peito"));
        String braco_esquerdo = cursor.getString(cursor.getColumnIndex("braco_esquerdo"));
        String braco_direito = cursor.getString(cursor.getColumnIndex("braco_direito"));
        String cintura = cursor.getString(cursor.getColumnIndex("cintura"));
        String quadril = cursor.getString(cursor.getColumnIndex("quadril"));
        String coxa_esquerda = cursor.getString(cursor.getColumnIndex("coxa_esquerda"));
        String coxa_direita = cursor.getString(cursor.getColumnIndex("coxa_direita"));
        String data = cursor.getString(cursor.getColumnIndex("data"));

        data_t.setText(data);
        altura_t.setText(altura + " cm");
        peso_t.setText(peso + " Kg");
        ombro_t.setText(ombro +"cm");
        idade_t.setText(idade + " anos");
        ombro_t.setText(ombro + " cm");
        peito_t.setText(peito + " cm");
        braco_e_t.setText(braco_esquerdo + " cm");
        braco_d_t.setText(braco_direito + " cm");
        cintura_t.setText(cintura + " cm");
        quadril_t.setText(quadril + " cm");
        coxa_e_t.setText(coxa_esquerda + " cm");
        coxa_d_t.setText(coxa_direita + " cm");
        int alturaInt = Integer.parseInt(altura);
        float pesoFloat = Float.parseFloat(peso);
        String imc = avaliacao.imc(alturaInt, pesoFloat);
        imc_t.setText(imc);
    }
}