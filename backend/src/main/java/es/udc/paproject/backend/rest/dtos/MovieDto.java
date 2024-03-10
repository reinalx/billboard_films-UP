package es.udc.paproject.backend.rest.dtos;

public class MovieDto {

    private Long id;
    private String title;
    private String summary;
    private short runtime;

    public MovieDto(){}

    public MovieDto(Long id, String title, String summary, short runtime){
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.runtime = runtime;
    }

    public Long getId() {
        return id;
    }

    public short getRuntime() {
        return runtime;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuntime(short runtime) {
        this.runtime = runtime;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
