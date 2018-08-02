/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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

      if(ParseUser.getCurrentUser() != null){//logado
        Log.i("LoginUsuario","Usuario esta logado");
      }else{//Nao esta logado
          Log.i("LoginUsuario","Usuario nao esta logado");
      }

      //Deslogar Usuario
      //ParseUser.logOut();

      /************
       * Fazer o login do usuario
       */
        ParseUser.logInInBackground("josejavier", "12345", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){//deu certo
                    Log.i("verificaLoginUsuario","Login realizado com sucesso");
                }else {//nao deu certo
                    Log.i("verificaLoginUsuario","Erro ao fazer Login do usuario. "+ e.getMessage());
                }
            }
        });


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

}