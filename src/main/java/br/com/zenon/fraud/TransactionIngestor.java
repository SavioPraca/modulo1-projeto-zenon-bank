package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {

    private static final int MAX_LIMIT = 50000;
    private final List<Transaction> transactions;

    public TransactionIngestor(final String pathName) {
        this.transactions = csvTransactionMapper(pathName);
    }

    private List<Transaction> csvTransactionMapper(final String caminhoArquivo){
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            throw new IllegalArgumentException("Caminho do arquivo não informado.");
        }

        final List<Transaction> transactionList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo, StandardCharsets.UTF_8)))
        {
            reader.readLine();

            String line;
            int count = 0;

            while (count < MAX_LIMIT && (line = reader.readLine()) != null) {
                if (line.isBlank())
                    continue;

                montaTransaction(line).ifPresent(transactionList::add);
                count++;
            }

        } catch (IOException exception) {
            throw new RuntimeException("ERRO: " + exception.getMessage());
        }

        return transactionList;
    }

    private Optional<Transaction> montaTransaction(final String line){
        final String[] coluna = line.split(",",-1);
        try {
            final Customer origem = montaCustomer(coluna[3],coluna[4],coluna[5]);
            final Customer destino = montaCustomer(coluna[6],coluna[7],coluna[8]);

            return Optional.of(new Transaction(
                    coluna[0],
                    TransactionType.valueOf(coluna[1]),
                    new BigDecimal(coluna[2]),
                    origem,
                    destino,
                    coluna[9],
                    coluna[10])
            );

        } catch (RuntimeException e) {
            System.err.println("ERRO: " + line + " | " + e );
            return Optional.empty();
        }
    }

    private Customer montaCustomer(final String name, final String oldBalance, final String newBalance){
        return new Customer(name, new BigDecimal(oldBalance), new BigDecimal(newBalance));
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }
}
