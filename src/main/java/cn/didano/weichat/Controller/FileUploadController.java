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

import com.aliyun.oss.OSSClient;

import cn.didano.weichat.config.OssInfo;
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
	public String handleFileUploadSingle(@RequestParam("file") MultipartFile file,@RequestParam("extension") String extension, @RequestParam("module") String module) {
		logger.info("FileUploadController handleFileUploadSingle");
		if(module==null||module.equals("")||extension==null||extension.equals("")){
			logger.error("FileSystemStorageService store file module:" + module + "extension:" + extension);
			return "FileSystemStorageService store file module:" + module + "extension:" + extension;
		}
		String fileurl = null;
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
	        System.out.println(d);  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(d);  
			String tmpuri = "tmp/" + module +"/"+ dateNowStr +"/"+UUIDTool.getUUID()+"."+extension;
			String tmpPreurl = imagePath + "tmp/";
			String formalPreurl = imagePath + "formal/";
			fileurl = imagePath + tmpuri;
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			//no path
			ossClient.putObject(bucketName, tmpuri, file.getInputStream());
			//关闭client
			ossClient.shutdown();
			logger.info("finished uploadCorFile ...");
		} catch (IOException e) {
			logger.error("上传:" + filename + "失败:" + e.getMessage());
		}
		return fileurl;
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