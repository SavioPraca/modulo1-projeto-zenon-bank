package br.com.zenon;

import br.com.zenon.fraud.FraudAnalyzer;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public class Main {
    static void main() throws IOException {
        final String caminho = "../data/PS_20174392719_1491204439457_log.csv";
        final List<Transaction> transactionList = new TransactionIngestor(caminho).getTransactions();
        final FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactionList);


        System.out.println("1. Total de Fraudes: " + fraudAnalyzer.getTotalFraudes());
        System.out.println("2. Top 3 Fraudes de Maior Valor:");
        fraudAnalyzer.getFraudesPorValor().stream().limit(3).map(BigDecimal::toPlainString).forEach(System.out::println);
        System.out.println("3. Clientes Suspeitos:");
        fraudAnalyzer.getClientesComMaisFraudes().stream().limit(5).forEach(System.out::println);

        System.out.println("4. Prejuízo Total: " + fraudAnalyzer.getValorTotalEmFraudes());
        System.out.println("5. Fraudes por Tipo:");
        fraudAnalyzer.getTipoDeTransacaoComFraudes().forEach((k,v) -> System.out.println(" - " + k + ": " + v));


    }

}
