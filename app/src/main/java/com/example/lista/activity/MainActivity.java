package com.example.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista.R;
import com.example.lista.adapter.MyAdapter;
import com.example.lista.model.MainActivityViewModel;
import com.example.lista.model.MyItem;
import com.example.lista.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //cria inteiro
    static int NEW_ITEM_REQUEST = 1;
    //cria lista


    MyAdapter myAdapter;



    //poe os dados no elemento da lista
    @Override
    protected void  onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==NEW_ITEM_REQUEST){
            if(resultCode == Activity.RESULT_OK){

                MyItem myItem = new MyItem();
                myItem.title= data.getStringExtra("title");
                myItem.description=data.getStringExtra("description");
                Uri selectedPhotoURI= data.getData();

                try{
                    //mapeia a imagem
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
                    myItem.photo = photo;

                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }



                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItens();

                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //identifica recicle view
        RecyclerView rvItens = findViewById(R.id.rvItens);


        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens= vm.getItens();


        //cria my adapter
        myAdapter=new MyAdapter(this,itens);

        //atribui ao rvitens os itens de myAdapter
        rvItens.setAdapter(myAdapter);


        //configura rvitens
        rvItens.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);
        rvItens.addItemDecoration(dividerItemDecoration);
        /**/


        //detectar click
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        fabAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //cria e inicia intencao
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }

        });

    }
}