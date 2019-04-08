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
    LayoutInflater inflter;

    public Article_adapter(Context applicationContext, String[] countryList, int[] flags) {
        this.context = context;
        this.countryList = countryList;
        this.flags = flags;
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
        article.setText(countryList[i]);
        icon.setImageResource(flags[i]);
        return view;
    }
}