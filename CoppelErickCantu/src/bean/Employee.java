package bean;

public class Employee {
    private int idEmployee;
    private String name;
    private Role rol;

    public Employee(int idEmployee, String name, Role rol) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.rol = rol;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}
