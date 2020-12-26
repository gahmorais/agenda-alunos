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
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.gabrielmorais.agendaaluno.R;
import br.com.gabrielmorais.agendaaluno.dao.AlunoDAO;
import br.com.gabrielmorais.agendaaluno.model.Aluno;

import static br.com.gabrielmorais.agendaaluno.ui.activity.ConstantesActrivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";

    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle( TITULO_APPBAR );
        configurFabNovoAluno();
        configuraLista();
        for ( int i = 0; i< 10;i++ ) {
            dao.salva(new Aluno("Alex", "1122223333", "alex@alura.com.br"));
            dao.salva(new Aluno("Fran", "1122223333", "fran@alura.com.br"));
        }
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate( R.menu.activity_lista_alunos_menu , menu );

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if ( itemId == R.id.acitivty_lista_alunos_menu_remover ) {

            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void abreFormularioModoInsereAluno() {
        startActivity( new Intent( ListaAlunosActivity.this, FormularioAlunoActivity.class ) );
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();

    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll( dao.todos() );
    }

    private void configuraLista() {

        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter( listaDeAlunos );
        configuraListenerDeCliquePorItem( listaDeAlunos );
        registerForContextMenu( listaDeAlunos );

    }

    public void remove(Aluno alunoEscolhido) {
        dao.remove( alunoEscolhido );
        adapter.remove( alunoEscolhido );
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 );
        listaDeAlunos.setAdapter( adapter );
    }
}
