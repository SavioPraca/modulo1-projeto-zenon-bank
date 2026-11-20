package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        int step,
        TransactionType type,
        BigDecimal amount,
        Customer origin,
        Customer destination,
        boolean isFraud,
        boolean isFlaggedFraud
) {

    public Transaction(int step, TransactionType type, BigDecimal amount, Customer origin, Customer destination, int isFraud, int isFlaggedFraud) {
        this(step, type, amount, origin, destination, parseFlag(isFraud), parseFlag(isFlaggedFraud));
    }

    private static boolean parseFlag(int value) {
        return switch (value) {
            case 1 -> true;
            case 0 -> false;
            default -> throw new IllegalArgumentException(
                    "Valor inválido para indicador: " + value
            );
        };
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "\n\tstep=" + step +
                "\n\t,type=" + type +
                "\n\t,amount=" + amount +
                "\n\t,nameOrig=" + origin.name() +
                "\n\t,oldbalanceOrg=" + origin.oldBalance() +
                "\n\t,newbalanceOrig=" + origin.newBalance() +
                "\n\t,nameDest=" + destination.name() +
                "\n\t,oldbalanceDest=" + destination.oldBalance() +
                "\n\t,newbalanceDest=" + destination.newBalance() +
                "\n\t,isFraud=" + isFraud +
                "\n\t,isFlaggedFraud=" + isFlaggedFraud +
                "\n}";
    }
}
