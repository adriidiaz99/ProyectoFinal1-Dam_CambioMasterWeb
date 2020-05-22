package com.cambiomaster.web.upload.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cambiomaster.web.upload.DBStorageService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ImageController {

	
	private final DBStorageService dbStoreService;
	

	@GetMapping("/files/{id}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable Long id) {
		Resource file = (Resource) dbStoreService.loadAsResource(id);
		return ResponseEntity.ok().body(file);
	}
	
}
