
package com.metamorphosis.metapos.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountsPojo {

    @SerializedName("data")
    @Expose
    private List<AccountsData> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<AccountsData> getData() {
        return data;
    }

    public void setData(List<AccountsData> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class AccountsData {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("imageurl")
        @Expose
        private String imageurl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

    }

}
