package itesm.com.tecstore;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

class Article_adapter extends BaseAdapter {
    Context context;
    String countryList[];
    int flags[];
    String descripciones[];
    LayoutInflater inflter;

    public Article_adapter(Context applicationContext, String[] countryList, int[] flags, String[] descripciones) {
        this.context = context;
        this.countryList = countryList;
        this.flags = flags;
        this.descripciones=descripciones;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.list_item, null);
        TextView article = (TextView) view.findViewById(R.id.textView_name);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView_poster);
        TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
        article.setText(countryList[i]);
        descripcion.setText(descripciones[i]);
        icon.setImageResource(flags[i]);
        return view;
    }
}