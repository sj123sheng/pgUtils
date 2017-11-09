package sj.generated.model;

import java.io.Serializable;
import java.util.Date;

public class SongjmTest implements Serializable {
    private Integer colId;

    private String colName;

    private Date colDate;

    private static final long serialVersionUID = 1L;

    public Integer getColId() {
        return colId;
    }

    public void setColId(Integer colId) {
        this.colId = colId;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName == null ? null : colName.trim();
    }

    public Date getColDate() {
        return colDate;
    }

    public void setColDate(Date colDate) {
        this.colDate = colDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SongjmTest other = (SongjmTest) that;
        return (this.getColId() == null ? other.getColId() == null : this.getColId().equals(other.getColId()))
            && (this.getColName() == null ? other.getColName() == null : this.getColName().equals(other.getColName()))
            && (this.getColDate() == null ? other.getColDate() == null : this.getColDate().equals(other.getColDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getColId() == null) ? 0 : getColId().hashCode());
        result = prime * result + ((getColName() == null) ? 0 : getColName().hashCode());
        result = prime * result + ((getColDate() == null) ? 0 : getColDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", colId=").append(colId);
        sb.append(", colName=").append(colName);
        sb.append(", colDate=").append(colDate);
        sb.append("]");
        return sb.toString();
    }
}