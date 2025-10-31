package hellojpa.jpql;

public class ProductDTO {
    private Long productId;
    private String name;
    private int price;
    private int stockAmount;

    private Long orderId;
    private int orderAmount;
    private Address address;

    public ProductDTO() { }

    public ProductDTO(Long productId, String name, int price, int stockAmount, Long orderId, int orderAmount, Address address) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockAmount = stockAmount;
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.address = address;
    }

    public ProductDTO(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public ProductDTO(String name, int price, int orderAmount) {
        this.name = name;
        this.price = price;
        this.orderAmount = orderAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockAmount=" + stockAmount +
                ", orderId=" + orderId +
                ", orderAmount=" + orderAmount +
                ", address=" + address +
                '}';
    }
}
