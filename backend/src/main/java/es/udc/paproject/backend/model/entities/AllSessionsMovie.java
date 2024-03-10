package es.udc.paproject.backend.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllSessionsMovie {

    private Movie movie;

    private List<Session> sessionList = new ArrayList<>();


    public AllSessionsMovie(Movie movie){
        this.movie = movie;
    }
    public AllSessionsMovie(Movie movie,  Session sessionList){
        this.movie = movie;
        this.sessionList.add(sessionList);
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void addSession(Session session){
        sessionList.add(session);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result +((movie == null)? 0 : movie.hashCode());
        result = prime * result + ((sessionList == null)? 0 : sessionList.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AllSessionsMovie other = (AllSessionsMovie) obj;

        if(movie == null){
            if(other.movie != null)
                return false;
        }else if(!movie.equals(other.movie))
            return false;
        if(sessionList == null){
            if(!sessionList.equals(other.sessionList))
                return false;
        }
        return true;
    }
}
