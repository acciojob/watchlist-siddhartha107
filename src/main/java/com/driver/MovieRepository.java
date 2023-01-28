package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }


    public void addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);

    }

    public void addDirector(Director director){
        directorMap.put(director.getName(),director);

    }

    public void addMovieDirectorPair(String movieName, String directorName){
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)) {
            movieMap.put(movieName, movieMap.get(movieName));
            directorMap.put(directorName, directorMap.get(directorName));

            List<String> currentMovies = new ArrayList<>();
            if (directorMovieMapping.containsKey(directorName)) {
                currentMovies = directorMovieMapping.get(directorName);
            }
            currentMovies.add(movieName);
            directorMovieMapping.put(directorName, currentMovies);
        }

    }

    public Movie getMovie(String movieName){
        return movieMap.get(movieName);

    }

    public Director getDirector(String directorName){
        return directorMap.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieList  = new ArrayList<>();
        if(directorMovieMapping.containsKey(directorName)){
            movieList = directorMovieMapping.get(directorName);
        }
        return movieList;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String directorName){
        List<String> movies = new ArrayList<>();
        if(directorMovieMapping.containsKey(directorName)){
            movies = directorMovieMapping.get(directorName);
            for(String movie : movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            directorMovieMapping.remove(directorName);
        }
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
    }

    public void deleteAllDirectors(){
        HashSet<String> moviesSet = new HashSet<>();

        for(String directorName : directorMovieMapping.keySet()){
            for(String movie : directorMovieMapping.get(directorName)){
                moviesSet.add(movie);
            }
        }
        for(String movie : moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
    }

}
