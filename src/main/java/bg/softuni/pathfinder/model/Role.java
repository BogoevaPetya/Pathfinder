package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private UserRoles name;

    public Role(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRoles getName() {
        return name;
    }

    public void setName(UserRoles name) {
        this.name = name;
    }

}
