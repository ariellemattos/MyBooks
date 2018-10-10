package br.com.senaijandira.mybooks.adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.senaijandira.mybooks.R;
import br.com.senaijandira.mybooks.Utils;
import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.model.Livro;

public class LidosAdapter  extends ArrayAdapter<Livro> {
    private MyBooksDataBase myBooksDb;
    ListView lstViewLivros;

    LidosAdapter adapter;

    public LidosAdapter(Context context){

        super(context, 0, new ArrayList<Livro>());

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        myBooksDb = Room.databaseBuilder(getContext(), MyBooksDataBase.class, Utils.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        if (v == null){

            v = LayoutInflater.from(getContext()).inflate(R.layout.livro_layout, parent, false);

        }

        lstViewLivros = v.findViewById(R.id.lstViewLivros);

        //Criar o adapter
        adapter = new LidosAdapter(getContext());


        lstViewLivros.setAdapter(adapter);

        return v;
    }




}
