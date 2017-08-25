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
	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file,String module) {
		logger.info("store....");
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory " + filename);
			}
			// Files.copy(file.getInputStream(),
			// this.rootLocation.resolve(filename),
			// StandardCopyOption.REPLACE_EXISTING);
			logger.info("ready uploadCorFile ...");
			String endpoint = ossInfo.getEndpoint();
			String accessKeyId = ossInfo.getAccessKeyId();
			String accessKeySecret = ossInfo.getAccessKeySecret();
			String bucketName = ossInfo.getBucketname();
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			//no path
			ossClient.putObject(bucketName, file.getOriginalFilename(), file.getInputStream());
			// 关闭client
			ossClient.shutdown();
			logger.info("finished uploadCorFile ...");
		} catch (IOException e) {
			logger.error("上传:" + filename + "失败:" + e.getMessage());
		}
	}

	@Override
	public void store(MultipartFile file, String module,int i) {
		try {
			if (file.isEmpty()) {
				logger.warn("Failed to store empty file ,file.getOriginalFilename()=" + file.getOriginalFilename());
				// throw new StorageException("Failed to store empty file " +
				// file.getOriginalFilename());
			} else {
				System.err.println(file.getName());
				Files.copy(file.getInputStream(),
						this.rootLocation.resolve("pic" + i + "." + file.getOriginalFilename().split("\\.")[1]));
			}

		} catch (IOException e) {
			logger.error("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
