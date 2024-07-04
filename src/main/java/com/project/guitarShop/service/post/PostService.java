package com.project.guitarShop.service.post;

import com.project.guitarShop.dto.post.PostRequest.*;
import com.project.guitarShop.dto.post.PostResponse.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostWriteResponse write(PostWriteRequest request);

    Page<PostListResponse> list(Pageable pageable);

    PostReadResponse read(Long postId);

    PostUpdateResponse update(Long postId, PostUpdateRequest request);

    void delete(Long id);

}
