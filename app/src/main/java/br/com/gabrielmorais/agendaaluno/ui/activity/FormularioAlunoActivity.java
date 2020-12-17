package br.com.gabrielmorais.agendaaluno.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        configuraBotaoSalvar();
        carregaAluno();

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

    private void configuraBotaoSalvar() {
        Button botao_salvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botao_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preencheAluno();
                finalizaFormulario();

            }
        });
    }

    public void finalizaFormulario() {

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