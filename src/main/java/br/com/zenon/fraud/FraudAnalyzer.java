package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FraudAnalyzer {

    private final List<Transaction> transactions;


    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getFraudes(){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .toList();
    }

    public long getTotalFraudes() {
        return getFraudes().stream().count();
    }

    public List<Transaction> getFraudesOrdenadoPorValor(){
        return getFraudes().stream()
                .sorted(Comparator
                                .comparing(Transaction::amount)
                                .reversed())
                .toList();
    }

    public List<BigDecimal> getFraudesPorValor(){
        return getFraudesOrdenadoPorValor().stream()
                .map(Transaction::amount)
                .toList();
    }

    public List<Customer> getCustomerOrigem(){
        return getFraudesOrdenadoPorValor().stream()
                .map(Transaction::origin)
                .toList();
    }

    public List<Customer> getCustomerDestino(){
        return getFraudesOrdenadoPorValor().stream()
                .map(Transaction::destination)
                .toList();
    }

    public List<String> getClientesComMaisFraudes(){
        return getCustomerOrigem().stream()
                .map(Customer::name)
                .distinct()
                .toList();
    }

    public BigDecimal getValorTotalEmFraudes(){
        return getFraudesOrdenadoPorValor().stream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public HashMap<TransactionType, Integer> getTipoDeTransacaoComFraudes() {
        final HashMap<TransactionType, Integer> types  = new HashMap<>();
        getFraudesOrdenadoPorValor().stream()
                .map(Transaction::type)
                .forEach(type -> {
            types.put(type, types.getOrDefault(type, 0) + 1);
        });

        return types;
    }


}
