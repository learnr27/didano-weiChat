package cn.didano.weichat.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.didano.weichat.file.StorageFileNotFoundException;
import cn.didano.weichat.file.StorageService;
import io.swagger.annotations.Api;

@Api(value = "微信前端上传文件")
@Controller
@RequestMapping("/base/fileupload")
public class FileUploadController {
	private static final Logger log = Logger.getLogger(FileUploadController.class);

	private final StorageService storageService;


	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		storageService.deleteAll();
		storageService.init();
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	/**
	 * 上传单个文件
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/single")
	public String handleFileUploadSingle(@RequestParam("file") MultipartFile file,@RequestParam("module") String module,
			RedirectAttributes redirectAttributes) {

		storageService.deleteAll();
		storageService.init();

		storageService.store(file,module);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		return "redirect:/";
	}

	/**
	 * 上传多个文件
	 * @param files
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/multifile")
	public String handleFileUploadMultifile(@RequestParam("file") List<MultipartFile> files,@RequestParam("module") String module,
			RedirectAttributes redirectAttributes) {

		storageService.deleteAll();
		storageService.init();
		for (int i = 0; i < files.size(); i++) {
			// Optional.ofNullable(files.get(i)).map(files.get(i)::getName).orElse("no
			// name");
			storageService.store(files.get(i),module, i + 1);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + files.get(i).getOriginalFilename() + "!");
		}
		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}