package com.example.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lista.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/*
        EdgeToEdge.enable(this);*/
        setContentView(R.layout.activity_new_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //detectar click
        ImageButton imgCI = findViewById(R.id.imbCI);
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cria e inicia intencao
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        //detectar outro click
        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifica se há dados inseridos
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;


                }


                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um titulo", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descricao", Toast.LENGTH_LONG).show();
                    return;

                }
                //devolve os dados recem adquiridos
                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title",title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });
    }



    @Override
    //ao finalizar atividade
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        //verifica erros
        if(requestCode == PHOTO_PICKER_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                //salva a uri da foto
                photoSelected =data.getData();
                ImageView imvfotoPreview =findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);

            }
        }
    }
}