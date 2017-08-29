package cn.didano.weichat.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

import cn.didano.weichat.config.OssInfo;

@Service
public class FileSystemStorageService implements StorageService {
	private static final Logger logger = Logger.getLogger(FileSystemStorageService.class);
	@Autowired
	OssInfo ossInfo;
	

	@Override
	public String store(MultipartFile file,String module) {
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
			String uri = "tmp/" + file.getOriginalFilename();
			fileurl = imagePath + uri;
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			//no path
			ossClient.putObject(bucketName, uri, file.getInputStream());
			//关闭client
			ossClient.shutdown();
			logger.info("finished uploadCorFile ...");
		} catch (IOException e) {
			logger.error("上传:" + filename + "失败:" + e.getMessage());
		}
		return fileurl;
	}
}
