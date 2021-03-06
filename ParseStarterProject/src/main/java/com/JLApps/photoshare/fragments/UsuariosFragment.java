package com.JLApps.photoshare.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.JLApps.photoshare.R;
import com.JLApps.photoshare.activity.FeedUsuariosActivity;
import com.JLApps.photoshare.adapter.UsuariosAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosFragment extends Fragment {

    private ListView lstUsers;
    private ArrayList<ParseUser> usuarios;
    private ArrayAdapter<ParseUser> adapter;
    private ParseQuery<ParseUser> query;

    public UsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);

        /*
         * Montar ListView e Adapter
         * */
        usuarios = new ArrayList<>();
        lstUsers = (ListView) view.findViewById(R.id.lstViewUsers);
        adapter = new UsuariosAdapter(getActivity(), usuarios);
        lstUsers.setAdapter(adapter);

        lstUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ParseUser user = usuarios.get(position);
                Intent intent = new Intent(getActivity(), FeedUsuariosActivity.class);
                intent.putExtra("selectedUserName", user.getUsername());

                startActivity(intent);

            }
        });

        //Recupera usuarios
        getUsuarios();

        return view;
    }

    private void getUsuarios() {

        query = ParseUser.getQuery();

        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {//Sucesso
                    if (objects.size() > 0) {

                        usuarios.clear();
                        for (ParseUser user : objects) {
                            usuarios.add(user);
                        }
                        adapter.notifyDataSetChanged();

                    }
                } else {//Erro
                    Toast.makeText(getActivity(), "Erro ao procurar usuarios! Tente novamente", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
