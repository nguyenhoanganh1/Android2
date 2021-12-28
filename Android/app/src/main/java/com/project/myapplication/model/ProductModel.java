package com.project.myapplication.model;

import java.util.List;

public class ProductModel {

    private List<Data> data;

    private String status;

    public List<Data> getData ()
    {
        return data;
    }

    public void setData (List<Data> data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString() { return "ClassPojo [data = "+data+", status = "+status+"]"; }

    public class Data{
        private String id;
        private String name;
        private String image;
        private String unitPrice;
        private String discount;
        private String quantity;
        private String productDate;
        private String description;
        private String special;
        private String latest;
        private String clickCount;

        public Data() {
        }

        public Data(String id, String name, String image, String unitPrice, String discount, String quantity, String productDate, String description, String special, String latest, String clickCount) {
            this.id = id;
            this.name = name;
            this.image = image;
            this.unitPrice = unitPrice;
            this.discount = discount;
            this.quantity = quantity;
            this.productDate = productDate;
            this.description = description;
            this.special = special;
            this.latest = latest;
            this.clickCount = clickCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getProductDate() {
            return productDate;
        }

        public void setProductDate(String productDate) {
            this.productDate = productDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public String getLatest() {
            return latest;
        }

        public void setLatest(String latest) {
            this.latest = latest;
        }

        public String getClickCount() {
            return clickCount;
        }

        public void setClickCount(String clickCount) {
            this.clickCount = clickCount;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [id = "+id+", name = "+name+", image = "+image+", " +
                    "unitPrice = "+unitPrice+", discount = "+discount+", quantity = "+quantity+"," +
                    "productDate = "+productDate+", description = "+description+"," +
                    "special = "+special+", latest = "+description+", clickCount = "+clickCount+"]";
        }
    }

}
