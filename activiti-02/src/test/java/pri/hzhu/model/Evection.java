package pri.hzhu.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 请假流程参数
 * @author: pp_lan
 * @date: 2021/8/7 8:32
 */
public class Evection implements Serializable {

    private static final long serialVersionUID = 538778390525308783L;

    /**
     *
     */
    private Long id;

    /**
     * 请假名称
     */
    private String evectionName;

    /**
     * 请假天数
     */
    private Double num;

    /**
     * 请假开始时间
     */
    private Date beginDate;

    /**
     * 请假结束时间
     */
    private Date endDate;

    /**
     * 请假目的地
     */
    private String destination;

    /**
     * 请假原因
     */
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvectionName() {
        return evectionName;
    }

    public void setEvectionName(String evectionName) {
        this.evectionName = evectionName;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
