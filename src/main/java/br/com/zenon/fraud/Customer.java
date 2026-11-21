package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

    public Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

        validaCampos("name", name);
        validaValor("oldBalance",oldBalance);
        validaValor("newBalance",newBalance);

        this.name = name;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    private static void validaCampos(final String field, Object value) {
        validaObjetoNull(field,value);

        if(value instanceof String && value.toString().isBlank())
            throw new IllegalArgumentException(field + " should not be empty ");
    }

    private static void validaValor(String field, BigDecimal value) {
        validaObjetoNull(field,value);

        if (value.signum() < 0)
            throw new IllegalArgumentException(field + " should be positive: " + value);
    }

    private  static void validaObjetoNull(String field, Object value) {
        Objects.requireNonNull(value, field + " should not be null");
    }
}
