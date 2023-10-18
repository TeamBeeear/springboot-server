//package com.project.gomgom.test.post;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PostTestService {
//
//    private final PostTestRepository postTestRepository;
//
//
//    public PostTestEntity createPostTest(PostTestDto dto) {
//        PostTestEntity newEntity = new PostTestEntity();
//        newEntity.setTitle(dto.getTitle());
//        newEntity.setContent(dto.getContent());
//        PostTestEntity result = postTestRepository.save(newEntity);
//        return newEntity;
//    }
//
//    public PostTestEntity readPostTest(Long id) {
//        return postTestRepository.findPostTestEntityById(id);
//    }
//}
