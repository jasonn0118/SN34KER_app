package com.example.sn34ker.datamodels;

public class OrderTableModel {
    private int productId,pin,cardExpDate,orderID;
    private String customerName,customerAddress,postalCode,creditCardNumber,customerId,productName;
    private String productBrand;
    private double CA_price;
    private double US_Size;

    public OrderTableModel(int orderID,int productId, int pin, int cardExpDate, String customerName,
                           String customerAddress, String postalCode, String creditCardNumber,
                           String customerId, String productName, String productBrand, double CA_price, double US_Size)
    {
        this.orderID=orderID;
        this.productId = productId;
        this.pin = pin;
        this.cardExpDate = cardExpDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.creditCardNumber = creditCardNumber;
        this.customerId = customerId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.CA_price = CA_price;
        this.US_Size = US_Size;
    }
//getter

    public int getOrderID() {
        return orderID;
    }

    public int getProductId() {
        return productId;
    }

    public int getPin() {
        return pin;
    }

    public int getCardExpDate() {
        return cardExpDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public double getCA_price() {
        return CA_price;
    }

    public double getUS_Size() {
        return US_Size;
    }

    //setter


    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setCardExpDate(int cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public void setCA_price(double CA_price) {
        this.CA_price = CA_price;
    }

    public void setUS_Size(double US_Size) {
        this.US_Size = US_Size;
    }

    @Override
    public String toString() {
        return "OrderTableModel{" +
                "productId=" + productId +
                ", pin=" + pin +
                ", cardExpDate=" + cardExpDate +
                ", orderID=" + orderID +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", CA_price=" + CA_price +
                ", US_Size=" + US_Size +
                '}';
    }
}
