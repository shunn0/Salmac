package com.salmac.cleint.engine;

import java.util.ArrayList;
import java.util.List;

import com.salmac.cleint.engine.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.salmac.cleint.engine.files.FileStorageService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
	
	@Autowired
	ProcessBuilderExecutor executor;

	@Autowired
    private FileStorageService fileStorageService;
	
	//@CrossOrigin(origins = "http://10.0.2.15:3000")
	@GetMapping("/runcmd")
	public String runcmdGet() {
		return "Hello";
	}

	//@CrossOrigin(origins = "http://10.0.2.15:3000")
	@PostMapping("/runcmd")
	public List<String> runcmd(@RequestBody String cmd) {
		if (!Utils.isEmptyString(cmd)) {
			cmd = removeAppendedDelim(cmd);
			//System.out.println(cmds.trim());
			return executor.runMultipleCmd(cmd);
		} else {
			return new ArrayList<>();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/runmulticmd")
	public List<String> runmulticmd(@RequestBody String cmds) {
		if (!Utils.isEmptyString(cmds)) {
			cmds = removeAppendedDelim(cmds);
			//System.out.println(cmds.trim());
			return executor.runMultipleCmd(cmds);
		} else {
			return new ArrayList<>();
		}
	}

	@PostMapping("/runscript")
    public List<String> uploadFile(@RequestParam MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        return executor.runScript(fileName);
    }
	
	
	private String removeAppendedDelim(String str) {
		if(!Utils.isEmptyString(str)) {
			return str.trim().substring(0, str.length() - 1);
		}
		return str;
	}	
}
