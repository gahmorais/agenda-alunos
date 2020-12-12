package br.com.gabrielmorais.agendaaluno.ui.activity;

/*
 **
 **
 **  Create by
 **  gabrielmorais
 **  AgendaAluno
 **  09/12/2020
 **
 **
 */

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.gabrielmorais.agendaaluno.R;

public class ListaAlunosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle("Lista de alunos");

        List<String> aluno = new ArrayList<>( Arrays.asList( "Alex", "Gabriel", "Ana" ) );
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);

        listaDeAlunos.setAdapter(new ArrayAdapter<>( this , android.R.layout.simple_list_item_1 , aluno ) );

    }
}
