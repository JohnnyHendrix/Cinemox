package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jean-marchendrikse on 26.01.17.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private ArrayList<Movie> movies;
    private Context mContext;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        this.movies = movies;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        //private TextView mTitle;
        private ImageView mPosterView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            //   mTitle = (TextView)view.findViewById(R.id.tv_movie_title);
            mPosterView = (ImageView) view.findViewById(R.id.iv_poster_movie);
        }
    }

    /**
     * onCreateViewHolder() is called each time the RecyclerView needs a new ViewHolder to
     * display an image. This is called initially to create a number of ViewHolders which are
     * then recycled (so onCreateViewHolder() won’t be called again).
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.row_layout, parent, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Picasso.with(mContext).load(movies.get(position).getPosterUrl()).into(holder.mPosterView);
    }

    @Override
    public int getItemCount() {
        if (null == movies) return 0;
        return movies.size();
    }
}
