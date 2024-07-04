package com.project.guitarShop.controller.reply;

import com.project.guitarShop.dto.reply.ReplyRequest.*;
import com.project.guitarShop.dto.reply.ReplyResponse.*;
import com.project.guitarShop.service.reply.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{id}")
    public ResponseEntity<ReplyWriteResponse> reply(@Valid @RequestBody ReplyWriteRequest request) {
        ReplyWriteResponse reply = replyService.reply(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReplyUpdateResponse> update(@Valid @PathVariable Long id, @Valid @RequestBody ReplyUpdateRequest request) {
        return ResponseEntity.ok(replyService.update(id, request));
    }

}
