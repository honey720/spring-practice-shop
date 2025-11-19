package com.example.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test", description = "테스트용 API")
@RestController
public class TestController {

    @Operation(summary = "테스트 엔드포인트", description = "단순히 \"hello\" 문자열을 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청 성공")
    })
    @GetMapping("/")
    public String test() {
        return "hello";
    }
}
