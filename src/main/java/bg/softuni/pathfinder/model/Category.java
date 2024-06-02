package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.CategoryType;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private CategoryType name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(){};


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
