package com.cambiomaster.web.upload;

import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService<T, U> {
	
	void init();

    String store(MultipartFile file);

    Stream<T> loadAll();

    T load(U id);

    Resource loadAsResource(U id);
    
    void delete(U id);

    void deleteAll();

}
