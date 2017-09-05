package cn.didano.weichat.Controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.MailListService;
import cn.didano.weichat.Service.StudentService;
import cn.didano.weichat.config.AppConfigProperties;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_Parent_Edit;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_parent4mailList;
import cn.didano.weichat.model.Tb_deleteParentDate;
import cn.didano.weichat.model.Tb_relation;
import cn.didano.weichat.model.Tb_schoolparent4;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.util.SendSms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

@Api(value = "家庭管理服务", tags = "家庭管理服务，提供给表现层")
@RestController
@RequestMapping(value = "/base/family/post/")
public class FamilyManagementController {

	
	static Logger logger = Logger.getLogger(FamilyManagementController.class);

	@Autowired
	private MailListService mailListService;
	@Autowired
	private StudentService studentService;
	@Autowired
	AppConfigProperties appConfigProperties;
	/**
	 * 家庭管理首页 通过小朋友id查询该小朋友所有家长信息
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "studentParent_searchByChildId/{student_id}")
	@ApiOperation(value = "通过小朋友id查询所有父母", notes = "通过小朋友id查询")
	@ResponseBody
	public Out<OutList<Hand_parent4mailList>> studentParent_searchByChildId(@PathVariable("student_id") Integer student_id) {
		logger.info("访问  FamilyManagementController:studentParent_searchByChildId,student_id=" + student_id);
		List<Hand_parent4mailList> parentall = null;
		OutList<Hand_parent4mailList> outList = null;
		Out<OutList<Hand_parent4mailList>> back = new Out<OutList<Hand_parent4mailList>>();
		try {
			parentall = mailListService.findparent(student_id);
			outList = new OutList<Hand_parent4mailList>(parentall.size(), parentall);
			back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

//	/**
//	 * 通过小朋友id编辑其家长信息
//	 *
//	 * @param c_channel
//	 * @return
//	 */
//	@ApiOperation(value = "编辑特定小朋友家长的信息", notes = "编辑小朋友家长信息")
//	@PostMapping(value = "parent_edit")
//	@ResponseBody
//	public Out<String> parent_edit(
//			@ApiParam(value = "编辑特定小朋友家长的信息", required = true) @RequestBody Hand_parent4mailList parent_edit) {
//		logger.info("访问  FamilyManagementController:parent_edit,parent_edit=" + parent_edit);
//		Hand_parent4mailList parent = parent_edit;
//		Out<String> back = new Out<String>();
//		try {
//			if (parent.getRelation_id() != 99) {
//				Tb_relation relation = mailListService.findrealtionById(parent.getRelation_id());
//				parent.setParent_name(relation.getTitle());
//				parent.setRelation_id(relation.getId());
//			} 
//			int rowNum =	mailListService.UpdateParent(parent);
//			if (rowNum > 0) {
//				back.setBackTypeWithLog(BackType.SUCCESS_UPDATE, "rowNum=" + rowNum);
//			} else {
//				back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, "rowNum=" + rowNum);
//			}
//		} catch (ServiceException e) {
//			// 服务层错误，包括 内部service 和 对外service
//			back.setServiceExceptionWithLog(e.getExceptionEnums());
//		} catch (Exception ex) {
//			back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, ex.getMessage());
//		}
//		return back;
//	}

	/**
	 * 删除某位家长
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "deleteParent/{student_id}/{parent_id}")
	@ApiOperation(value = "删除某位家长", notes = "删除某位家长")
	@ResponseBody
	public Out<String> deleteParent(@PathVariable("student_id") Integer student_id,@PathVariable("parent_id") Integer parent_id) {
		logger.info("访问  FamilyManagementController:deleteParent,student_id=" + student_id+",parent_id="+parent_id);
		Out<String> back = new Out<String>();
		try {
			
			Tb_deleteParentDate date =  new Tb_deleteParentDate();
			date.setStudent_id(student_id);
			date.setParent_id(parent_id);
			int rowNum=mailListService.deleteparentByid(date);		
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_UPDATE, "rowNum=" + rowNum);
			} else {
				back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, "rowNum=" + rowNum);
			}
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

	/**
	 * 新增编辑家长
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "addParent")
	@ApiOperation(value = "新增编辑家长", notes = "新增编辑家长")
	@ResponseBody
	public Out<String> addParent(@ApiParam(value = "新增家长", required = true) @RequestBody In_Parent_Edit parent_edit) {
		logger.info("访问  FamilyManagementController:parent_edit,parent_edit=" + parent_edit);
		Tb_schoolparent4 vd_parent = new Tb_schoolparent4();
		Tb_student_parent vd_studentparent = new Tb_student_parent();
		Hand_parent4mailList parent = new Hand_parent4mailList ();
		Out<String> back = new Out<String>();
		try {
			if(parent_edit.getId()==null){
			vd_parent.setSchoolId(parent_edit.getSchoolId());
			vd_parent.setPhone(parent_edit.getPhone());
			vd_parent.setType(0);
			vd_parent.setStatus((byte) 1);
			vd_parent.setCreated(new Date());

			studentService.insertParentSelective(vd_parent);
			vd_studentparent.setSchoolId(parent_edit.getSchoolId());
			vd_studentparent.setClassId(parent_edit.getClassId());
			vd_studentparent.setStudentId(parent_edit.getStudentId());
			vd_studentparent.setParentId(vd_parent.getId());
			vd_studentparent.setRelationId(parent_edit.getRelationId());
			vd_studentparent.setIcCardId(0);
			if (parent_edit.getRelationId() != 99) {
				vd_studentparent.setRelationTitle(
						mailListService.findrealtionById(Integer.valueOf(parent_edit.getRelationId())).getTitle());
			} else {
				vd_studentparent.setRelationTitle(parent_edit.getName());
			}
			vd_studentparent.setCreated(new Date());

			int rowNum=studentService.insertStudentParentSelective(vd_studentparent);
			//发送绑定短信
			SendSms.sendMessage(vd_parent.getPhone());
			HttpPost httpPost = new HttpPost(appConfigProperties.getQrcodePath());
			logger.info("appConfigProperties.getQrcodePath()=" + appConfigProperties.getQrcodePath());
			CloseableHttpClient client = HttpClients.createDefault();
			String respContent = null;

			// json方式
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("school_id", vd_parent.getSchoolId());
			jsonParam.put("type", 1);
			jsonParam.put("parent_id", vd_parent.getId());
			jsonParam.put("student_id", parent_edit.getStudentId());

			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			HttpResponse resp = client.execute(httpPost);
			logger.info("resp.getStatusLine().getStatusCode()=" + resp.getStatusLine().getStatusCode());
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity he = resp.getEntity();
				respContent = EntityUtils.toString(he, "UTF-8");
			}
			logger.info("respContent=" + respContent);
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "rowNum=" + rowNum);
			} else {
				back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=" + rowNum);
			}
			}else{//编辑
				parent.setId(parent_edit.getId());
				parent.setParent_phone(parent_edit.getPhone());
				if (parent_edit.getRelationId() != 99) {
					Tb_relation relation = mailListService.findrealtionById(Integer.valueOf(parent_edit.getRelationId()));
					parent.setParent_name(relation.getTitle());
					parent.setRelation_id(relation.getId());
				}else{
					parent.setRelation_id(Integer.valueOf(parent_edit.getRelationId()));
					parent.setParent_name(parent_edit.getName());
				}
				int rowNum =mailListService.UpdateParent(parent);
				if (rowNum > 0) {
					back.setBackTypeWithLog(BackType.SUCCESS_UPDATE, "rowNum=" + rowNum);
				} else {
					back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, "rowNum=" + rowNum);
				}
			}
		}  catch (IOException e) {
			logger.warn(e.getMessage());
			back.setBackType(BackType.INFO_IOEXCEPTION, e.getMessage());
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}
}
