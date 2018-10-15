package br.com.senaijandira.mybooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.EditarActivity;
import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

/**
 * Created by sn1041520 on 01/10/2018.
 */

public class LivroAdapter extends ArrayAdapter<Livro> {


    //Banco de dados
    private MyBooksDataBase myBooksDb;

    public LivroAdapter(Context ctx, MyBooksDataBase myBooksDb ){
        super(ctx, 0, new ArrayList<Livro>());

        this.myBooksDb = myBooksDb;
    }


    private void deletarLivro(Livro livro){

        //Remover do banco de dados
        myBooksDb.daoLivro().deletar(livro);

        //remover livro da lista
        remove(livro);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.livro_layout,
                            parent, false);
        }

        final Livro livro = getItem(position);

        ImageView imgLivroCapa = v.findViewById(R.id.imgLivroCapa);
        TextView txtLivroTitulo = v.findViewById(R.id.txtLivroTitulo);
        TextView txtLivroDescricao = v.findViewById(R.id.txtLivroDescricao);

        ImageView imgEditarLivro = v.findViewById(R.id.imgEditarLivro);

        imgEditarLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditarActivity.class);
                intent.putExtra("id_livro", livro.getId());

                getContext().startActivity(intent);
            }
        });

        ImageView imgDeleteLivro = v.findViewById(
                R.id.imgDeleteLivro);

        imgDeleteLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarLivro(livro);
                Toast.makeText(getContext(), "Livro removido da lista", Toast.LENGTH_SHORT).show();
            }
        });


//        Onclick do icone de livros para ler
        ImageView imglivroler = v.findViewById(R.id.imglivroler);

        imglivroler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro livroLido = new Livro(livro.getId(), livro.getCapa(), livro.getTitulo(), livro.getDescricao(), 2);

                myBooksDb.daoLivro().atualizar(livroLido);

                clear();

                Livro[] livros = myBooksDb.daoLivro().selecionarTodos();

                addAll(livros);

                if (livro.getStatus() != 1)
                    Toast.makeText(getContext(), "Livro adicionado na lista de livros para ler", Toast.LENGTH_SHORT).show();
            }
        });



        //Onclick de livros lidos
      ImageView imglivrolido = v.findViewById(R.id.imglivrolido);

        imglivrolido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro livroLido = new Livro(livro.getId(), livro.getCapa(), livro.getTitulo(), livro.getDescricao(), 1);

                myBooksDb.daoLivro().atualizar(livroLido);

                clear();

                Livro[] livros = myBooksDb.daoLivro().selecionarTodos();

                addAll(livros);

                if (livro.getStatus() != 2)
                    Toast.makeText(getContext(), "Livro adicionado na lista de livros lidos", Toast.LENGTH_SHORT).show();
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
