package br.com.zenon;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

import java.io.IOException;
import java.util.List;


public class Main {
    static void main() throws IOException {
        final String caminho = "../data/PS_20174392719_1491204439457_log.csv";
        final List<Transaction> transactionList = new TransactionIngestor(caminho).getTransactions();
        transactionList.stream()
                .limit(10)
                .forEach(System.out::println);

    }

}
