package br.com.senaijandira.mybooks;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import br.com.senaijandira.mybooks.adapter.LivroAdapter;

import br.com.senaijandira.mybooks.db.MyBooksDataBase;
import br.com.senaijandira.mybooks.fragments.Inicio;
import br.com.senaijandira.mybooks.model.Livro;

public class MainActivity extends AppCompatActivity {

    FragmentManager tela;

    TabLayout tab_menu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tela = getSupportFragmentManager();

        tab_menu = findViewById(R.id.tab_menu);

        tab_menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    abrirInicio(null);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void abrirInicio (View view){
        FragmentTransaction ft = tela.beginTransaction();

        ft.replace(R.id.frame_layout, new Inicio());

        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //abrir a tela de inicio como padrão
        abrirInicio(null);
    }




    public void abrirCadastro(View v){
        startActivity(new Intent(this,
                CadastroActivity.class));
    }

}
