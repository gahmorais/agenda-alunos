package br.com.gabrielmorais.agendaaluno.dao;/*
 **
 **
 **  Create by
 **  gabrielmorais
 **  AgendaAluno
 **  12/12/2020
 **
 **
 */

import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.gabrielmorais.agendaaluno.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        atualizaIds();
    }

    public void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {

        Aluno alunoEncontrado = buscaAlunoPeloId( aluno );

        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);

        }
    }

    public Aluno buscaAlunoPeloId(Aluno aluno) {

        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {

        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if (alunoDevolvido != null ) {
            alunos.remove(alunoDevolvido);

        }
    }
}
