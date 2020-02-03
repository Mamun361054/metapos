
package com.metamorphosis.metapos.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PieChartPojo {

    @SerializedName("data")
    @Expose
    private List<PieData> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<PieData> getData() {
        return data;
    }

    public void setData(List<PieData> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class PieData {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("productname")
        @Expose
        private String productname;
        @SerializedName("totalamount")
        @Expose
        private Integer totalamount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public Integer getTotalamount() {
            return totalamount;
        }

        public void setTotalamount(Integer totalamount) {
            this.totalamount = totalamount;
        }

    }

}
