package com.project.gomgom.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class PostTestController {

    private final PostTestService postTestService;

    public PostTestController(PostTestService postTestService) {
        this.postTestService = postTestService;
    }

    @PostMapping
    public ResponseEntity<PostTestEntity> createPostTest(@RequestBody PostTestDto dto) {
        return ResponseEntity.ok(postTestService.createPostTest(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<PostTestEntity> readPostTest(@PathVariable("id") Long id) {
        PostTestEntity result = postTestService.readPostTest(id);
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(result);
    }
}
