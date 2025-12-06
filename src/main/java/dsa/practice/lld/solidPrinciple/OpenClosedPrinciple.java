package dsa.practice.lld.solidPrinciple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* NOTE Closed Should be open for extension but close for Modification
* */

public class OpenClosedPrinciple {
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.pay(100,"g-pay");

        PaymentScheduler paymentScheduler = new PaymentScheduler(new GPay());
        paymentScheduler.pay(100);
    }
}

interface PaymentMethod {
    void pay(double amount);
}

class GPay implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Transaction successful of " + amount + " via " + this.getClass().getSimpleName());
    }
}

class PhonePay implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Transaction successful of " + amount + " via " + this.getClass().getSimpleName());
    }
}

class PayPal implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Transaction successful of " + amount + " via " + this.getClass().getSimpleName());
    }
}

/**
* NOTE now we are using dependency injection over multiple if-else statements
* */

class PaymentScheduler{
    PaymentMethod paymentMethod;

    public PaymentScheduler(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void pay(double amount){
        paymentMethod.pay(amount);
    }
}

/**
* BUG if a new payment method comes then need to modify existing class, will violate OCP
* */

class Payment{
    public void pay(double amount, String method){
        if(method.equalsIgnoreCase("G-PAY")){
            System.out.println("Transaction successful of " + amount + " via " + method);
        } else if (method.equalsIgnoreCase("PHONE-PAY")) {
            System.out.println("Transaction successful of " + amount + " via " + method);
        }else {
            System.out.println("Transaction cannot be completed at the moment  of " + amount + " via " + method);
        }
    }
}
