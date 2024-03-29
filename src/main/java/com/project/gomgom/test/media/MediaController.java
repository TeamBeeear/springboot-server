//package com.project.gomgom.test.media;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Collection;
//
//@RestController
//@RequestMapping("media")
//public class MediaController {
//
//    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
//    private final MediaService mediaService;
//
//    public MediaController(MediaService mediaService) {
//        this.mediaService = mediaService;
//    }
//
//    @PostMapping("upload")
//    public ResponseEntity<MediaDescriptorDto> uploadMedia(@RequestParam("file")MultipartFile file, @RequestParam("userId") String userId) {
//        MediaDescriptorDto descriptorDto = this.mediaService.saveFile(file);
//        System.out.println(userId);
//        return ResponseEntity
//                .status(descriptorDto.getStatus())
//                .body(descriptorDto);
//    }
//
//    @PostMapping("upload-bulk")
//    public ResponseEntity<Collection<MediaDescriptorDto>> uploadMediaBulk(
//            @RequestParam("file") MultipartFile[] files
//    ) {
//        return ResponseEntity
//                .status(HttpStatus.MULTI_STATUS)
//                .body(this.mediaService.saveFileBulk(files));
//    }
//
//    @GetMapping("**")
//    public ResponseEntity<byte[]> staticLikeEndpoint(
//            HttpServletRequest request, HttpServletResponse response
//    ) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(
//                this.mediaService.getFileAsBytes(request.getRequestURI().split("media")[1]),
//                headers,
//                HttpStatus.OK
//        );
//    }
//}
