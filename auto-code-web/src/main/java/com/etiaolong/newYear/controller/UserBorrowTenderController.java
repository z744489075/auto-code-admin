package com.etiaolong.newYear.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import com.zengtengpeng.common.utils.ExcelUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.etiaolong.newYear.bean.UserBorrowTender;
import com.etiaolong.newYear.service.UserBorrowTenderService;

/**
 * 
 * @author zengtp
 *
 */
@RestController
public class UserBorrowTenderController {
	
	@Resource
	private UserBorrowTenderService userBorrowTenderService;

	/**
	 * 删除
	 * @param userBorrowTender
	 * @return
	 */
	@RequestMapping("/userBorrowTender/deleteByPrimaryKey")
	public DataRes deleteByPrimaryKey(UserBorrowTender userBorrowTender, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(userBorrowTenderService.deleteByPrimaryKey(userBorrowTender));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param userBorrowTender
	 * @return
	 */
	@RequestMapping("/userBorrowTender/save")
	public DataRes save(UserBorrowTender userBorrowTender, HttpServletRequest request, HttpServletResponse response){
		if(userBorrowTender.getId()==null){
			return DataRes.success(userBorrowTenderService.insert(userBorrowTender));
		}
		return DataRes.success(userBorrowTenderService.updateByPrimaryKey(userBorrowTender));
	}

    /**
     * 根据主键查询
     * @param userBorrowTender
     * @return
     */
	@RequestMapping("/userBorrowTender/selectByPrimaryKey")
	public DataRes selectByPrimaryKey(UserBorrowTender userBorrowTender, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(userBorrowTenderService.selectByPrimaryKey(userBorrowTender));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("/userBorrowTender/queryUserBorrowTenderByCondition")
	public DataRes queryUserBorrowTenderByCondition(UserBorrowTender userBorrowTender, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(userBorrowTenderService.queryUserBorrowTenderByCondition(userBorrowTender));
    }

   /**
	* 分页查询
	* @param userBorrowTender 参数
	* @return
	*/
	@RequestMapping("/userBorrowTender/selectAll")
	public DataRes selectAll(UserBorrowTender userBorrowTender,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(userBorrowTenderService.selectAll(userBorrowTender));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("/userBorrowTender/export")
	public void export(UserBorrowTender userBorrowTender,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<UserBorrowTender> list= userBorrowTenderService.export(userBorrowTender);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("userId", "投资人ID");
        header.put("bid", "标的编号");
        header.put("buserId", "借款人ID");
        header.put("status", "状态: 0.已投资  1.已满标 2.已流标 0：投资中，1、计息中，2，已流标  3，还款中 4，已完成;9:已债转：10：债转中");
        header.put("tenderType", "来源: 1.手动投标，2.自动投标");
        header.put("autoTenderId", "自动投标ID");
        header.put("money", "投标金额（包含抵扣红包金额）");
        header.put("deductionMoney", "抵扣金额（抵扣券）");
        header.put("deductionid", "用户的抵扣券ID");
        header.put("repaymentMoney", "应还款总金额");
        header.put("repaymentInterest", "应还款利息");
        header.put("paidMoney", "已还总额");
        header.put("paidInterest", "已还利息");
        header.put("waitMoney", "待还总额");
        header.put("waitInterest", "待还利息");
        header.put("overdueInterest", "借款人逾期所带来的收益");
		header.put("addtime_", "");
        header.put("addip", "");
        header.put("btitle", "标的标题");
        header.put("bapr", "标的年化利率");
        header.put("additionalApr", "额外加的利息，不影响逾期");
        header.put("additionalid", "加息券ID");
        header.put("additionalMoney", "在还款成功时，额外加息部分的金额");
        header.put("bloanLife", "借款期限");
        header.put("bloanLifeType", "借款类型【0：月标，1：天标】");
		header.put("firstRepaymentDate_", "预计首次还款日期");
		header.put("endRepaymentDate_", "预计最后一次还款日期（预计结束日期）");
		header.put("accrualDate_", "起息日（满标审核日期）");
        header.put("repaymentType", "还款类型");
        header.put("buyId", "购买id（冗余）");
		header.put("additionalDate_", "加息起息时间");
		header.put("createDate_", "创建时间");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("/userBorrowTender/gotoList")
	public String gotoList(UserBorrowTender userBorrowTender, HttpServletRequest request, HttpServletResponse response){
		return "newYear/user_borrow_tender_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("/userBorrowTender/gotoDetail")
	public String gotoDetail(UserBorrowTender userBorrowTender, HttpServletRequest request, HttpServletResponse response){
		if(userBorrowTender.getId()!=null){
			request.setAttribute("user_borrow_tender",userBorrowTenderService.selectByPrimaryKey(userBorrowTender));
		}else {
			request.setAttribute("user_borrow_tender",userBorrowTender);
		}
		return "newYear/user_borrow_tender_detail";
	}
}
