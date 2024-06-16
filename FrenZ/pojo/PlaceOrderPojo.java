/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrenZ.pojo;

/**
 *
 * @author himan
 */
public class PlaceOrderPojo {

    @Override
    public String toString() {
        return "PlaceOrderPojo{" + "orderId=" + orderId + ", productId=" + productId + ", customerId=" + customerId + ", deliveryStaffId=" + deliveryStaffId + ", companyId=" + companyId + '}';
    }

    public PlaceOrderPojo(String orderId, String productId, String customerId, String deliveryStaffId, String companyId) {
        this.orderId = orderId;
        this.productId = productId;
        this.customerId = customerId;
        this.deliveryStaffId = deliveryStaffId;
        this.companyId = companyId;
    }
    public PlaceOrderPojo(){
        
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryStaffId() {
        return deliveryStaffId;
    }

    public void setDeliveryStaffId(String deliveryStaffId) {
        this.deliveryStaffId = deliveryStaffId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    private String orderId;
    private String productId;
    private String customerId;
    private String deliveryStaffId;
    private String companyId;

}
