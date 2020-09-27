package com.example.demo.base.domain.productInformation;

import lombok.Data;

@Data
public class ProductForm {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品金額
     */
    private Long productPrice;

    /**
     * 商品の種類
     */
    private String productType;

    /**
     * 会員ID
     */
    private String memberId;

    /**
     * 従業員ID
     */
    private String employeeId;

    /**
     * 従業員名
     */
    private String employeeName;
}
