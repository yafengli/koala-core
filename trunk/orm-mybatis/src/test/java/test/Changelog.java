package test;

public class Changelog {
    private Long id;
    private String applied_at;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplied_at() {
        return applied_at;
    }

    public void setApplied_at(String applied_at) {
        this.applied_at = applied_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
