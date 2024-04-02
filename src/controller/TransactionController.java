package controller;

import java.util.concurrent.Semaphore;

public class TransactionController extends Thread {

	private int transaction;
	private int account;
	private double balance;
	private double value;
	private Semaphore depositMutex;
	private Semaphore withdrawMutex;
	
	public TransactionController() {
		super();
	}
	
	public TransactionController(int transaction, int account, double balance, double value, Semaphore depositMutex, Semaphore withdrawMutex) {
		this.transaction = transaction;
		this.account = account;
		this.balance = balance;
		this.value = value;
		this.depositMutex = depositMutex;
		this.withdrawMutex = withdrawMutex;
	}

	private void deposit() throws InterruptedException {
		depositMutex.acquire();
		balance += value;
		System.out.printf("%s: deposit of R$%.2f (New balance: R$%.2f)\n", this, value, balance);
		depositMutex.release();
	}
	
	private void withdraw() throws InterruptedException {
		withdrawMutex.acquire();
		balance -= value;
		System.out.printf("%s: withdrawal of R$%.2f (New balance: R$%.2f)\n", this, value, balance);
		withdrawMutex.release();
	}
	
	@Override
	public void run() {
		try {
			if(transaction % 2 == 0)
				deposit();
			else
				withdraw();
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		
		return "Account " + account;
	}
}
