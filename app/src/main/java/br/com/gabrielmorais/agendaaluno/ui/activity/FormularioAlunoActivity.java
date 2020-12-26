package br.com.gabrielmorais.agendaaluno.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.gabrielmorais.agendaaluno.R;
import br.com.gabrielmorais.agendaaluno.dao.AlunoDAO;
import br.com.gabrielmorais.agendaaluno.model.Aluno;

import static br.com.gabrielmorais.agendaaluno.ui.activity.ConstantesActrivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoemail;
    private Aluno aluno;
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR_NOVO_ALUNO);
        inicializacaoDosCampos();
        carregaAluno();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.actyivity_formulario_aluno_menu, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if ( itemId == R.id.activity_formulario_aluno_salvar ) {

            finalizaFormulario();

        }

        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();

        if ( dados.hasExtra( CHAVE_ALUNO ) ) {

            setTitle( TITULO_APPBAR_EDITA_ALUNO );
            aluno = (Aluno) dados.getSerializableExtra( CHAVE_ALUNO );
            preencheCampos();

        } else {

            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoemail.setText(aluno.getEmail());
    }

    public void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()){

            dao.edita( aluno );

        } else {

            dao.salva( aluno );

        }

        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome       = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone   = findViewById(R.id.activity_formulario_aluno_telefone);
        campoemail      = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome         = campoNome.getText().toString();
        String telefone     = campoTelefone.getText().toString();
        String email        = campoemail.getText().toString();

        aluno.setNome( nome );
        aluno.setTelefone( telefone );
        aluno.setEmail( email );
    }
}