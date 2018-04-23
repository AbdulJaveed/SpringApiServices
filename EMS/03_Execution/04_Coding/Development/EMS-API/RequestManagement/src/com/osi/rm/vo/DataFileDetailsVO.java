package com.osi.rm.vo;

public class DataFileDetailsVO {
	String header;
	String cashierName;
	String machineSerialNum = "";
	String locationName="";
	String instrumentName = "Return FxF-Cashier fr POS";
	String sourceBankAccount;
	String destBankaccount;
	String currencyId;
	String amount;
	String biller="";
	String noOfTransactions="0";
	String chkNum = "";
	//String nameOnChk;
	String payor = "";
	String chkIssuedBank = "";
	String chkIssuedBranch="";
	String cashDenomination;
	String foreignCurrencyCode="";
	String foreignCurrencyAmount="";
	String foreignCurrencyDenomination="";
	String cashierEmpNumber;
	
	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	/**
	 * @return the cashierName
	 */
	public String getCashierName() {
		return cashierName;
	}
	/**
	 * @param cashierName the cashierName to set
	 */
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}
	/**
	 * @return the machineSerialNum
	 */
	public String getMachineSerialNum() {
		return machineSerialNum;
	}
	/**
	 * @param machineSerialNum the machineSerialNum to set
	 */
	public void setMachineSerialNum(String machineSerialNum) {
		this.machineSerialNum = machineSerialNum;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the instrumentName
	 */
	public String getInstrumentName() {
		return instrumentName;
	}
	/**
	 * @param instrumentName the instrumentName to set
	 */
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	/**
	 * @return the sourceBankAccount
	 */
	public String getSourceBankAccount() {
		return sourceBankAccount;
	}
	/**
	 * @param sourceBankAccount the sourceBankAccount to set
	 */
	public void setSourceBankAccount(String sourceBankAccount) {
		this.sourceBankAccount = sourceBankAccount;
	}
	/**
	 * @return the destBankaccount
	 */
	public String getDestBankaccount() {
		return destBankaccount;
	}
	/**
	 * @param destBankaccount the destBankaccount to set
	 */
	public void setDestBankaccount(String destBankaccount) {
		this.destBankaccount = destBankaccount;
	}
	/**
	 * @return the currencyId
	 */
	public String getCurrencyId() {
		return currencyId;
	}
	/**
	 * @param currencyId the currencyId to set
	 */
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the biller
	 */
	public String getBiller() {
		return biller;
	}
	/**
	 * @param biller the biller to set
	 */
	public void setBiller(String biller) {
		this.biller = biller;
	}
	/**
	 * @return the noOfTransactions
	 */
	public String getNoOfTransactions() {
		return noOfTransactions;
	}
	/**
	 * @param noOfTransactions the noOfTransactions to set
	 */
	public void setNoOfTransactions(String noOfTransactions) {
		this.noOfTransactions = noOfTransactions;
	}
	/**
	 * @return the chkNum
	 */
	public String getChkNum() {
		return chkNum;
	}
	/**
	 * @param chkNum the chkNum to set
	 */
	public void setChkNum(String chkNum) {
		this.chkNum = chkNum;
	}
	/**
	 * @return the payor
	 */
	public String getPayor() {
		return payor;
	}
	/**
	 * @param payor the payor to set
	 */
	public void setPayor(String payor) {
		this.payor = payor;
	}
	/**
	 * @return the chkIssuedBank
	 */
	public String getChkIssuedBank() {
		return chkIssuedBank;
	}
	/**
	 * @param chkIssuedBank the chkIssuedBank to set
	 */
	public void setChkIssuedBank(String chkIssuedBank) {
		this.chkIssuedBank = chkIssuedBank;
	}
	/**
	 * @return the chkIssuedBranch
	 */
	public String getChkIssuedBranch() {
		return chkIssuedBranch;
	}
	/**
	 * @param chkIssuedBranch the chkIssuedBranch to set
	 */
	public void setChkIssuedBranch(String chkIssuedBranch) {
		this.chkIssuedBranch = chkIssuedBranch;
	}
	/**
	 * @return the cashDenomination
	 */
	public String getCashDenomination() {
		return cashDenomination;
	}
	/**
	 * @param cashDenomination the cashDenomination to set
	 */
	public void setCashDenomination(String cashDenomination) {
		this.cashDenomination = cashDenomination;
	}
	/**
	 * @return the foreignCurrencyCode
	 */
	public String getForeignCurrencyCode() {
		return foreignCurrencyCode;
	}
	/**
	 * @param foreignCurrencyCode the foreignCurrencyCode to set
	 */
	public void setForeignCurrencyCode(String foreignCurrencyCode) {
		this.foreignCurrencyCode = foreignCurrencyCode;
	}
	/**
	 * @return the foreignCurrencyAmount
	 */
	public String getForeignCurrencyAmount() {
		return foreignCurrencyAmount;
	}
	/**
	 * @param foreignCurrencyAmount the foreignCurrencyAmount to set
	 */
	public void setForeignCurrencyAmount(String foreignCurrencyAmount) {
		this.foreignCurrencyAmount = foreignCurrencyAmount;
	}
	/**
	 * @return the foreignCurrencyDenomination
	 */
	public String getForeignCurrencyDenomination() {
		return foreignCurrencyDenomination;
	}
	/**
	 * @param foreignCurrencyDenomination the foreignCurrencyDenomination to set
	 */
	public void setForeignCurrencyDenomination(String foreignCurrencyDenomination) {
		this.foreignCurrencyDenomination = foreignCurrencyDenomination;
	}
	/**
	 * @return the cashierEmpNumber
	 */
	public String getCashierEmpNumber() {
		return cashierEmpNumber;
	}
	/**
	 * @param cashierEmpNumber the cashierEmpNumber to set
	 */
	public void setCashierEmpNumber(String cashierEmpNumber) {
		this.cashierEmpNumber = cashierEmpNumber;
	}

}
