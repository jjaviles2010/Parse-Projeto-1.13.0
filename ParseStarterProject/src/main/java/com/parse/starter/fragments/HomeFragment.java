package com.parse.starter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ListView listPostagems;
    private ArrayList<ParseObject> postagems;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> query;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*
        * Montar ListView e adapter
        * */
        postagems = new ArrayList<>();
        listPostagems = (ListView)view.findViewById(R.id.list_postagens_home);
        adapter = new HomeAdapter(getActivity(),postagems);
        listPostagems.setAdapter(adapter);

        //Recuperar Imagens postadas
        getPostagens();

        return view;
    }

    private void getPostagens(){

        /*
        * Recuperar imagens das postagens
        * */
        query = ParseQuery.getQuery("Imagem");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e == null){//Sucesso

                    if(objects.size() > 0){

                        postagems.clear();
                        for(ParseObject parseObject : objects){
                            postagems.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();

                    }

                }else {//Error

                    e.printStackTrace();

                }
            }
        });

    }

}
