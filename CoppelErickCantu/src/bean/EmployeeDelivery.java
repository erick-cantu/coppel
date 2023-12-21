package bean;

public class EmployeeDelivery {
    private int idEmployeeDelivery;
    private int idEmployee;
    private int month;
    private int year;
    private int delivery;

    public int getIdEmployeeDelivery() {
        return idEmployeeDelivery;
    }

    public void setIdEmployeeDelivery(int idEmployeeDelivery) {
        this.idEmployeeDelivery = idEmployeeDelivery;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }


    public EmployeeDelivery(int idEmployeeDelivery, int idEmployee, int month, int year, int delivery) {
        this.idEmployeeDelivery = idEmployeeDelivery;
        this.idEmployee = idEmployee;
        this.month = month;
        this.year = year;
        this.delivery = delivery;
    }
}
