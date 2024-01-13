package models;

public class Payment {

    private String paymentMethod;
    private String montlyInstallments;

    public Payment(String paymentMethod, String montlyInstallments) {
        this.paymentMethod = paymentMethod;
        this.montlyInstallments = montlyInstallments;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMontlyInstallments() {
        return montlyInstallments;
    }

    public void setMontlyInstallments(String montlyInstallments) {
        this.montlyInstallments = montlyInstallments;
    }
}
