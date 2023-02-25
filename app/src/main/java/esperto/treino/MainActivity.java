package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import esperto.treino.Helper.DbHelper;
import esperto.treino.Model.User;

public class MainActivity extends AppCompatActivity {

    private Button btn_cadastro, btn_entrar;
    EditText campo_user_logar;
    EditText campo_password_logar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DbHelper myDB = new DbHelper(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_cadastro = findViewById(R.id.btn_cadastro);
        btn_entrar = findViewById(R.id.btn_entrar);
        campo_user_logar = findViewById(R.id.campo_login);
        campo_password_logar = findViewById(R.id.campo_senha);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campo_user_logar.getText().toString().isEmpty()){
                    campo_user_logar.setError("Campo obrigatório!");
                }else if(campo_password_logar.getText().toString().isEmpty()) {
                    campo_password_logar.setError("Campo obrigatório!");
                }
                Boolean login = myDB.login(campo_user_logar.getText().toString(),
                        campo_password_logar.getText().toString());
                if(login){
                    SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor myEditor = myPreferences.edit();
                    myEditor.putString("USER", campo_user_logar.getText().toString());
                    myEditor.commit();


                    Intent it = new Intent(MainActivity.this, Menu.class);
                    it.putExtra("user", campo_user_logar.getText().toString());
                    startActivity(it);
                }else{
                    Toast.makeText(MainActivity.this, "Login incorreto", Toast.LENGTH_LONG).show();
                }

            }


        });

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastrar_User.class);
                startActivity(intent);
            }
        });


    }
    }