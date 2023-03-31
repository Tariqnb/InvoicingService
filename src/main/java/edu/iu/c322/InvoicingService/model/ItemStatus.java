package edu.iu.c322.InvoicingService.model;
public class ItemStatus {

    private int itemId;
    private String status;

    public ItemStatus() {}

    public ItemStatus(int itemId, String status) {
        this.itemId = itemId;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemStatus{" +
                "itemId=" + itemId +
                ", status='" + status + '\'' +
                '}';
    }
}