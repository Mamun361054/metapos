
package com.metamorphosis.metapos.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInPojo {

    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Data {

        @SerializedName("shopname")
        @Expose
        private String shopname;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private Object password;
        @SerializedName("roleId")
        @Expose
        private String roleId;
        @SerializedName("storeId")
        @Expose
        private String storeId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("branchId")
        @Expose
        private String branchId;
        @SerializedName("activedate")
        @Expose
        private String activedate;
        @SerializedName("expirydate")
        @Expose
        private String expirydate;
        @SerializedName("monthlyfee")
        @Expose
        private String monthlyfee;
        @SerializedName("baseurl")
        @Expose
        private String baseurl;

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getActivedate() {
            return activedate;
        }

        public void setActivedate(String activedate) {
            this.activedate = activedate;
        }

        public String getExpirydate() {
            return expirydate;
        }

        public void setExpirydate(String expirydate) {
            this.expirydate = expirydate;
        }

        public String getMonthlyfee() {
            return monthlyfee;
        }

        public void setMonthlyfee(String monthlyfee) {
            this.monthlyfee = monthlyfee;
        }

        public String getBaseurl() {
            return baseurl;
        }

        public void setBaseurl(String baseurl) {
            this.baseurl = baseurl;
        }

    }



}
