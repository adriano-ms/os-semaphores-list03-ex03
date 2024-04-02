package view;

import java.util.concurrent.Semaphore;

import controller.TransactionController;

public class Main {

	public static void main(String[] args) {

		TransactionController[] transactions = new TransactionController[20];

		Semaphore withdrawMutex = new Semaphore(1);
		Semaphore depositMutex = new Semaphore(1);

		for (int i = 0; i < 20; i++) {
			int transaction = (int) ((Math.random() * 2) + 1);
			double initialBalance = ((Math.random() * 1001) + 100);
			double transactionValue = ((Math.random() * 201) + 50);
			transactions[i] = new TransactionController(transaction, i, initialBalance, transactionValue, depositMutex,
					withdrawMutex);
		}

		for (TransactionController transaction : transactions)
			transaction.start();

	}
}
