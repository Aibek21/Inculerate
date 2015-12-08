package kz.inculerate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aibek on 09.12.15.
 */
public class AdapterNewsList extends RecyclerView.Adapter<AdapterNewsList.ViewHolder> {
    ArrayList<News> newsArrayList;
    String baseUrl = "http://fs.royal.kz/640x480xc/";
    Context mContext;
    public AdapterNewsList(Context context,ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
        this.mContext = context;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mDescriptionTextView;
        TextView mDateTextView;
        ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
            mTitleTextView = (TextView) v.findViewById(R.id.title);
            mDescriptionTextView = (TextView) v.findViewById(R.id.description);
            mDateTextView = (TextView) v.findViewById(R.id.date);
            mImageView = (ImageView) v.findViewById(R.id.image);

        }
    }



    @Override
    public AdapterNewsList.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        News news = newsArrayList.get(position);
        holder.mTitleTextView.setText(news.getNewsTitle());
        holder.mDescriptionTextView.setText(news.getNewsCat());
        holder.mDateTextView.setText(news.getNewsCreatedDate());

        Picasso.with(mContext)
                .load(baseUrl + news.getNewsImageFile())
                .into(holder.mImageView);


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
