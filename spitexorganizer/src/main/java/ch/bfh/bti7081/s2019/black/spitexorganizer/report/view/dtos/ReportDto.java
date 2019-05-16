package ch.bfh.bti7081.s2019.black.spitexorganizer.report.view.dtos;

public class ReportDto {
    private Long id;
    private Boolean edit;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "id=" + id +
                ", edit=" + edit +
                ", description='" + description + '\'' +
                '}';
    }
}
