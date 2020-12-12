package br.com.gabrielmorais.agendaaluno.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.gabrielmorais.agendaaluno.R;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        Button botao_salvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
    }
}