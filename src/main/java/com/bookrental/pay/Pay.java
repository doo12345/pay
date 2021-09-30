package com.bookrental.pay;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Entity
@Table(name = "Pay_table")
public class Pay {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String payDate;
    String rentalId;
    String customerId;
    int payAmt;
    String payStatus;
    String orderStatus;

    @PrePersist
    public void onPrePersist(){

        if("취소".equals(orderStatus)){
            BookCanceled 결제취소됨 = new BookCanceled();
            BeanUtils.copyProperties(this, 결제취소됨);
            결제취소됨.publish();

        }else{
            BookPaid 결제승인됨 = new BookPaid();
            BeanUtils.copyProperties(this, 결제승인됨);

            //바로 이벤트를 보내버리면 주문정보가 커밋되기도 전에 배송발송됨 이벤트가 발송되어 주문테이블의 상태가 바뀌지 않을 수 있다.
            // TX 리스너는 커밋이 완료된 후에 이벤트를 발생하도록 만들어준다.
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void beforeCommit(boolean readOnly) {
                    결제승인됨.publish();
                }
            });

            try {
                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @PostPersist
    public void OrderPaid(){
        System.out.println("%%%%%%%%%%%%%%%%S");
    }

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
        return this.rentalId;
    }

    public void setOrderId(String orderId) {
        this.rentalId = orderId;
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
