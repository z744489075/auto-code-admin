package com.etiaolong.newYear.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zengtengpeng.common.bean.Page;
import com.zengtengpeng.common.utils.DateUtils;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
* 测试生成代码
* @author zengtp
*/
public class UserBorrowTender extends Page {
    /**
     * 测试生成代码
     */
    private Integer id;

    /**
     * 投资人ID
     */
    private Integer userId;

    /**
     * 标的编号
     */
    private Integer bid;

    /**
     * 借款人ID
     */
    private Integer buserId;

    /**
     * {"0":"投资中","1":"计息中","2":"已流标","3":"还款中","4":"已完成","9":"已债转","10":"债转中"}
     */
    private Byte status;

    /**
     * {"1":"手动投标","2":"自动投标"}
     */
    private Byte tenderType;

    /**
     * 自动投标ID
     */
    private Integer autoTenderId;

    /**
     * 投标金额（包含抵扣红包金额）
     */
    private Long money;

    /**
     * 抵扣金额（抵扣券）
     */
    private Integer deductionMoney;

    /**
     * 用户的抵扣券ID
     */
    private Integer deductionid;

    /**
     * 应还款总金额
     */
    private Long repaymentMoney;

    /**
     * 应还款利息
     */
    private Integer repaymentInterest;

    /**
     * 已还总额
     */
    private Long paidMoney;

    /**
     * 已还利息
     */
    private Integer paidInterest;

    /**
     * 待还总额
     */
    private Long waitMoney;

    /**
     * 待还利息
     */
    private Integer waitInterest;

    /**
     * 借款人逾期所带来的收益
     */
    private Integer overdueInterest;

    private Date addtime;

    private String addip;

    /**
     * 标的标题
     */
    private String btitle;

    /**
     * 标的年化利率
     */
    private BigDecimal bapr;

    /**
     * 额外加的利息，不影响逾期
     */
    private BigDecimal additionalApr;

    /**
     * 加息券ID
     */
    private Integer additionalid;

    /**
     * 在还款成功时，额外加息部分的金额
     */
    private Integer additionalMoney;

    /**
     * 借款期限
     */
    private Short bloanLife;

    /**
     * {"0":"月标","1":"天标"}
     */
    private Byte bloanLifeType;

    /**
     * 预计首次还款日期
     */
    private Date firstRepaymentDate;

    /**
     * 预计最后一次还款日期（预计结束日期）
     */
    private Date endRepaymentDate;

    /**
     * 起息日（满标审核日期）
     */
    private Date accrualDate;

    /**
     * 还款类型
     */
    private Byte repaymentType;

    /**
     * 购买id（冗余）
     */
    private Integer buyId;

    /**
     * 加息起息时间
     */
    private Date additionalDate;

    /**
     * 创建时间
     */
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getBuserId() {
        return buserId;
    }

    public void setBuserId(Integer buserId) {
        this.buserId = buserId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTenderType() {
        return tenderType;
    }

    public void setTenderType(Byte tenderType) {
        this.tenderType = tenderType;
    }

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getDeductionMoney() {
        return deductionMoney;
    }

    public void setDeductionMoney(Integer deductionMoney) {
        this.deductionMoney = deductionMoney;
    }

    public Integer getDeductionid() {
        return deductionid;
    }

    public void setDeductionid(Integer deductionid) {
        this.deductionid = deductionid;
    }

    public Long getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(Long repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public Integer getRepaymentInterest() {
        return repaymentInterest;
    }

    public void setRepaymentInterest(Integer repaymentInterest) {
        this.repaymentInterest = repaymentInterest;
    }

    public Long getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(Long paidMoney) {
        this.paidMoney = paidMoney;
    }

    public Integer getPaidInterest() {
        return paidInterest;
    }

    public void setPaidInterest(Integer paidInterest) {
        this.paidInterest = paidInterest;
    }

    public Long getWaitMoney() {
        return waitMoney;
    }

    public void setWaitMoney(Long waitMoney) {
        this.waitMoney = waitMoney;
    }

    public Integer getWaitInterest() {
        return waitInterest;
    }

    public void setWaitInterest(Integer waitInterest) {
        this.waitInterest = waitInterest;
    }

    public Integer getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(Integer overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    @JsonIgnore
    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle == null ? null : btitle.trim();
    }

    public BigDecimal getBapr() {
        return bapr;
    }

    public void setBapr(BigDecimal bapr) {
        this.bapr = bapr;
    }

    public BigDecimal getAdditionalApr() {
        return additionalApr;
    }

    public void setAdditionalApr(BigDecimal additionalApr) {
        this.additionalApr = additionalApr;
    }

    public Integer getAdditionalid() {
        return additionalid;
    }

    public void setAdditionalid(Integer additionalid) {
        this.additionalid = additionalid;
    }

    public Integer getAdditionalMoney() {
        return additionalMoney;
    }

    public void setAdditionalMoney(Integer additionalMoney) {
        this.additionalMoney = additionalMoney;
    }

    public Short getBloanLife() {
        return bloanLife;
    }

    public void setBloanLife(Short bloanLife) {
        this.bloanLife = bloanLife;
    }

    public Byte getBloanLifeType() {
        return bloanLifeType;
    }

    public void setBloanLifeType(Byte bloanLifeType) {
        this.bloanLifeType = bloanLifeType;
    }

    @JsonIgnore
    public Date getFirstRepaymentDate() {
        return firstRepaymentDate;
    }

    public void setFirstRepaymentDate(Date firstRepaymentDate) {
        this.firstRepaymentDate = firstRepaymentDate;
    }

    @JsonIgnore
    public Date getEndRepaymentDate() {
        return endRepaymentDate;
    }

    public void setEndRepaymentDate(Date endRepaymentDate) {
        this.endRepaymentDate = endRepaymentDate;
    }

    @JsonIgnore
    public Date getAccrualDate() {
        return accrualDate;
    }

    public void setAccrualDate(Date accrualDate) {
        this.accrualDate = accrualDate;
    }

    public Byte getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Byte repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Integer getBuyId() {
        return buyId;
    }

    public void setBuyId(Integer buyId) {
        this.buyId = buyId;
    }

    @JsonIgnore
    public Date getAdditionalDate() {
        return additionalDate;
    }

    public void setAdditionalDate(Date additionalDate) {
        this.additionalDate = additionalDate;
    }

    @JsonIgnore
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus_() {
        if(StringUtils.isEmpty(status)){
             return "";
        }else if(status.equals(0)){
             return "投资中";
        }else if(status.equals(1)){
             return "计息中";
        }else if(status.equals(2)){
             return "已流标";
        }else if(status.equals(3)){
             return "还款中";
        }else if(status.equals(4)){
             return "已完成";
        }else if(status.equals(9)){
             return "已债转";
        }else if(status.equals(10)){
             return "债转中";
        }
        return "";
    }

    public String getTenderType_() {
        if(StringUtils.isEmpty(tenderType)){
             return "";
        }else if(tenderType.equals(1)){
             return "手动投标";
        }else if(tenderType.equals(2)){
             return "自动投标";
        }
        return "";
    }

    public String getAddtime_() {
        return DateUtils.formatDateTime(addtime);
    }

    public String getBloanLifeType_() {
        if(StringUtils.isEmpty(bloanLifeType)){
             return "";
        }else if(bloanLifeType.equals(0)){
             return "月标";
        }else if(bloanLifeType.equals(1)){
             return "天标";
        }
        return "";
    }

    public String getFirstRepaymentDate_() {
        return DateUtils.formatDate(firstRepaymentDate);
    }

    public String getEndRepaymentDate_() {
        return DateUtils.formatDate(endRepaymentDate);
    }

    public String getAccrualDate_() {
        return DateUtils.formatDate(accrualDate);
    }

    public String getAdditionalDate_() {
        return DateUtils.formatDateTime(additionalDate);
    }

    public String getCreateDate_() {
        return DateUtils.formatDateTime(createDate);
    }
}