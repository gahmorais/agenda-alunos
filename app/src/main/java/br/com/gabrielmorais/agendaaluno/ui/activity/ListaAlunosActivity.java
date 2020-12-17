package br.com.gabrielmorais.agendaaluno.ui.activity;

/*
 **
 **
 **  Created by
 **  gabrielmorais
 **  AgendaAluno
 **  09/12/2020
 **
 **
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.gabrielmorais.agendaaluno.R;
import br.com.gabrielmorais.agendaaluno.dao.AlunoDAO;
import br.com.gabrielmorais.agendaaluno.model.Aluno;

import static br.com.gabrielmorais.agendaaluno.ui.activity.ConstantesActrivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle( TITULO_APPBAR );
        configurFabNovoAluno();

    }


    private void configurFabNovoAluno() {

        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAluno();
            }
        });

    }

    private void abreFormularioModoInsereAluno() {
        startActivity( new Intent( ListaAlunosActivity.this, FormularioAlunoActivity.class ) );
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        final List<Aluno> alunos = dao.todos();
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem( listaDeAlunos);

        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Clique Longo", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    

    private void configuraListenerDeCliquePorItem( ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition( position );
                abreFormularioModoEditaAluno( alunoEscolhido );
            }
        });
    }

    public void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno );
        startActivity( vaiParaFormularioActivity );
    }

    private void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>( this , android.R.layout.simple_list_item_1 , dao.todos() ) );
    }
}
