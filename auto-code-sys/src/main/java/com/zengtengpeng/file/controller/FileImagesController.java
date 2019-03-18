package com.zengtengpeng.file.controller;

import javax.annotation.Resource;

import com.zengtengpeng.common.annotation.Pass;
import com.zengtengpeng.common.utils.DateUtils;
import com.zengtengpeng.common.utils.ExcelUtils;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zengtengpeng.common.bean.DataRes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.zengtengpeng.file.bean.FileImages;
import com.zengtengpeng.file.service.FileImagesService;
import org.springframework.stereotype.Controller;
import com.zengtengpeng.common.annotation.Auth;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author zengtp
 *
 */
@Controller
@Api(description = "图片管理")
public class FileImagesController {
	
	@Resource
	private FileImagesService fileImagesService;

	@Value("${auto.code.filePath}")
	private String filePath;

	/**
	 * 删除
	 * @param fileImages
	 * @return
	 */
	@RequestMapping("fileImages/deleteByPrimaryKey")
	@ResponseBody
	@ApiOperation(value="删除", notes="删除" ,httpMethod="POST")
	public DataRes deleteByPrimaryKey(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(fileImagesService.deleteByPrimaryKey(fileImages));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param fileImages
	 * @return
	 */
	@RequestMapping("fileImages/save")
	@ResponseBody
	@ApiOperation(value="保存", notes="保存" ,httpMethod="POST")
	public DataRes save(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
		SysUser user = UserUtils.getUser(request.getSession());
		fileImages.setCreateUserId(user.getId());
		if(fileImages.getId()==null){
			return DataRes.success(fileImagesService.insert(fileImages));
		}
		return DataRes.success(fileImagesService.update(fileImages));
	}
    /**
	 * 上传图片
	 * @param uploadfile
	 * @return
	 */
	@RequestMapping("fileImages/upload")
	@ResponseBody
	@Auth("fileImages/save")
	@ApiOperation(value="上传图片", notes="上传图片" ,httpMethod="POST")
	public DataRes upload(@RequestParam("file") MultipartFile uploadfile) throws IOException {
		// 获得文件：
		// 获得文件名：
		String filename = uploadfile.getOriginalFilename();
		// 获得输入流：
		InputStream input = uploadfile.getInputStream();
		File p = new File(filePath+ DateUtils.formatDateByPattern(new Date(),"/yyyy/MM/dd/"));
		if(!p.exists()){
			p.mkdirs();
		}
		File file=new File(p, DateUtils.formatDateByPattern(new Date(),"yyyyMMddHHmmss")+filename);
		uploadfile.transferTo(file);
		return DataRes.success(DateUtils.formatDateByPattern(new Date(),"yyyy/MM/dd/")+DateUtils.formatDateByPattern(new Date(),"yyyyMMddHHmmss")+filename);
	}

	/**
	 * 查看图片
	 * @return
	 */
	@RequestMapping("fileImages/watch")
	@Pass
	@ApiOperation(value="查看图片", notes="查看图片" ,httpMethod="POST")
	public void watch(String path,HttpServletResponse response) throws IOException {
		File file=new File(filePath+path);
		FileInputStream fileInputStream=null;
		try {
			fileInputStream=new FileInputStream(file);
			int len;
			byte[] buff = new byte[1204];
			while ((len=fileInputStream.read(buff))!=-1){
				response.getOutputStream().write(buff,0,len);
			}
		} finally {
			if (fileInputStream!=null){
				fileInputStream.close();
			}
		}


	}

    /**
     * 根据主键查询
     * @param fileImages
     * @return
     */
	@RequestMapping("fileImages/selectByPrimaryKey")
	@ResponseBody
	@ApiOperation(value="根据主键查询", notes="根据主键查询" ,httpMethod="POST")
	public DataRes selectByPrimaryKey(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(fileImagesService.selectByPrimaryKey(fileImages));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("fileImages/queryFileImagesByCondition")
	@ResponseBody
	@ApiOperation(value="根据条件查询", notes="根据条件查询" ,httpMethod="POST")
	public DataRes queryByCondition(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(fileImagesService.selectByCondition(fileImages));
    }

   /**
	* 分页查询
	* @param fileImages 参数
	* @return
	*/
	@RequestMapping("fileImages/selectAll")
	@ResponseBody
	@Auth({"fileImages/selectAll","sysUser/save"})
	@ApiOperation(value="分页查询", notes="分页查询" ,httpMethod="POST")
	public DataRes selectAll(FileImages fileImages,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(fileImagesService.selectAllByPaging(fileImages));
    }

	/**
	* 导出数据
	* @param tests 参数
	* @return
	*/
	@RequestMapping("fileImages/export")
	@ApiOperation(value="导出数据", notes="导出数据" ,httpMethod="POST")
	public void export(FileImages fileImages,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<FileImages> list= fileImagesService.selectAll(fileImages);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "图片管理");
        header.put("name", "名称");
        header.put("imageUrl", "图片url");
		header.put("createTime_", "创建时间");
        header.put("createUserId", "创建人");
		ExcelUtils.exportExcel("图片管理",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("fileImages/gotoList")
	@ApiIgnore
	public String gotoList(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
		return "file/file_images_list";
	}
	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("fileImages/selectList")
	@Auth
	@ApiIgnore
	public String selectList(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
		return "file/select_images_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("fileImages/gotoDetail")
	@Auth("fileImages/save")
	@ApiIgnore
	public String gotoDetail(FileImages fileImages, HttpServletRequest request, HttpServletResponse response){
		if(fileImages.getId()!=null){
			request.setAttribute("file_images",fileImagesService.selectByPrimaryKey(fileImages));
		}else {
			request.setAttribute("file_images",fileImages);
		}
		return "file/file_images_detail";
	}
}
