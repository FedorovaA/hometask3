package com.student.myapplication.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.student.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private MovieListFragment fragment;

    public MovieListAdapter(MovieListFragment fragment) {
        this.fragment = fragment;
        movies = new ArrayList<>();
        movies.add(new Movie("The Shawshank Redemption","2008","8.5","movie" + (1 + movies.size()),"Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));
        movies.add(new Movie("The Godfather","2001", "8.3", "movie" + (1 + movies.size()),"The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."));
        movies.add(new Movie("The Godfather: Part II", "1995","8.7", "movie" + (1 + movies.size()),"The early life and career of Vito Corleone in 1920s New York is portrayed while his son, Michael, expands and tightens his grip on the family crime syndicate."));
        movies.add(new Movie("The Dark Knight", "204","8.3","movie" + (1 + movies.size()),"When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice."));
        movies.add(new Movie("12 Angry Men", "1999","9.6","movie" + (1 + movies.size()),"A jury holdout attempts to prevent a miscarriage of justice by forcing his colleagues to reconsider the evidence."));
        movies.add(new Movie("Schindler's List","2001","9.1", "movie" + (1 + movies.size()), "In German-occupied Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazi Germans."));
        movies.add(new Movie("Pulp Fiction","2001","9.6","movie" + (1 + movies.size()), "The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption."));
        movies.add(new Movie("The Lord of the Rings: The Return of the King","2005" ,"9.5","movie" + (1 + movies.size()),"Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."));
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getMark().compareTo(o1.getMark());
            }
        });
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie movie = movies.get(position);
                    fragment.showMovieToast(movie);
                }
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Movie movie = movies.get(i);
        viewHolder.number.setText(String.valueOf(i+1));
        viewHolder.title.setText(movie.getTitle());
        viewHolder.description.setText(movie.getDescription());
        viewHolder.mark.setText(movie.getMark());
        viewHolder.year.setText(movie.getYear());
        viewHolder.imageView.setImageDrawable(fragment.getActivity().getDrawable(
                fragment.getActivity().getResources().getIdentifier(
                        movie.getImage(), "drawable", fragment.getActivity().getPackageName())));

    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;
        TextView year;
        TextView mark;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_item_title);
            description = itemView.findViewById(R.id.list_item_description);
            imageView = itemView.findViewById(R.id.list_item_image);
            year = itemView.findViewById(R.id.list_item_year);
            mark = itemView.findViewById(R.id.list_item_mark);
            number = itemView.findViewById(R.id.item_number);
        }
    }
}
