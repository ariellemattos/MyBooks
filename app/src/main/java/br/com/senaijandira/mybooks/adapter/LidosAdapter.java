package br.com.senaijandira.mybooks.adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.EditarActivity;
import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class LidosAdapter  extends ArrayAdapter<Livro> {

    //Banco de dados
    private MyBooksDataBase myBooksDb;

    public LidosAdapter(Context ctx, MyBooksDataBase myBooksDb ){
        super(ctx, 0, new ArrayList<Livro>());
        this.myBooksDb = myBooksDb;
    }

    private void deletarLivro(Livro livro){

        Livro deletarLivro = new Livro(livro.getId(), livro.getCapa(), livro.getTitulo(), livro.getDescricao(), 0);

        myBooksDb.daoLivro().atualizar(deletarLivro);

        clear();

        Livro[] livros = myBooksDb.daoLivro().SelecionarLivrosLidos();

        addAll(livros);

        Toast.makeText(getContext(), "Livro removido da lista", Toast.LENGTH_SHORT).show();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.livro_layout_lidos, parent, false);
        }

        final Livro livro = getItem(position);

        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);

        ImageView imgDeleteLivro = v.findViewById(
                R.id.imgDeleteLivro);

        imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarLivro(livro);
                Toast.makeText(getContext(), "Livro removido da lista", Toast.LENGTH_SHORT).show();
            }
        });



        //Setando a imagem
        imgLivroCapa.setImageBitmap(
                Utils.toBitmap(livro.getCapa()) );

        //Setando o titulo do livro
        txtLivroTitulo.setText(livro.getTitulo());

        //Setando a descrição do livro
        txtLivroDescricao.setText(livro.getDescricao());




        return v;
    }




}
