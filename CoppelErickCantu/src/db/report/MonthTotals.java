package db.report;

public class MonthTotals {
    private int idEmployee;
    private int month;
    private int year;
    private int deliveries;
    private int idEmployeeRole;
    private float workingHours;
    private float totalPayPerDelivery;
    private float totalBonus;
    private float totalRetention;
    private float vouchers;
    private float totalPaycheck;

    public int getIdEmployeeRole() {
        return idEmployeeRole;
    }

    public void setIdEmployeeRole(int idEmployeeRole) {
        this.idEmployeeRole = idEmployeeRole;
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

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    public float getTotalPayPerDelivery() {
        return totalPayPerDelivery;
    }

    public void setTotalPayPerDelivery(float totalPayPerDelivery) {
        this.totalPayPerDelivery = totalPayPerDelivery;
    }

    public float getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(float totalBonus) {
        this.totalBonus = totalBonus;
    }

    public float getTotalRetention() {
        return totalRetention;
    }

    public void setTotalRetention(float totalRetention) {
        this.totalRetention = totalRetention;
    }

    public float getVouchers() {
        return vouchers;
    }

    public void setVouchers(float vouchers) {
        this.vouchers = vouchers;
    }

    public float getTotalPaycheck() {
        return totalPaycheck;
    }

    public void setTotalPaycheck(float totalPaycheck) {
        this.totalPaycheck = totalPaycheck;
    }

    public int getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }
}
