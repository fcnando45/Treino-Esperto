package esperto.treino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import esperto.treino.Model.User;

public class Cadastrar_User extends AppCompatActivity {
    EditText campo_user, campo_password;
    Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_user);

        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        campo_password = findViewById(R.id.campo_senha_cadastrar);
        campo_user = findViewById(R.id.campo_login_cadastrar);


        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campo_password.getText().toString().equals("")){
                    campo_password.setError("Campos obrigatório");
                }
                if(campo_user.getText().toString().equals("")){
                    campo_user.setError("Campos obrigatório");
                }
                User user = new User(
                        null,
                        campo_user.getText().toString(),
                        campo_password.getText().toString(),Cadastrar_User.this
                        );
                if(user.inserir()){
                    Intent main =
                            new Intent(Cadastrar_User.this, MainActivity.class);
                    startActivity(main);

                }


                }


            });
    }
}