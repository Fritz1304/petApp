package jorgedev.app.petapp.domain.model;

public class User {

    private final String id;
    private final String name;
    private final String username;
    private final String password;

    // Falta agregar las debidas restricciones referentes a seguridada y reglas del negocio
    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
