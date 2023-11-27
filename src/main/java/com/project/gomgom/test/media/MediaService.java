package com.project.gomgom.test.media;

import java.util.Collection;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    MediaDescriptorDto saveFile(MultipartFile file);
    Collection<MediaDescriptorDto> saveFileBulk(MultipartFile[] files);
    byte[] getFileAsBytes(String resourcePath);
}
