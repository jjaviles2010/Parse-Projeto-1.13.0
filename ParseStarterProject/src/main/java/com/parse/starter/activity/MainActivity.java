/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.adapter.TabsAdapter;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.util.SlidingTabLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar_princ;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configurar toolbar
        toolbar_princ = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_principal);
        //toolbar_princ.setLogo(R.drawable.instagramlogo);
        setSupportActionBar(toolbar_princ);

        //Configurar Tabs
        viewPager = (ViewPager)findViewById(R.id.view_pager_main);
        tabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tab_main);

        //Configurar adapter
        TabsAdapter tabsAdapter = new TabsAdapter( getSupportFragmentManager(),this );
        viewPager.setAdapter( tabsAdapter );


        //Configurando Layout Custom
       tabLayout.setCustomTabView(R.layout.tab_layout, R.id.text_tab);
         tabLayout.setDistributeEvenly(true);
        tabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.cinzaEscuro));
        tabLayout.setViewPager(viewPager);

        /********************************/
        /*Cadastro de Usuarios
         */
   /* ParseUser usuario = new ParseUser();
    usuario.setUsername("josejavier");
    usuario.setPassword("12345");
    usuario.setEmail("josejavier@gmail.com");

    //Cadastrar usuario
    usuario.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){//deu cert
            Log.i("cadastroUsuario", "Sucesso ao cadastrar Usuario");

        }else {//erro
            Log.i("cadastroUsuario", "Erro ao cadastrar Usuario - " + e.getMessage());
        }
      }
    });*/

        /**************
         * Verificar usuario logado
         */

      /*if(ParseUser.getCurrentUser() != null){//logado
        Log.i("LoginUsuario","Usuario esta logado");
      }else{//Nao esta logado
          Log.i("LoginUsuario","Usuario nao esta logado");
      }*/

        //Deslogar Usuario
        //ParseUser.logOut();

        /************
         * Fazer o login do usuario
         */
        /*ParseUser.logInInBackground("josejavier", "12345", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){//deu certo
                    Log.i("verificaLoginUsuario","Login realizado com sucesso");
                }else {//nao deu certo
                    Log.i("verificaLoginUsuario","Erro ao fazer Login do usuario. "+ e.getMessage());
                }
            }
        });*/


        //ParseAnalytics.trackAppOpenedInBackground(getIntent());

      /*ParseObject pontuacao = new ParseObject("Pontuacao");
      pontuacao.put("nome","Leticia");
      pontuacao.put("pontos",800);

      pontuacao.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if(e == null){//nao temos erro, pois o objeto esta nulo
                    Log.i("salvarPontos","Dados salvos com sucesso");
              }else {
                    Log.i("salvarPontos","Erro ao salvar os dados");
              }
          }
      });

      ParseQuery<ParseObject> consulta = ParseQuery.getQuery("Pontuacao");
      consulta.getInBackground("j2wlR7pYJF", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
              if(e == null){//nao temos erro, pois o objeto esta nulo
                  object.put("pontos", 700);
                  object.saveInBackground();
                  Log.i("consultaObjeto","Dados salvos com sucesso");
              }else {
                  Log.i("consultaObjeto","Erro ao salvar objeto");
              }
          }
      });

      ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Pontuacao");

      //Aplicando filtro na listagem de objetos
      //filtro.whereGreaterThan("pontos",700);
      filtro.whereGreaterThanOrEqualTo("pontos",800);

      //Listar os dados
      filtro.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
              if(e==null){//efetuada listagem sem erro
                 // Log.i("ListarDatos","Sucesso ao listar os obejetos " + objects.size());

                  for (ParseObject object: objects) {
                      Log.i("ListarDatos","obejetos - Nome : " + object.get("nome") + " ponto: " + object.get("pontos"));
                  }

              }else {//Erro ao listar
                  Log.i("ListarDatos","Erro ao Listar os objeto " + e.getMessage());
              }
          }
      });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sair:
                deslogarUsuario();
            case R.id.action_settings:
                return true;
            case R.id.action_compartilhar:
                compartilharFoto();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void deslogarUsuario(){
        ParseUser.logOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void compartilharFoto(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Testar processo de retorno dos dados
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            //recupera o local do recurso
            Uri localImagemSelecionada = data.getData();

            //recupera a imagem do local que foi selecionada
            try {
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),localImagemSelecionada);

                //comprimir no formate PNG
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);

                //Cria Array de Bytes da imagem
                byte[] arrayBytesImage = stream.toByteArray();

                //Criar arquivo no formato prorio do parse
                Locale locale = new Locale("pt-Br");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("aaaammddhhmmss",locale);
                String nomeImagem = simpleDateFormat.format(new Date());
                ParseFile arquivoParse = new ParseFile(nomeImagem + "imagem.png", arrayBytesImage);

                //Monta objeto para salvar no parse
                ParseObject parseObject = new ParseObject("Imagem");
                parseObject.put("username",ParseUser.getCurrentUser().getUsername());
                parseObject.put("imagem", arquivoParse);

                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){//sucesso ao salvar imagem
                            Toast.makeText(MainActivity.this,"Imagem salva com sucesso!",Toast.LENGTH_LONG).show();

                            //Actualiza a lista de imagens do HomoFragment
                            TabsAdapter adapterNovo = (TabsAdapter) viewPager.getAdapter();
                            HomeFragment homeFragmentNovo = (HomeFragment)adapterNovo.getFragment( 0 );
                            homeFragmentNovo.atualizaPostagens();


                        }else {//erro ao salvar imagem
                            Toast.makeText(MainActivity.this,"Erro ao salvar imagem, tente novamente!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
