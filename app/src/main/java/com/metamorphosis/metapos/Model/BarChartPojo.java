
package com.metamorphosis.metapos.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BarChartPojo {

    @SerializedName("data")
    @Expose
    private List<BarData> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<BarData> getData() {
        return data;
    }

    public void setData(List<BarData> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class BarData {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("saleamount")
        @Expose
        private String saleamount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSaleamount() {
            return saleamount;
        }

        public void setSaleamount(String saleamount) {
            this.saleamount = saleamount;
        }
    }

}
