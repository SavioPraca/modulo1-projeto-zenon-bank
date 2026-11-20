package br.com.zenon;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;

import java.math.BigDecimal;

import static br.com.zenon.fraud.TransactionType.CASH_OUT;
import static br.com.zenon.fraud.TransactionType.PAYMENT;


public class Main {
    static void main() {
        Customer customerOrig = new Customer(
                "C1231006815",
                new BigDecimal("170136.0"),
                new BigDecimal("160296.36"));

        Customer customerDest = new Customer(
                "M1979787155",
                new BigDecimal("0.0"),
                new BigDecimal("0.0"));

        Transaction transaction = new Transaction(
                1,
                PAYMENT,
                new BigDecimal("9839.64"),
                customerOrig,
                customerDest,
                0,
                0
        );


        Customer customerOrig2 = new Customer(
                "C1280323807",
                new BigDecimal("850002.52"),
                new BigDecimal("0.0"));

        Customer customerDest2 = new Customer(
                "C873221189",
                new BigDecimal("6510099.11"),
                new BigDecimal("7360101.63"));

        Transaction transaction2 = new Transaction(
                743,
                CASH_OUT,
                new BigDecimal("850002.52"),
                customerOrig2,
                customerDest2,
                1,
                0
        );


        System.out.println(transaction);
        System.out.println(transaction2);

    }

}
