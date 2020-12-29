package br.com.gabrielmorais.agendaaluno.ui.activity;/*
 **
 **
 **  Created by
 **  gabrielmorais
 **  AgendaAluno
 **  29/12/2020
 **
 **
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.gabrielmorais.agendaaluno.R;
import br.com.gabrielmorais.agendaaluno.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewCriada = criaView(parent);
        Aluno alunoDevolvido = alunos.get( position );
        vincula(viewCriada, alunoDevolvido);

        return viewCriada;
    }

    public void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        TextView telefone = view.findViewById( R.id.item_aluno_telefone );

        nome.setText( aluno.getNome() );
        telefone.setText( aluno.getTelefone() );
    }

    public View criaView(ViewGroup parent) {
        return LayoutInflater
                .from( context )
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void atualiza( List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll( alunos );
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove( aluno );
        notifyDataSetChanged();
    }

}

