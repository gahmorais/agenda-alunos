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

    public void salva( Aluno aluno ) {

        alunos.add( aluno );
    }

    public List<Aluno> todos() {

        return new ArrayList<>( alunos );
    }
}
