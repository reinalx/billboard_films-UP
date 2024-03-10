package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
public class Movie {
    private Long id;
    private String title;
    private String summary;
    private short runtime;


    public Movie() {}

    public Movie(String title, String summary, short runtime) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.runtime = runtime;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public short getRuntime() {
        return runtime;
    }

    public void setRuntime(short runtime) {
        this.runtime = runtime;
    }


}
