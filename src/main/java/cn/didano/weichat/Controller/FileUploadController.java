package cn.didano.weichat.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;

import cn.didano.weichat.config.OssInfo;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.util.UUIDTool;
import io.swagger.annotations.Api;

@Api(value = "微信前端上传文件" , tags = "微信前端上传文件")
@Controller
@RequestMapping("/base/fileupload")
public class FileUploadController {
	private static final Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	OssInfo ossInfo;

	/**
	 * 上传单个文件
	 * @param file 文件
	 * @param extension 扩展名
	 * @param module 模块编号
	 * @return
	 */
	@ResponseBody
	@PostMapping("/single")
	public Out<String> handleFileUploadSingle(@RequestParam("file") MultipartFile file,@RequestParam("extension") String extension, @RequestParam("module") String module) {
		logger.info("FileUploadController handleFileUploadSingle");
		String fileurl = null;
		OSSClient ossClient = null;
		Out<String> back = new Out<String>();
		if(module==null||module.equals("")||extension==null||extension.equals("")){
			logger.error("FileSystemStorageService store file module:" + module + "extension:" + extension);
			back.setBackTypeWithLog(BackType.FAIL_UPLOAD_FILE_PARA_ERROR, fileurl, "FileSystemStorageService store file module:" + module + "extension:" + extension);
			return back;
		}
		logger.info("store....");
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				logger.error("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				logger.error("Cannot store file with relative path outside current directory " + filename);
			}
			logger.info("ready uploadCorFile ...");
			String endpoint = ossInfo.getEndpoint();
			String accessKeyId = ossInfo.getAccessKeyId();
			String accessKeySecret = ossInfo.getAccessKeySecret();
			String bucketName = ossInfo.getBucketname();
			String imagePath = ossInfo.getImgPath();
			Date d = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(d);  
			String tmpuri = "tmp/" + module +"/"+ dateNowStr +"/"+UUIDTool.getUUID()+"."+extension;
			fileurl = imagePath + tmpuri;
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			ossClient.putObject(bucketName, tmpuri, file.getInputStream());
			logger.info("finished uploadCorFile ...");
			back.setBackTypeWithLog(BackType.SUCCESS_UPLOAD_FILE, fileurl, BackType.SUCCESS_UPLOAD_FILE.getMessage());
		}catch (IOException io) {
			logger.error("上传:" + filename + "失败:" + io.getMessage());
            back.setBackTypeWithLog(BackType.FAIL_UPLOAD_FILE, fileurl, BackType.FAIL_UPLOAD_FILE.getMessage());
        }
		catch (OSSException oe) {
			logger.error("上传:" + filename + "失败:" + oe.getMessage());
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            back.setBackTypeWithLog(BackType.FAIL_UPLOAD_FILE, fileurl, BackType.FAIL_UPLOAD_FILE.getMessage());
        } catch (ClientException ce) {
        	logger.error("上传:" + filename + "失败:" + ce.getMessage());
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            back.setBackTypeWithLog(BackType.FAIL_UPLOAD_FILE, fileurl, BackType.FAIL_UPLOAD_FILE.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
        	ossClient.shutdown();
        }
		return back;
	}

	/**
	 * 上传多个文件
	 * @param files
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/multifile")
	@ResponseBody
	public String handleFileUploadMultifile(@RequestParam("file") List<MultipartFile> files,@RequestParam("module") String module,
			RedirectAttributes redirectAttributes) {

//		storageService.deleteAll();
//		storageService.init();
//		for (int i = 0; i < files.size(); i++) {
//			// Optional.ofNullable(files.get(i)).map(files.get(i)::getName).orElse("no
//			// name");
//			storageService.store(files.get(i),module, i + 1);
//			redirectAttributes.addFlashAttribute("message",
//					"You successfully uploaded " + files.get(i).getOriginalFilename() + "!");
//		}
		return "redirect:/";
	}


}