package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(
        int step,
        TransactionType type,
        BigDecimal amount,
        Customer origin,
        Customer destination,
        boolean isFraud,
        boolean isFlaggedFraud
) {
    public Transaction(int step, TransactionType type, BigDecimal amount, Customer origin, Customer destination, boolean isFraud, boolean isFlaggedFraud) {

        if(step < 1)
            throw new IllegalArgumentException("step should be positive: " + step);

        validaCampos("type",type);
        validaValor("amount",amount);
        validaCampos("isFraud",isFraud);
        validaCampos("isFlaggedFraud",isFlaggedFraud);

        this.step = step;
        this.type = type;
        this.amount = amount;
        this.origin = origin;
        this.destination = destination;
        this.isFraud = isFraud;
        this.isFlaggedFraud = isFlaggedFraud;
    }

    public Transaction(String step, TransactionType type, BigDecimal amount, Customer origin, Customer destination, String isFraud, String isFlaggedFraud) {
        validaCampos("step",step);
        this(Integer.parseInt(step), type, amount, origin, destination, parseFlag(isFraud), parseFlag(isFlaggedFraud));
    }

    private static void validaCampos(final String field, Object value) {
        Objects.requireNonNull(value, field+" should not be null");

        if(value instanceof String && value.toString().isBlank())
            throw new IllegalArgumentException(field + " should not be empty ");
    }

    private static void validaValor(String field, BigDecimal value) {
        Objects.requireNonNull(value, field + " should not be null");

        if (value.signum() < 0)
            throw new IllegalArgumentException(field + " should be positive: " + value);
    }

    private static boolean parseFlag(String value) {
        return switch (value) {
            case "1" -> true;
            case "0" -> false;
            default -> throw new IllegalArgumentException("Valor inválido para indicador: " + value);
        };
    }

//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "\n\tstep=" + step +
//                "\n\t,type=" + type +
//                "\n\t,amount=" + amount +
//                "\n\t,nameOrig=" + origin.name() +
//                "\n\t,oldbalanceOrg=" + origin.oldBalance() +
//                "\n\t,newbalanceOrig=" + origin.newBalance() +
//                "\n\t,nameDest=" + destination.name() +
//                "\n\t,oldbalanceDest=" + destination.oldBalance() +
//                "\n\t,newbalanceDest=" + destination.newBalance() +
//                "\n\t,isFraud=" + isFraud +
//                "\n\t,isFlaggedFraud=" + isFlaggedFraud +
//                "\n}";
//    }
}
