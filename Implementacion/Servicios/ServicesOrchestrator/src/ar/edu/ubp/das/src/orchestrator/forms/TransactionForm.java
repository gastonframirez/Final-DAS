package ar.edu.ubp.das.src.orchestrator.forms;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class TransactionForm extends DynaActionForm {
	private Integer transactionID;
	private String fecha;
	private String productID;
	private String prodName;
	private Float productPrice;
	private String transactionType;
	private Float commision;
	private String offerID;
	private String userName;
	private String userLastName;
	private String userEmail;
	private Integer userDni;
	
	public Integer getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Float getCommision() {
		return commision;
	}
	public void setCommision(Float commision) {
		this.commision = commision;
	}
	public String getOfferID() {
		return offerID;
	}
	public void setOfferID(String offerID) {
		this.offerID = offerID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getUserDni() {
		return userDni;
	}
	public void setUserDni(Integer userDni) {
		this.userDni = userDni;
	}
	
	
	
}
