package br.com.gabrielmorais.agendaaluno;/*
 **
 **
 **  Create by
 **  gabrielmorais
 **  AgendaAluno
 **  09/12/2020
 **
 **
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSmoothScroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> aluno = new ArrayList<>( Arrays.asList( "Alex", "Gabriel", "Ana" ) );
        ListView listaDeAlunos = findViewById(R.id.activity_main_lista_de_alunos);

        listaDeAlunos.setAdapter(new ArrayAdapter<>( this , android.R.layout.simple_list_item_1 , aluno ) );

    }
}