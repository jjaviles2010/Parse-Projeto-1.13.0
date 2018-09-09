package com.JLApps.photoshare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;
import com.JLApps.photoshare.R;

import java.util.ArrayList;
import java.util.List;

public class UsuariosAdapter extends ArrayAdapter<ParseUser> {
    private Context context;
    private ArrayList<ParseUser> users;
    private TextView userName;
    private TextView email;


    public UsuariosAdapter(Context c, ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.users = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        /**
         * Verifica se a View ja esta criada, pois ela fina
         * no cache do Android
         */
        if(view == null){

            //Inicializa o objeto para Montagem da View
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inicializa a view a partir do Layout
            view = inflater.inflate(R.layout.lista_usuarios,parent,false);
        }

        if(users.size() > 0){
            userName = (TextView) view.findViewById(R.id.txtUserName);
            email = (TextView) view.findViewById(R.id.txtEmail);

            userName.setText(users.get(position).getUsername());
            email.setText("user@gmail.com");
            //email.setText(users.get(position).getEmail());

        }

        return view;
    }
}
