package br.com.gabrielmorais.agendaaluno.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.gabrielmorais.agendaaluno.R;
import br.com.gabrielmorais.agendaaluno.dao.AlunoDAO;
import br.com.gabrielmorais.agendaaluno.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoemail;
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle( TITULO_APPBAR );
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botao_salvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botao_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno alunocriado = criaAluno();
                salva( alunocriado );

            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome       = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone   = findViewById(R.id.activity_formulario_aluno_telefone);
        campoemail      = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno aluno) {
        dao.salva( aluno );

        finish();
    }

    private Aluno criaAluno() {
        String nome         = campoNome.getText().toString();
        String telefone     = campoTelefone.getText().toString();
        String email        = campoemail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}