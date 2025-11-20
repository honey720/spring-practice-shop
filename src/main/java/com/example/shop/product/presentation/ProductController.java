package com.example.shop.product.presentation;

import com.example.shop.common.ResponseEntity;
import com.example.shop.product.application.ProductService;
import com.example.shop.product.application.dto.ProductInfo;
import com.example.shop.product.presentation.dto.ProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.v1}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "상품 목록 조회", description = "등록된 상품을 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<ProductInfo>> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @Operation(summary = "상품 추가", description = "요청받은 상품을 public.product에 추가한다.")
    @PostMapping
    public ResponseEntity<ProductInfo> create(ProductRequest request) {
        return productService.ProductCreate(request.toCommand());
    }

    @Operation(summary = "상품 수정", description = "요청받은 상품을 public.product에서 수정한다.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductInfo> update(ProductRequest request, @PathVariable("id") UUID id) {
        return productService.ProductUpdate(request.toCommand(), id);
    }

    @Operation(summary = "상품 삭제", description = "요청으로 받은 상품정보로 public.product에서 삭제한다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return productService.ProductDelete(id);
    }
}
