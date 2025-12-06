package dsa.practice.lld.parkingLot.payment;

class CreditCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        if(amount > 0){
            System.out.println("Payment successful of Rs " + amount + " via " + this.getClass().getSimpleName() + " method.");
        }else{
            System.out.println("Payment Declined");
        }
    }
}