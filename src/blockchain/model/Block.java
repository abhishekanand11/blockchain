package blockchain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blockchain.model.transaction.Transaction;
import blockchain.util.StringUtil;

/**
 * Block Class used to store each block of the chain
 * 
 * @author abhanand
 *
 */
public class Block {
	public String hash;
	public String previousHash;
	public String merkleRoot;
	public List<Transaction> transactions = new ArrayList<Transaction>(); // our data will be a simple message.
	private long timeStamp; // milliseconds since 1st Jan 1970. will be used more like created time
	private int nonce;

	public Block(String previousHash) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); // Check for the positioning, to be kept at last after all other values are set
	}

	/**
	 * Used to calculate the hash using previous hash, time-stamp, nonce and the
	 * data provided as input
	 * 
	 * @return String - calculated hash
	 */
	public String calculateHash() {
		String calculatedHash = StringUtil
				.applySHA256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot);
		return calculatedHash;
	}

	/**
	 * This method is used to mine blocks
	 * 
	 * @param difficulty
	 */
	public void mineBlock(int difficulty) {
		merkleRoot = StringUtil.getMerkleRoot(transactions);
		String target = StringUtil.getDificultyString(difficulty); // Create a string with difficulty * "0"
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}

	// Add transactions to this block
	public boolean addTransaction(Transaction transaction) {
		// process transaction and check if valid, unless block is genesis block then
		// ignore.
		if (transaction == null)
			return false;
		if (previousHash != "0") {
			if ((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}

}
