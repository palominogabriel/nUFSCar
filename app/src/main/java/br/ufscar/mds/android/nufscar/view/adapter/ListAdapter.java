package br.ufscar.mds.android.nufscar.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.ufscar.mds.android.nufscar.R;
import br.ufscar.mds.android.nufscar.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel Palomino on 26/01/2017.
 */

public class ListAdapter extends BaseAdapter {

    private List<News> mAndroidMapList = new ArrayList<>();

    public ListAdapter(List<News> mAndroidMapList) {
        // Sort the list to From the most recent News
        for(int i = mAndroidMapList.size()-1; i >= 0 ; --i){
            this.mAndroidMapList.add(mAndroidMapList.get(i));
        }
    }

    @Override
    public int getCount() {
        return mAndroidMapList.size();
    }

    @Override
    public News getItem(int position) {
        return mAndroidMapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewInflater = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);

        News news = this.mAndroidMapList.get(position);

        ContentViewHolder contentViewHolder = new ContentViewHolder(viewInflater);
        contentViewHolder.newsDate.setText(news.getData());
        contentViewHolder.newsTitle.setText(news.getTitulo());
        return viewInflater;
    }

    public class ContentViewHolder {
        protected TextView newsDate;
        protected TextView newsTitle;

        public ContentViewHolder(View view) {
            this.newsDate = (TextView) view.findViewById(R.id.text_view_news_date);
            this.newsTitle = (TextView) view.findViewById(R.id.text_view_news_title);
        }
    }

}
