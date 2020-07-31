package com.example.demo.base.dao.productInformation;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProductInformationDto {

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
    private int productPrice;

    /**
     * 商品の種類
     */
    private String productType;

    /**
     * 日別商品レンタル金額
     */
    private int productRentalDayPrice;

    /**
     * 週別商品レンタル金額
     */
    private int productRentalWeekPrice;

    /**
     * 会員ID
     */
    private int memberId;

    /**
     * 販売日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date saleDay;

    /**
     * 貸出日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date rentalDay;

    /**
     * 返却日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date rentalReturnDay;

    /**
     * 返却予定日
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date rentalReturnPlansDay;
}
