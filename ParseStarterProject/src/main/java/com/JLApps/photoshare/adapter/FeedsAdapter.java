package com.JLApps.photoshare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.JLApps.photoshare.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedsAdapter extends ArrayAdapter<ParseObject> {
    private Context context;
    private ArrayList<ParseObject> feeds;

    public FeedsAdapter( Context c, ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.feeds = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if(view == null){
            //Inicializa objeto para montagem do layout a partir do XML
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //monta a view a partir do xml
            view = inflater.inflate(R.layout.lista_postagem,parent, false);
        }

        //Verifica se existem postagens
        if(feeds.size() > 0){

            //recupera o componente da tela
            ImageView imagemPostagem = (ImageView)view.findViewById(R.id.image_lista_postagem);

            ParseObject parseObject = feeds.get( position );

            Picasso.get().load( parseObject.getParseFile("imagem").getUrl()).fit().into(imagemPostagem);

        }

        return view;
    }
}
