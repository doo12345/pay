package com.bookrental.pay;

public class BookPaid extends AbstractEvent {
    Long id;
    String payDate;
    String bookId;
    String customerId;
    int payAmt;
    String payStatus;
    String orderStatus;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayDate() {
        return this.payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getOrderId() {
        return this.bookId;
    }

    public void setOrderId(String bookId) {
        this.bookId = bookId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getPayAmt() {
        return this.payAmt;
    }

    public void setPayAmt(int payAmt) {
        this.payAmt = payAmt;
    }

    public String getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
